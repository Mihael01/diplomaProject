package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolMedicForm;
import com.health.SchoolHealth.controlers.formPOJOs.UserRegistrationForm;
import com.health.SchoolHealth.model.entities.*;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class UserRegistrationController {

//  Services

//  ModelAndViews
    ModelAndView modelAndView;

//  Forms
    UserRegistrationForm userRegistrationForm = new UserRegistrationForm();

    @GetMapping
    @RequestMapping(value = { "/user/registration"})
    public ModelAndView getUserRegistrationData(HttpSession httpSession) {

        modelAndView = new ModelAndView("/user/registration");
//        System.out.println("studentId w getStudentBaseDatadata" +  httpSession.getAttribute("studentId"));
//        Long studentId = (Long) httpSession.getAttribute("studentId");
//        System.out.println("studentId w getStudentBaseDatadata" + studentId);
//        if (studentId != null) {
//            studentBaseForm.setStudent(studentService.findStudentById(studentId));
//        } else {
//            studentBaseForm.setStudent(new Student());
//        }

        userRegistrationForm.setUser(new User());
        modelAndView.addObject("userRegistrationForm", userRegistrationForm);

        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"userRegistrationPostData"})
    public ModelAndView userRegistrationPostData(
            @ModelAttribute("userRegistrationForm") @Valid UserRegistrationForm userRegistrationForm,
            HttpServletRequest request, Errors errors) {
        System.out.println("NAME " + userRegistrationForm.getUser().getFirstName());

        modelAndView = new ModelAndView("redirect:/user/registration");

        return modelAndView;
    }

}
