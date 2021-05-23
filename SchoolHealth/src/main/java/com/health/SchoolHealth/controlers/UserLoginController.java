package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.model.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.lang3.RandomStringUtils;

import javax.validation.Valid;
import java.util.List;


@RestController
public class UserLoginController {

//  Services

//  ModelAndViews
    ModelAndView modelAndView;

//  Forms
    User user = new User();

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ModelAndView getUserRegistrationData(ModelAndView modelAndView) {
        System.out.println("getUserRegistrationData");

        if (!modelAndView.hasView()) {
            modelAndView = new ModelAndView("/user/login");
        }

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/user/login", method=RequestMethod.POST)
    public ModelAndView userRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelAndView modelAndView) {

        System.out.println("user code : " + user.getUserCode());
        System.out.println("user password : " + user.getPassword());

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

//Да се прави сравнение с хешираната парола във базата
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println("encodePassword" + encodePassword);
        System.out.println(bCryptPasswordEncoder.matches(user.getPassword(),encodePassword));

        System.out.println("\n[a-zA-Z0-9]");

        for (int i = 0; i < 5; i++) {
            System.out.println(RandomStringUtils.randomAlphanumeric(10));
        }

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("mariya_rumenova_g@abv.bg");
//        message.setSubject("test email from spring");
//        message.setText("email from spring\n keep it");
//        mailSender.send(message);
        return modelAndView;
    }
}
