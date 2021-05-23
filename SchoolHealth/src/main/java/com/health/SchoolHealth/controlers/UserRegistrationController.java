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
public class UserRegistrationController {

//  Services

//  ModelAndViews
    ModelAndView modelAndView;

//  Forms
    User user = new User();

    @RequestMapping(value = {"/user/registration"}, method = RequestMethod.GET)
    public ModelAndView getUserRegistrationData(ModelAndView modelAndView) {
        System.out.println("getUserRegistrationData");

        if (!modelAndView.hasView()) {
            modelAndView = new ModelAndView("/user/registration");
        }

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/user/registration", method=RequestMethod.POST)
    public ModelAndView userRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelAndView modelAndView) {


        List<FieldError> fr = bindingResult.getFieldErrors();
        for (FieldError f : fr) {
            System.out.println("ERROR: " + f.getField());
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);

            System.out.println("VALIDATION ERROR");

            return modelAndView;
        }

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        }

        modelAndView.addObject("successfulSubmitOfRegistrationData", true);
//Да се съхранява във базата с хеширана парола
        return modelAndView;
    }
}
