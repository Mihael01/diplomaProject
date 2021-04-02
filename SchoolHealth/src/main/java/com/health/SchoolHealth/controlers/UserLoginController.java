package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.UserLoginForm;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class UserLoginController {

//  Services

//  ModelAndViews
    ModelAndView modelAndView;

//  Forms
    UserLoginForm userLoginnForm = new UserLoginForm();

    @GetMapping
    @RequestMapping(value = { "/user/login"})
    public ModelAndView getUserLoginData(HttpSession httpSession) {

        modelAndView = new ModelAndView("/user/login");
//        System.out.println("studentId w getStudentBaseDatadata" +  httpSession.getAttribute("studentId"));
//        Long studentId = (Long) httpSession.getAttribute("studentId");
//        System.out.println("studentId w getStudentBaseDatadata" + studentId);
//        if (studentId != null) {
//            studentBaseForm.setStudent(studentService.findStudentById(studentId));
//        } else {
//            studentBaseForm.setStudent(new Student());
//        }

        modelAndView.addObject("userLoginForm", userLoginnForm);

        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"userLoginPostData"})
    public ModelAndView userLoginPostData(
            @ModelAttribute("userLoginForm") @Valid UserLoginForm userLoginForm,
            HttpServletRequest request, Errors errors) {
        System.out.println("NAME " + userLoginForm.getUser().getFirstName());

        modelAndView = new ModelAndView("redirect:/user/login");

        return modelAndView;
    }

}


