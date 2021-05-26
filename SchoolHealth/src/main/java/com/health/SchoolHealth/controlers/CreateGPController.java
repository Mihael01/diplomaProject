package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.CreateGPForm;
import com.health.SchoolHealth.model.entities.GP;
import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.GPService;
import com.health.SchoolHealth.services.UserService;
import com.health.SchoolHealth.util.ControllerUtil;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class CreateGPController {

    // Services
    @Autowired
    private GPService gpService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;


    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    CreateGPForm createGPForm = new CreateGPForm();

    @RequestMapping(value ={"creategp"}, method=RequestMethod.GET)
    public ModelAndView getSchooldata(HttpSession httpSession) {

        modelAndView = new ModelAndView("creategp");

        Long gpId = (Long) httpSession.getAttribute("gpId");
        if (gpId != null) {
            createGPForm.setGp(gpService.getGP(gpId));
        } else {
            createGPForm.setGp(new GP());
        }

        httpSession.setAttribute("gpId", null);

        createGPForm.setAllGPs(gpService.findAllGPs());

        modelAndView.addObject("createGPForm", createGPForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"createGPPostData"})
    public ModelAndView createGPPostData(@ModelAttribute("createGPForm") CreateGPForm createGPForm, HttpSession httpSession) {


        User savedUser = null;
        if (createGPForm.getGp() != null && createGPForm.getGp().getUser() != null && createGPForm.getGp().getUser().getEmail() != null) {
            if (createGPForm.getGp().getUser().getId() == null) {
                // Задаваме стойности в полетата на потребителя, за случаите, когато на личния лекар все още не е създаден акаунт.
                ControllerUtil.setUserData(createGPForm.getGp().getUser(), UserType.GP.getCode(), userService, mailSender);
                System.out.println("e-mail: " + createGPForm.getGp().getUser().getEmail());
                savedUser = userService.createOrUpdateUser(createGPForm.getGp().getUser());
System.out.println();
                //Ако Потребителя не е съществувал като запис в базата,
                // слагаме вече записания потребител като поле на gp (със съществуващо вече id)
                createGPForm.getGp().setUser(savedUser);
            } else {
                User foundUser = userService.findUser(createGPForm.getGp().getUser().getId());
                // Актуализираме имейла на GP
                foundUser.setEmail(createGPForm.getGp().getUser().getEmail());
                savedUser = userService.createOrUpdateUser(foundUser);
            }
        }

         GP gp = gpService.createOrUpdateGP(createGPForm.getGp());

System.out.println("gp id " + gp.getId());
        modelAndView = new ModelAndView("redirect:/creategp");
        return modelAndView;
    }



    @PostMapping
    @RequestMapping(value = {"editGPData"})
    public ModelAndView editGPData(@RequestParam(value = "gpId", required = false) Long gpId, HttpSession httpSession) {

        System.out.println("editImmunizationData immunizationId = " + gpId);
//        immunizationForm.getImmunization().setHealthCondition(healthConditionService.getHealthConditionById((Long) httpSession.getAttribute("healthConditionId")));
//        immunizationService.createOrUpdateImmunization(immunizationForm.getImmunization());
        httpSession.setAttribute("gpId", gpId);
        modelAndView = new ModelAndView("redirect:/creategp");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deleteGPData"})
    public ModelAndView deleteGPData(@RequestParam(value = "gpId", required = false) Long gpId, HttpSession httpSession) {

        System.out.println("DELETE  gpId = " + gpId);
        GP foundGP = gpService.getGP(gpId);

        gpService.deleteGPById(gpId);

        if (foundGP.getUser() != null && foundGP.getUser().getId() != null) {
            userService.deleteUserById(foundGP.getUser().getId());
        }

        modelAndView = new ModelAndView("redirect:/creategp");

        return modelAndView;
    }


    @RequestMapping(value = {"creategp"}, method=RequestMethod.POST)
    public ModelAndView searchGPData(@RequestParam(value = "gpTelephoneNumber", required = false) String gpTelephoneNumber, HttpSession httpSession) {
        String trimedGpTelephoneNumber = gpTelephoneNumber.replaceAll(" ", "");
        System.out.println("search  gpTelephoneNumber = " + trimedGpTelephoneNumber);
//        GP foundGP = gpService.getGP(gpId);
//
//        gpService.deleteGPById(gpId);
//
//        if (foundGP.getUser() != null && foundGP.getUser().getId() != null) {
//            userService.deleteUserById(foundGP.getUser().getId());
//        }

        createGPForm.setAllGPs(gpService.findGPByTelephoneNumber(trimedGpTelephoneNumber));
System.out.println("SIZE "+ createGPForm.getAllGPs().size());
//        modelAndView = new ModelAndView("redirect:/creategp");

        return modelAndView;
    }
}


