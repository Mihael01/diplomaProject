package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.ButtonsForm;
import com.health.SchoolHealth.controlers.formPOJOs.LzpkForm;
import com.health.SchoolHealth.model.entities.Lzpk;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.FormUtil;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;


@RestController
public class lzpkController {

    // Services
    @Autowired
    private LzpkService lzpkService;

    @Autowired
    private SexTypeService sexTypeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolService schoolService;

    // Models
    ModelAndView modelAndView;

    // Forms
    LzpkForm lzpkForm = new LzpkForm();

    AddressForm addressForm = new AddressForm();

    ButtonsForm buttonsForm = new ButtonsForm();


    @GetMapping
    @RequestMapping(value = { "/lzpk", "/lzpk/{studentId}"}, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getLzpkData(HttpSession httpSession, @PathVariable(value="studentId", required = false) Long studentIdParam ) {

        modelAndView = new ModelAndView("lzpk");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode) || UserType.SPORT_TEACHER.getCode().equals(userTypeCode)) {

            System.out.println("studentId::param :::::: " + studentIdParam);
            System.out.println("(Long) httpSession.getAttribute(\"studentId\")::param :::::: " + httpSession.getAttribute("studentId"));
            // Ученик
//        httpSession.setAttribute("studentId", 2L); //Remove hardcode
            Long studentId = null;
            if (studentIdParam != null) {
                try {
                    studentId = Long.valueOf(studentIdParam);
                    httpSession.setAttribute("studentId", studentIdParam);
                } catch (NumberFormatException ex) {

                }
            } else if ((Long) httpSession.getAttribute("studentId") != null) {
                studentId = (Long) httpSession.getAttribute("studentId");
            }

            System.out.println(" httpSession.getAttribute(\"studentId\" " + httpSession.getAttribute("studentId"));
//
//        if (studentId != null) {
//            System.out.println("(studentId != null) " + (studentId != null));
//            Student foundStudent = studentService.findStudentWithLzpkById(studentId);
//            if (foundStudent != null) {
//                System.out.println("foundStudent != null " + (foundStudent != null));
//                lzpkForm.setStudent(foundStudent);
//            } else {
//                System.out.println("foundStudent == null " );
//                //Нов ученик
//                lzpkForm.setStudent(new Student());
//                // Нова лична здравно профилактична карта (ЛЗПК)
//                lzpkForm.getStudent().setLzpk(new Lzpk());
//            }
//        }


            if (studentId != null) {
                buttonsForm.setHasNotStudent(false);
                System.out.println("(studentId != null) " + (studentId != null));
                Student foundStudent = studentService.findStudentWithLzpkById(studentId);
                if (foundStudent != null) {
                    System.out.println("foundStudent != null " + (foundStudent != null));
                    lzpkForm.setStudent(foundStudent);
                } else {
                    System.out.println("foundStudent == null ");
                    //Нов ученик
                    lzpkForm.setStudent(new Student());
                    // Нова лична здравно профилактична карта (ЛЗПК)
                    lzpkForm.getStudent().setLzpk(new Lzpk());
                }
            } else {
                buttonsForm.setHasNotStudent(true);
                System.out.println("foundStudent == null ");
                //Нов ученик
                lzpkForm.setStudent(new Student());
                // Нова лична здравно профилактична карта (ЛЗПК)
                lzpkForm.getStudent().setLzpk(new Lzpk());
            }

            // Адрес
            Long addressId = null;
            if (lzpkForm.getStudent() != null && lzpkForm.getStudent().getStudentAddress() != null
                    && lzpkForm.getStudent().getStudentAddress().getId() != null) {
                addressId = lzpkForm.getStudent().getStudentAddress().getId();
            }

            addressForm.setIsOwnerNotPresent(lzpkForm.getStudent() == null || lzpkForm.getStudent().getId() == null);

            FormUtil.setAddressForm(FormUtil.ADDRESS_ABOUT_STUDENT, addressId, null,
                    addressService, addressForm, httpSession);

            httpSession.setAttribute("redirect", "redirect:/lzpk");

            lzpkForm.setSexNomenclature(sexTypeService.findAllSexTypes());
            lzpkForm.setClassesNomenclature(FormUtil.getClassesNomenclature());
            lzpkForm.setClassLettersNomenclature(FormUtil.getClassLettersNomenclature());

            modelAndView.addObject("lzpkForm", lzpkForm);
            modelAndView.addObject("addressForm", addressForm);
            modelAndView.addObject("buttonsForm", buttonsForm);
            System.out.println("VIEW " + modelAndView.getViewName());

            //Добавяме видимост на бутони спрямо роли
            System.out.println("userTypeCode in lzpk " + userTypeCode);
            if (!UserType.SPORT_TEACHER.getCode().equals(userTypeCode)) {
                System.out.println("No SPORT_TEACHER");
                modelAndView.addObject("isStudentDataVisible", "true");
            }
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }
        return modelAndView;
    }

//    @PreAuthorize("securityService.hasLzpkAccess()")
//    public void checkPermission(HttpSession httpSession) {
//        System.out.println("permisions has checked");
//    }

//    @PostMapping
//    @RequestMapping(value = { "/lzpk/{studentId}"})
//    public ModelAndView lzpkRedirect(HttpSession httpSession, @PathVariable(value="studentId", required = false) String studentIdParam ) {
//        httpSession.setAttribute("studentId", studentIdParam);
//
//        modelAndView = new ModelAndView("redirect:/lzpk");
//        return modelAndView;
//    }
    @PostMapping
    @RequestMapping(value = {"lzpkPostData"})
    public ModelAndView lzpkPostData(@ModelAttribute("lzpkForm") LzpkForm lzpkForm, HttpSession httpSession) {

        System.out.println("getSexTypeCode" + lzpkForm.getStudent().getSexType().getSexTypeCode());
        Student foundStudent = studentService.findStudentById((Long) httpSession.getAttribute("studentId"));
        if (foundStudent != null) {
            foundStudent.setLzpk(lzpkForm.getStudent().getLzpk());
            foundStudent.setFirstName(lzpkForm.getStudent().getFirstName());
            foundStudent.setMiddleName(lzpkForm.getStudent().getMiddleName());
            foundStudent.setLastName(lzpkForm.getStudent().getLastName());
            foundStudent.setBirthDate(lzpkForm.getStudent().getBirthDate());
            foundStudent.setBirthPlace(lzpkForm.getStudent().getBirthPlace());
            foundStudent.setEgn(lzpkForm.getStudent().getEgn());
            foundStudent.setTelephoneNumber(lzpkForm.getStudent().getTelephoneNumber());
            foundStudent.setClass_(lzpkForm.getStudent().getClass_());
            foundStudent.setClassLetter(lzpkForm.getStudent().getClassLetter());
            foundStudent.setSexType(sexTypeService.getSexTypeByCode(lzpkForm.getStudent().getSexType().getSexTypeCode()));
        } else {
            Integer schoolId = (Integer) httpSession.getAttribute("schoolId");
            lzpkForm.getStudent().setSchool(schoolService.findSchoolById(schoolId));
        }

        System.out.println("DATE getBirthDate " + lzpkForm.getStudent().getBirthDate());
        Lzpk savedLzpk = null;
        if(foundStudent != null) {
            savedLzpk = lzpkService.createOrUpdateLzpk(foundStudent.getLzpk());
        } else {
            savedLzpk = lzpkService.createOrUpdateLzpk(lzpkForm.getStudent().getLzpk());
        }

        lzpkForm.getStudent().setLzpk(savedLzpk);

        Student student = studentService.createOrUpdateStudent(foundStudent != null? foundStudent : lzpkForm.getStudent());
        System.out.println("!!!!!!!!!!!!1 "+  student.getId());
        httpSession.setAttribute("studentId", student.getId());
        System.out.println("(String) httpSession.getAttribute(\"redirect\") " + (String) httpSession.getAttribute("redirect"));
        modelAndView = new ModelAndView((String) httpSession.getAttribute("redirect"));

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/cleanSessionDataOnLzpkRedirect"})
    public ModelAndView cleanRegionAndMunicipalitySessionData(HttpSession httpSession) {

        httpSession.setAttribute("regionCode", null);
        httpSession.setAttribute("municipalityCode", null);
        modelAndView = new ModelAndView("redirect:/lzpk");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/cleanSessionDataOnParentsRedirect"})
    public ModelAndView cleanSessionDataOnParentsRedirect(HttpSession httpSession) {

        httpSession.setAttribute("regionCode", null);
        httpSession.setAttribute("municipalityCode", null);
        modelAndView = new ModelAndView("redirect:/parents");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/cleanSessionDataOnHomeRedirect"})
    public ModelAndView cleanSessionDataOnHomeRedirect(HttpSession httpSession) {
        System.out.println("in cleanSessionDataOnHomeRedirect ");
        httpSession.setAttribute("studentId", null);
        modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }
}


