package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolMedicForm;
import com.health.SchoolHealth.model.entities.*;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;


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

    ModelAndView newModelAndView;

//  Forms
    SchoolMedicForm schoolMedicForm= new SchoolMedicForm();

    SchoolForm schoolForm = new SchoolForm();

    AddressForm addressForm = new AddressForm();

//    @GetMapping
//    //addressId, ownerId, adressAbout like post parameters
//    @RequestMapping(value ={"address", "address/{addressId}/{ownerId}/{addressAbout}"})
//    public ModelAndView getAddressData(@RequestParam(value = "addressId", required = false) String addressIdParam,
//                                      @RequestParam(value = "ownerId", required = false) String ownerIdParam,
//                                      @RequestParam(value = "addressAbout", required = false) String addressAboutParam,
//                                      HttpSession httpSession) {
//
//
//        System.out.println("addressId " + addressIdParam);
//        System.out.println("ownerId " + ownerIdParam);
//        System.out.println("adressAbout " + addressAboutParam);
//
//        Long addressId = !addressIdParam.isEmpty()? Long.valueOf(addressIdParam) : null;
//        Long ownerId = !ownerIdParam.isEmpty()? Long.valueOf(ownerIdParam) : null;
//        String adressAbout = addressAboutParam;
//
//        // Връщаме "redirect" в зависимост от страницата, от кояйто сме извикали смяна на областта
//        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect") + "::addressFormFragment");
//
//        if (addressForm == null) {
//            addressForm = new AddressForm();
//        }
//        FormUtil.setAddressForm(adressAbout, addressId,
//                addressService, addressForm, httpSession);
//
//        return modelAndView;
//    }

    @PostMapping
    @RequestMapping(value = {"addressPostData"})
    public ModelAndView addressPostData( @ModelAttribute(value="addressForm") AddressForm addressForm,
                                         @ModelAttribute(value="addressForm2") AddressForm addressForm2,
                                        ModelAndView modelAndView, HttpSession httpSession) {
        System.out.println("addressForm2 getParent1Id  " + addressForm2.getParent1Id());
        System.out.println("addressForm2 getParent2Id  " + addressForm2.getParent2Id());
        System.out.println("addressForm1 getParent1Id  " + addressForm.getParent1Id());
        System.out.println("addressForm1 getParent2Id  " + addressForm.getParent2Id());


        Long parentId = null;
        System.out.println("httpSession.getAttribute(\"parentId\") " +httpSession.getAttribute("parent1Id"));
        AddressForm currentAddressForm = null;

        boolean hasEkatte = false;
        Address address = null;
        if (addressForm2 != null && addressForm2.getParent2Id() != null) {
            currentAddressForm = addressForm2;
System.out.println("ON addressForm2 ");
            parentId =  (Long) httpSession.getAttribute("parent2Id");

            if (addressForm2.getAddress2() != null && addressForm2.getAddress2().getSettlementPlace() != null
                    && addressForm2.getAddress2().getSettlementPlace().getEkatte() != null) {
                hasEkatte = true;
            }

            address = addressForm2.getAddress2();
        } else {
            currentAddressForm = addressForm;
            parentId =  (Long) httpSession.getAttribute("parent1Id");
            System.out.println("1 addressForm.getAddress() != null " + (addressForm.getAddress() != null));
            System.out.println("1 addressForm.getAddress().getSettlementPlace() != null  " + (addressForm.getAddress().getSettlementPlace() != null));
            System.out.println("1 addressForm.getAddress().getSettlementPlace().getEkatte() != null " + ( addressForm.getAddress().getSettlementPlace().getEkatte() != null));
            if (addressForm.getAddress() != null && addressForm.getAddress().getSettlementPlace() != null
                    && addressForm.getAddress().getSettlementPlace().getEkatte() != null) {
                hasEkatte = true;
            }

            address = addressForm.getAddress();
        }
        System.out.println("ADDRESS ID address.getId() " + address.getId());

        System.out.println("IN addressPostData httpsession address about " + httpSession.getAttribute("addressAbout"));
//        System.out.println("addressForm.getAddress() != null " + addressForm.getAddress() != null);
//        System.out.println("addressForm.getAddress().getSettlementPlace() != null " + addressForm.getAddress().getSettlementPlace() != null);
//        System.out.println("addressForm.getAddress().getSettlementPlace().getEkatte() != null " + addressForm.getAddress().getSettlementPlace().getEkatte() != null);
        System.out.println("hasEkatte " + hasEkatte + "parentId " + parentId  );
        if (hasEkatte) {
            String addressAbout = (String) httpSession.getAttribute("addressAbout");

            System.out.println("IN addressPostData httpsession address about " + httpSession.getAttribute("addressAbout"));

            // Записваме адреса
            Address savedAddress = addressService.createOrUpdateAddress(address);
            System.out.println("address id >>>>>>>  " + address.getId());
            System.out.println("savedAddress id >>>>>>>  " + savedAddress.getId());
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
                        System.out.println("addressForm.getStudentId() " + addressForm.getStudentId());
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
                        //modelAndView = new ModelAndView("redirect:/school");
                        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));
                    }
                }
            }
        }
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"regionPostData"})
    public ModelAndView regionPostData(@RequestParam(value = "element", required = false) String element,
                                       @RequestBody String value, HttpSession httpSession) {
        //През дропдаун value имаме кода на областта
        System.out.println("element " + element);
        if ("2".equals(element)) {
            httpSession.setAttribute("regionCode2", value);
            System.out.println("element 222222222" + element);
            httpSession.setAttribute("regionCode", null);
        } else {
            httpSession.setAttribute("regionCode", value);
            httpSession.setAttribute("regionCode2", null);
        }
        // Връщаме "redirect" в зависимост от страницата, от кояйто сме извикали смяна на областта
        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"municipalitiesPostData"})
    public ModelAndView municipalitiesPostData(@RequestParam(value = "element", required = false) String element,
                                               @RequestBody String value, HttpSession httpSession) {
        System.out.println("element :::::: " + element);
        System.out.println("value :::::: " + value);

        //През дропдаун value имаме кода на общината
        if ("2".equals(element)) {
            httpSession.setAttribute("municipalityCode2", value);
            httpSession.setAttribute("municipalityCode", null);
            System.out.println("element 222222222" + element);
        } else {
            httpSession.setAttribute("municipalityCode", value);
            httpSession.setAttribute("municipalityCode2", null);
        }
        // Връщаме "redirect" в зависимост от страницата, от кояйто сме извикали смяна на общината
        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));
        return modelAndView;

    }



}


