package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.CreateSMForm;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.SchoolMedicsService;
import com.health.SchoolHealth.services.UserService;
import com.health.SchoolHealth.util.ControllerUtil;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CreateSMController {

    // Services
    @Autowired
    private SchoolMedicsService schoolMedicsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;


    // ModelAndViews
    ModelAndView modelAndView;


    // Forsm
    CreateSMForm createSMForm = new CreateSMForm();

    @RequestMapping(value ={"createsm"}, method=RequestMethod.GET)
    public ModelAndView getSchoolMedicsData(HttpSession httpSession) {

        modelAndView = new ModelAndView("createsm");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));
        if (UserType.SYS_ADMIN.getCode().equals(userTypeCode) || UserType.SCHOOL_ADMIN.getCode().equals(userTypeCode)) {
            Long smId = (Long) httpSession.getAttribute("smId");
            if (smId != null) {
                createSMForm.setSchoolMedic(schoolMedicsService.getSchoolMedic(smId));
            } else {
                createSMForm.setSchoolMedic(new SchoolMedics());
            }

            httpSession.setAttribute("smId", null);

            List<SchoolMedics> allSchoolMedics = schoolMedicsService.findAllSchoolMedics();
            createSMForm.setAllSchoolMedics(allSchoolMedics);

            List<String> tns = allSchoolMedics.stream().map(SchoolMedics::getSchoolMedicsTelephoneNumber).collect(Collectors.toList());
            createSMForm.setTelephoneNumbers(tns);
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }


        modelAndView.addObject("createSMForm", createSMForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"createSMPostData"})
    public ModelAndView createSMPostData(@ModelAttribute("createSMForm") CreateSMForm createSMForm, HttpSession httpSession) {


        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));
        if (UserType.SYS_ADMIN.getCode().equals(userTypeCode) || UserType.SCHOOL_ADMIN.getCode().equals(userTypeCode)) {
        User savedUser = null;
            if (createSMForm.getSchoolMedic() != null && createSMForm.getSchoolMedic().getUser() != null
                    && createSMForm.getSchoolMedic().getUser().getEmail() != null) {
                if (createSMForm.getSchoolMedic().getUser().getId() == null) {
                    // Задаваме стойности в полетата на потребителя, за случаите, когато на училищния медик все още не е създаден акаунт.
                    ControllerUtil.setUserData(createSMForm.getSchoolMedic().getUser(), UserType.SCHOOL_MEDIC.getCode(), userService, mailSender);
                    System.out.println("e-mail: " + createSMForm.getSchoolMedic().getUser().getEmail());
                    savedUser = userService.createOrUpdateUser(createSMForm.getSchoolMedic().getUser());

                    //Ако Потребителя не е съществувал като запис в базата,
                    // слагаме вече записания потребител като поле на училищен медик (със съществуващо вече id)
                    createSMForm.getSchoolMedic().setUser(savedUser);
                } else {
                    User foundUser = userService.findUser(createSMForm.getSchoolMedic().getUser().getId());
                    // Актуализираме имейла на медикa
                    foundUser.setEmail(createSMForm.getSchoolMedic().getUser().getEmail());
                    savedUser = userService.createOrUpdateUser(foundUser);
                }
            }

            SchoolMedics schoolMedic = schoolMedicsService.createOrUpdateSchoolMedic(createSMForm.getSchoolMedic());

        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        modelAndView = new ModelAndView("redirect:/createsm");
        return modelAndView;
    }



    @PostMapping
    @RequestMapping(value = {"editSMData"})
    public ModelAndView editSMData(@RequestParam(value = "smId", required = false) Long smId, HttpSession httpSession) {

        System.out.println("editSMData smId = " + smId);

        httpSession.setAttribute("smId", smId);
        modelAndView = new ModelAndView("redirect:/createsm");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deleteSMData"})
    public ModelAndView deleteSMData(@RequestParam(value = "smId", required = false) Long smId, HttpSession httpSession) {

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));
        if (UserType.SYS_ADMIN.getCode().equals(userTypeCode) || UserType.SCHOOL_ADMIN.getCode().equals(userTypeCode)) {

            System.out.println("DELETE  smId = " + smId);
            SchoolMedics foundSchoolMedic = schoolMedicsService.getSchoolMedic(smId);

            schoolMedicsService.deleteSchoolMedicById(smId);

            if (foundSchoolMedic.getUser() != null && foundSchoolMedic.getUser().getId() != null) {
                userService.deleteUserById(foundSchoolMedic.getUser().getId());
            }

        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }


    modelAndView = new ModelAndView("redirect:/createsm");

        return modelAndView;
    }


    @RequestMapping(value = {"createsm"}, method=RequestMethod.POST)
    public ModelAndView searchSMData(@RequestParam(value = "telephoneNumber", required = false) String telephoneNumber, HttpSession httpSession) {
        String trimedSmTelephoneNumber = telephoneNumber.replaceAll(" ", "");
        System.out.println("search  smTelephoneNumber = " + trimedSmTelephoneNumber);
//        SM foundSM = schoolMedicsService.getSM(smId);
//
//        schoolMedicsService.deleteSMById(smId);
//
//        if (foundSM.getUser() != null && foundSM.getUser().getId() != null) {
//            userService.deleteUserById(foundSM.getUser().getId());
//        }

        createSMForm.setAllSchoolMedics(schoolMedicsService.findSchoolMedicByTelephoneNumber(trimedSmTelephoneNumber));
System.out.println("SIZE "+ createSMForm.getAllSchoolMedics().size());
//        modelAndView = new ModelAndView("redirect:/createsm");

        return modelAndView;
    }
}


