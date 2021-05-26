package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.HomeForm;
import com.health.SchoolHealth.util.UserType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class HomeController {

    // ModelAndViews
    ModelAndView modelAndView;

    // Forms
    HomeForm homeForm = new HomeForm();

    @GetMapping
    @RequestMapping(value ={"/", "home"})
    public ModelAndView getIndexPage(HttpSession httpSession) {
        System.out.println("in getIndexPage  GETTER");

        httpSession.setAttribute("studentId", null);
        System.out.println("HOME studentId " + httpSession.getAttribute("studentId"));


        modelAndView = new ModelAndView("home");

        modelAndView.addObject("homeForm", homeForm);

        //Добавяме видимост на бутони спрямо роли
        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        System.out.println("HOME userTypeCode " + httpSession.getAttribute("userTypeCode"));
        if (UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {
            modelAndView.addObject("isCreateAdminButtonVisible", "true");
        }

        if (UserType.GP_ADMIN.getCode().equals(userTypeCode) || UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {
            modelAndView.addObject("isCreateGPButtonVisible", "true");
        }

        if (UserType.SCHOOL_ADMIN.getCode().equals(userTypeCode) || UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {
            modelAndView.addObject("isCreateSMandSTButtonVisible", "true");
        }

        if (UserType.SCHOOL_MEDIC.getCode().equals(userTypeCode) || UserType.SPORT_TEACHER.getCode().equals(userTypeCode)
                || UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {
            modelAndView.addObject("isSchoolButtonVisible", "true");
        }

        if (UserType.SCHOOL_MEDIC.getCode().equals(userTypeCode) || UserType.GP.getCode().equals(userTypeCode)
                || UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {
            modelAndView.addObject("isCreateLZPKButtonVisible", "true");
        }

        return modelAndView;
    }

}


