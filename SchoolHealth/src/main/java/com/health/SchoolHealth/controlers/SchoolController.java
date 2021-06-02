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
     public ModelAndView getSchooldata(HttpSession httpSession) {

        modelAndView = new ModelAndView("school");

        // Училищно медицинско лице
        SchoolMedics schoolMedic = (SchoolMedics) httpSession.getAttribute("schoolMedic");
        Long schoolMedicId = null;

        if (schoolMedic != null){
            System.out.println(">>>> " + schoolMedic.getId());
            schoolMedicId = schoolMedic.getId();
        }

        //Да се добави логика, ако логнатия потребител не е училищно медицинско лице!


//        Long schoolMedicId = medicId.orElse(1L);
//        SchoolMedics schoolMedic = schoolMedicsService.getSchoolMedic(schoolMedicId);
        if (schoolMedic != null) {
            schoolMedicForm.setSchoolMedic(schoolMedic);
        } else {
            schoolMedicForm.setSchoolMedic(new SchoolMedics());
        }

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

        System.out.println( "schoolForm.getSchool() == null  " + schoolForm.getSchool() == null );
        System.out.println( "schoolForm.getSchool().getId() == null  " + (schoolForm.getSchool().getId() == null)  + ' '
                + (schoolForm.getSchool() == null || schoolForm.getSchool().getId() == null));
        if (schoolForm.getSchool() == null || schoolForm.getSchool().getId() == null) {
            addressForm.setIsOwnerNotPresent(true);
        } else {
            addressForm.setIsOwnerNotPresent(false);
        }

        // Осигуряваме адреса да се запише за дадено училище
        addressForm.setIsOwnerNotPresent(schoolForm.getSchool() == null || schoolForm.getSchool().getId() == null);

        FormUtil.setAddressForm(FormUtil.ADDRESS_ABOUT_SCHOOL, addressId, null,
                addressService, addressForm, httpSession);

        // Пазим schoolId в addressForm, за да можем след събмит на формата да актуализираме училището
        addressForm.setSchoolId(schoolForm.getSchool().getId());
        modelAndView.addObject("addressForm" , addressForm);
        modelAndView.addObject("schoolForm" , schoolForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"schoolMedicPostData"})
    public ModelAndView schoolMedicPostData(@ModelAttribute("schoolMedicForm") SchoolMedicForm schoolMedicForm,
                                            HttpSession httpSession) {

        SchoolMedics schoolMedics = schoolMedicsService.createOrUpdateSchoolMedic(schoolMedicForm.getSchoolMedic());
        httpSession.setAttribute("schoolMedic", schoolMedics);

        newModelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));

        return newModelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"schoolPostData"})
    public ModelAndView postSchoolData(@ModelAttribute("schoolForm") SchoolForm schoolForm,
                                       HttpSession httpSession) {

        // Ако нямаме записан адрес, сетваме полето на null, защото в противен случай при запис на училище,
        // hibernate търси свързан обект в таблицата с адреси, а нямаме Id
        if (schoolForm.getSchool().getSchoolAddress() != null
                && schoolForm.getSchool().getSchoolAddress().getId() == null) {
            schoolForm.getSchool().setSchoolAddress(null);
        }

        if (schoolForm.getSchool() != null && (schoolForm.getSchool().getSchoolMedics() == null || schoolForm.getSchool().getSchoolMedics().getId() == null)) {
            SchoolMedics schoolMedic = (SchoolMedics) httpSession.getAttribute("schoolMedic");
            if (schoolMedic != null) {
                schoolForm.getSchool().setSchoolMedics(schoolMedic);
            }

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


