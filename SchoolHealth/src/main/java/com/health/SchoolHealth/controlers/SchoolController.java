package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolMedicForm;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.services.AddressService;
import com.health.SchoolHealth.services.SchoolMedicsService;
import com.health.SchoolHealth.services.SchoolService;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
public class SchoolController {

    // Services
    @Autowired
    private SchoolMedicsService schoolMedicsService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private AddressService addressService;

    // ModelAndViews
    ModelAndView modelAndView;

    ModelAndView newModelAndView;

    // Forms
    SchoolMedicForm schoolMedicForm= new SchoolMedicForm();

    SchoolForm schoolForm = new SchoolForm();

    AddressForm addressForm = new AddressForm();

    @GetMapping
    @RequestMapping(value ={"school", "school/{medicId}"})
    public ModelAndView getSchooldata(@PathVariable(value = "medicId", required = false) Optional<Long> medicId, HttpSession httpSession) {

        modelAndView = new ModelAndView("school");

        // Училищно медицинско лице
        Long schoolMedicId = medicId.orElse(1L);
        SchoolMedics schoolMedic = schoolMedicsService.getSchoolMedic(schoolMedicId);
        schoolMedicForm.setSchoolMedic(schoolMedic);
        modelAndView.addObject("schoolMedicForm", schoolMedicForm);

        // Училище
        if (!schoolService.getAllSchoolsBySchoolMedic(schoolMedicId).isEmpty()){
            schoolForm.setSchool(schoolService.getAllSchoolsBySchoolMedic(schoolMedicId).get(0));
        }
        // За случая, в който имаме записан медик, но още нямаме регистрирано училището, в което работи
        // записваме създадения медик в училището
        if (schoolForm.getSchool() == null) {
            School newSchool = new School();
            newSchool.setSchoolMedics(schoolMedic);
            schoolForm.setSchool(newSchool);
        }
        if (schoolForm.getSchool().getId() == null) {
            schoolForm.getSchool().setSchoolMedics(schoolMedic);
        }

        // Зареждаме всички видове училища
        schoolForm.setSchoolTypes(schoolService.getAllSchoolTypes());

        // schoolForm.setSchoolMedics(schoolMedicForm.getSchoolMedic());  Това да стане преди записа на училището за пръв път

        // modelAndView = new ModelAndView("redirect:/school/"+schoolMedicForm.getSchoolMedic().getId());
        httpSession.setAttribute("redirect", "redirect:/school");

        // Адрес
        Long addressId = null;
        if (schoolForm.getSchool().getSchoolAddress() != null &&  schoolForm.getSchool().getSchoolAddress().getId() != null) {
            addressId = schoolForm.getSchool().getSchoolAddress().getId();
        }

        System.out.println("schoolForm.getSchool()vvvvvvvvvvvvvll ");
                System.out.println("schoolForm.getSchool()vvvvvvvvvvvvvll " + schoolForm.getSchool() == null);

        System.out.println("schoolForm.getSchool().getId()xxxxxxxxxxxxxxx " + schoolForm.getSchool().getId());
        if (schoolForm.getSchool() == null || schoolForm.getSchool().getId() == null) {
            addressForm.setIsOwnerNotPresent(true);
            System.out.println("TRUE");
        } else {
            addressForm.setIsOwnerNotPresent(false);
            System.out.println("FALSE");
        }
        FormUtil.setAddressForm(FormUtil.ADDRESS_ABOUT_SCHOOL, addressId, null,
                addressService, addressForm, httpSession);

        // Пазим schoolId в addressForm, за да можем след събмит на формата да актуализираме училището
        addressForm.setSchoolId(schoolForm.getSchool().getId());
System.out.println("@@@@@@@@@@@ " + addressForm.getIsOwnerNotPresent());
        modelAndView.addObject("addressForm" , addressForm);
        modelAndView.addObject("schoolForm" , schoolForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"schoolMedicPostData"})
    public ModelAndView schoolMedicPostData(@ModelAttribute("schoolMedicForm") SchoolMedicForm schoolMedicForm,
                                            HttpSession httpSession) {

        schoolMedicsService.createOrUpdateSchoolMedic(schoolMedicForm.getSchoolMedic());

        newModelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));

        return newModelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"schoolPostData"})
    public ModelAndView postSchoolData(@ModelAttribute("schoolForm") SchoolForm schoolForm,
                                       HttpSession httpSession) {

        // Ако нямаме записан адрес, сетвавме полето на null, защото в противен случай при запис на училище,
        // hibernate търси свързан обект в таблицата с адреси, а нямаме Id
        if (schoolForm.getSchool().getSchoolAddress() != null
                && schoolForm.getSchool().getSchoolAddress().getId() == null) {
            schoolForm.getSchool().setSchoolAddress(null);
        }
        schoolService.createOrUpdateSchool(schoolForm.getSchool());
        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/cleanSessionDataOnSchoolRedirect"})
    public ModelAndView cleanRegionAndMunicipalitySessionData(HttpSession httpSession) {
        httpSession.setAttribute("regionCode", null);
        httpSession.setAttribute("municipalityCode", null);
        modelAndView = new ModelAndView("redirect:/school");
        return modelAndView;
    }
}


