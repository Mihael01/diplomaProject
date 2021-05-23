package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.model.entities.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@RestController
public class TestController {

//  Services

//  ModelAndViews
    ModelAndView modelAndView;

//  Forms
    User user = new User();

    @RequestMapping(value = {"/user/test"}, method = RequestMethod.GET)
    public ModelAndView getUserRegistrationData(ModelAndView modelAndView) {
        System.out.println("getUserRegistrationData");

        if (!modelAndView.hasView()) {
            modelAndView = new ModelAndView("/user/test");
        }

//        modelAndView.addObject("user", user);

        return modelAndView;
    }

}
