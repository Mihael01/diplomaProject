package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolMedicForm;
import com.health.SchoolHealth.model.entities.*;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@RestController
public class AddressController {

//  Services
    @Autowired
    private SchoolMedicsService schoolMedicsService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ParentService parentService;


//  ModelAndViews
    ModelAndView modelAndView;

//  Forms
    SchoolMedicForm schoolMedicForm= new SchoolMedicForm();

    SchoolForm schoolForm = new SchoolForm();

    AddressForm addressForm = new AddressForm();

    @PostMapping
    @RequestMapping(value = {"addressPostData"})
    public ModelAndView addressPostData( @ModelAttribute(value="addressForm") AddressForm addressForm,
                                         @ModelAttribute(value="addressForm2") AddressForm addressForm2,
                                        ModelAndView modelAndView, HttpSession httpSession) {

        Long parentId = null;

        AddressForm currentAddressForm = null;

        boolean hasEkatte = false;
        Address address = null;
        if (addressForm2 != null && addressForm2.getParent2Id() != null) {
            currentAddressForm = addressForm2;

            parentId =  (Long) httpSession.getAttribute("parent2Id");

            if (addressForm2.getAddress2() != null && addressForm2.getAddress2().getSettlementPlace() != null
                    && addressForm2.getAddress2().getSettlementPlace().getEkatte() != null) {
                hasEkatte = true;
            }

            address = addressForm2.getAddress2();
        } else {
            currentAddressForm = addressForm;
            parentId =  (Long) httpSession.getAttribute("parent1Id");

            if (addressForm.getAddress() != null && addressForm.getAddress().getSettlementPlace() != null
                    && addressForm.getAddress().getSettlementPlace().getEkatte() != null) {
                hasEkatte = true;
            }

            address = addressForm.getAddress();
        }

        if (hasEkatte) {
            String addressAbout = (String) httpSession.getAttribute("addressAbout");

            // Записваме адреса
            Address savedAddress = addressService.createOrUpdateAddress(address);

            if (savedAddress != null) {
                switch (addressAbout) {
                    case FormUtil.ADDRESS_ABOUT_SCHOOL: {
                        if (addressForm.getSchoolId() != null) {
                            School school = schoolService.findSchoolById(addressForm.getSchoolId());
                            if (school != null) {
                                school.setSchoolAddress(savedAddress);
                                schoolService.createOrUpdateSchool(school);
                            }
                        }
                        modelAndView = new ModelAndView("redirect:/school");
                        break;
                    }
                    case FormUtil.ADDRESS_ABOUT_STUDENT: {
                        Long studentId = (Long) httpSession.getAttribute("studentId");

                        if (studentId != null) {
                            Student student = studentService.findStudentById(studentId);
                            if (student != null) {
                                student.setStudentAddress(savedAddress);
                                studentService.createOrUpdateStudent(student);
                            }
                        }
                        modelAndView = new ModelAndView("redirect:/lzpk");
                        break;
                    }
                    case FormUtil.ADDRESS_ABOUT_PARENT: {
                        if (parentId != null) {
                            Parent parent = parentService.findParentById(parentId);
                            if (parent != null) {
                                parent.setParentAddress(savedAddress);
                                parentService.createOrUpdateParent(parent);
                            }
                        }
                        modelAndView = new ModelAndView("redirect:/parents");
                        break;
                    }
                    default: {
                        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));
                    }
                }
            }
        }
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/regionPostData"})
    public ModelAndView regionPostData(@RequestParam(value = "element", required = false) String element,
                                       @RequestBody String value, HttpSession httpSession) {
        //През дропдаун value имаме кода на областта

        if ("2".equals(element)) {
            httpSession.setAttribute("regionCode2", value);
            httpSession.setAttribute("regionCode", null);
        } else {
            httpSession.setAttribute("regionCode", value);
            httpSession.setAttribute("regionCode2", null);
        }
        System.out.println(">>> httpSession.getAttribute(\"redirect\") " + httpSession.getAttribute("redirect"));

        // Връщаме "redirect" в зависимост от страницата, от която сме извикали смяна на областта
        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));
        return modelAndView;
    }

//    @PostMapping
//    @RequestMapping(value = {"regionPostData"})
//    public @ResponseBody List<Municipality> regionPostData(@RequestParam(value = "element", required = false) String element,
//                                      @RequestBody String value,
//                                      @ModelAttribute(value="addressForm") AddressForm addressForm,
//                                      @ModelAttribute(value="addressForm2") AddressForm addressForm2,
//                                      ModelAndView modelAndView, HttpSession httpSession) {
//        //През дропдаун value имаме кода на областта
//
//        if ("2".equals(element)) {
//            httpSession.setAttribute("regionCode2", value);
//            httpSession.setAttribute("regionCode", null);
//        } else {
//            httpSession.setAttribute("regionCode", value);
//            httpSession.setAttribute("regionCode2", null);
//        }
//
//        // Връщаме "redirect" в зависимост от страницата, от която сме извикали смяна на областта
////        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));
//
////        Map<String, Object> model = modelAndView.getModel();
////        model.put("isMunicipalityEnable", true);
////        model.put("municipalities", addressService.getAllMunicipalitiesInRegion(addressForm.getRegionCode()));
////        addressForm.setIsMunicipalityEnable(true);
////        System.out.println("REGION {PST DATA  .getRegionCode() " + addressForm.getRegionCode());
////        addressForm.setMunicipalities(addressService.getAllMunicipalitiesInRegion(addressForm.getRegionCode()));
////        modelAndView.addObject("addressForm", addressForm);
//        return  addressService.getAllMunicipalitiesInRegion(addressForm.getRegionCode());
//    }




    @PostMapping
    @RequestMapping(value = {"municipalitiesPostData"})
    public ModelAndView municipalitiesPostData(@RequestParam(value = "element", required = false) String element,
                                               @RequestBody String value, HttpSession httpSession) {

        //През дропдаун value имаме кода на общината
        if ("2".equals(element)) {
            httpSession.setAttribute("municipalityCode2", value);
            httpSession.setAttribute("municipalityCode", null);
        } else {
            httpSession.setAttribute("municipalityCode", value);
            httpSession.setAttribute("municipalityCode2", null);
        }
        // Връщаме "redirect" в зависимост от страницата, от кояйто сме извикали смяна на общината
        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));
        return modelAndView;

    }
}
