package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.CreateSMForm;
import com.health.SchoolHealth.controlers.formPOJOs.CreateSTForm;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.SportTeacher;
import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.SchoolMedicsService;
import com.health.SchoolHealth.services.SportTeacherService;
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
public class CreateSTController {

    // Services
    @Autowired
    private SportTeacherService sportTeacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;


    // ModelAndViews
    ModelAndView modelAndView;


    // Forsm
    CreateSTForm createSTForm = new CreateSTForm();

    @RequestMapping(value ={"createst"}, method=RequestMethod.GET)
    public ModelAndView getSportTeachersData(HttpSession httpSession) {

        modelAndView = new ModelAndView("createst");

        Long stId = (Long) httpSession.getAttribute("stId");
        if (stId != null) {
            createSTForm.setSportTeacher(sportTeacherService.getSportTeacher(stId));
        } else {
            createSTForm.setSportTeacher(new SportTeacher());
        }

        httpSession.setAttribute("stId", null);

        List<SportTeacher> allSportTeachers = sportTeacherService.findAllSportTeachers();
        createSTForm.setAllSportTeachers(allSportTeachers);

        List<String> tns = allSportTeachers.stream().map(SportTeacher::getSportTeacherTelephoneNumber).collect(Collectors.toList());
        createSTForm.setTelephoneNumbers(tns);

        modelAndView.addObject("createSTForm", createSTForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"createSTPostData"})
    public ModelAndView createSTPostData(@ModelAttribute("createSTForm") CreateSTForm createSTForm, HttpSession httpSession) {

        User savedUser = null;
        if (createSTForm.getSportTeacher() != null && createSTForm.getSportTeacher().getUser() != null
                && createSTForm.getSportTeacher().getUser().getEmail() != null) {
            if (createSTForm.getSportTeacher().getUser().getId() == null) {
                // Задаваме стойности в полетата на потребителя, за случаите, когато на учителя по ФВС все още не е създаден акаунт.
                ControllerUtil.setUserData(createSTForm.getSportTeacher().getUser(), UserType.SPORT_TEACHER.getCode(), userService, mailSender);
                System.out.println("e-mail: " + createSTForm.getSportTeacher().getUser().getEmail());
                savedUser = userService.createOrUpdateUser(createSTForm.getSportTeacher().getUser());

                //Ако Потребителя не е съществувал като запис в базата,
                // слагаме вече записания потребител като поле на учителя по ФВС (със съществуващо вече id)
                createSTForm.getSportTeacher().setUser(savedUser);
            } else {
                User foundUser = userService.findUser(createSTForm.getSportTeacher().getUser().getId());
                // Актуализираме имейла на учителя по ФВС
                foundUser.setEmail(createSTForm.getSportTeacher().getUser().getEmail());
                savedUser = userService.createOrUpdateUser(foundUser);
            }
        }

         SportTeacher sportTeacher = sportTeacherService.createOrUpdateSportTeacher(createSTForm.getSportTeacher());


        modelAndView = new ModelAndView("redirect:/createst");
        return modelAndView;
    }



    @PostMapping
    @RequestMapping(value = {"editSTData"})
    public ModelAndView editSMData(@RequestParam(value = "stId", required = false) Long stId, HttpSession httpSession) {

        System.out.println("editSTData stId = " + stId);

        httpSession.setAttribute("stId", stId);
        modelAndView = new ModelAndView("redirect:/createst");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deleteSTData"})
    public ModelAndView deleteSTData(@RequestParam(value = "stId", required = false) Long stId, HttpSession httpSession) {

        System.out.println("DELETE  stId = " + stId);
        SportTeacher foundSportTeacher = sportTeacherService.getSportTeacher(stId);

        sportTeacherService.deleteSportTeacherById(stId);

        if (foundSportTeacher.getUser() != null && foundSportTeacher.getUser().getId() != null) {
            userService.deleteUserById(foundSportTeacher.getUser().getId());
        }

        modelAndView = new ModelAndView("redirect:/createst");

        return modelAndView;
    }


    @RequestMapping(value = {"createst"}, method=RequestMethod.POST)
    public ModelAndView searchSTData(@RequestParam(value = "telephoneNumber", required = false) String telephoneNumber, HttpSession httpSession) {
        String trimedStTelephoneNumber = telephoneNumber.replaceAll(" ", "");
        System.out.println("search  smTelephoneNumber = " + trimedStTelephoneNumber);
//        SM foundSM = schoolMedicsService.getSM(smId);
//
//        schoolMedicsService.deleteSMById(smId);
//
//        if (foundSM.getUser() != null && foundSM.getUser().getId() != null) {
//            userService.deleteUserById(foundSM.getUser().getId());
//        }

        createSTForm.setAllSportTeachers(sportTeacherService.findSportTeacherByTelephoneNumber(trimedStTelephoneNumber));
System.out.println("SIZE "+ createSTForm.getAllSportTeachers().size());
//        modelAndView = new ModelAndView("redirect:/createst");

        return modelAndView;
    }
}


