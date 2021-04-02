package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.ClassmatesForm;
import com.health.SchoolHealth.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class Analysis2SchemaController {

    // Services
    @Autowired
    private StudentService studentService;

    // Models
    ModelAndView modelAndView;

    // Forms
    ClassmatesForm classmatesForm = new ClassmatesForm();

    @GetMapping
    @RequestMapping(value = { "/analysis2"})
    public ModelAndView getClassmatesData(HttpSession httpSession) {

        modelAndView = new ModelAndView("analysis2");


        return modelAndView;
    }

}


