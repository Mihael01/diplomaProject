package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.ClassmatesForm;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.StudentService;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class AnalysisSchemaController {

    // Services
    @Autowired
    private StudentService studentService;

    // Models
    ModelAndView modelAndView;

    // Forms
    ClassmatesForm classmatesForm = new ClassmatesForm();

    @GetMapping
    @RequestMapping(value = { "/analysis"})
    public ModelAndView getClassmatesData(HttpSession httpSession) {

        modelAndView = new ModelAndView("analysis");


        return modelAndView;
    }

}


