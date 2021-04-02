package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.GPForm;
import com.health.SchoolHealth.services.GPService;
import com.health.SchoolHealth.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class AddictionsController {

    // Services
    @Autowired
    private GPService gpService;

    @Autowired
    private StudentService studentService;

    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    GPForm gpForm = new GPForm();

    @GetMapping
    @RequestMapping(value = {"addictions"})
    public ModelAndView getParasitsdata(HttpSession httpSession) {

        modelAndView = new ModelAndView("addictions");
//
//        // Запис в базата за личния лекар може да бъде създаден:
//        // I. При регистрация на личния лекар
//        // II. При запис на ученик от училищното медицинско лице, ако личния лекар не може да бъде намерен в базата данни по телефонен номер
//        // Телефонния номер на личния лекар е задължително поле; При регистрацията на личния лекар се прави проверка
//        // дали съществува лекар с такъв телефонен номер, ако съществува се актуализира записа
//
//        System.out.println("STUDENT ID " +  httpSession.getAttribute("studentId"));
//        Long studentId = (Long) httpSession.getAttribute("studentId");
//
//        // Училищно медицинско лице
//        Long gpId = gpIdParam.orElse(1L);
//
//        // Ако в системата се е логнал личният лекар, тогава го намираме по неговото id,
//        // но aко в системата се е логналo училищното медицинско лице, тогава GP се намира studentId (За случая, ако вече на ученика е записано GP)
//        GP gp = gpService.getGP(gpId);
//        GP gpOfStudent = gpService.getGpOfStudent(studentId);
//
//        if (gp != null) {
//            gpForm.setGp(gp);
//        }
//        else if (gpOfStudent != null) {
//            gpForm.setGp(new GP());
//        }
//        else {
//            gpForm.setGp(new GP());
//        }
//
//        modelAndView.addObject("gpForm", gpForm);
//
//        gpForm.setIsListOfStudentsVisible(isListOfStudentsVisible());
//
//        gpForm.setStudentsOfGP(gpService.getAllStudentsOfGP(gpId));

        return modelAndView;

    }
}


