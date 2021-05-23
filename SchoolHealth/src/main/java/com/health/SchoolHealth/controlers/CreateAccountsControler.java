package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class CreateAccountsControler {

//  Services
    @Autowired
    UserService userService;

//  ModelAndViews
    ModelAndView modelAndView;

//  Forms
    User user = new User();

    @RequestMapping(value = {"/user/createaccounts"}, method = RequestMethod.GET)
    public ModelAndView getUserRegistrationData(ModelAndView modelAndView) {
        System.out.println("getUserRegistrationData");

        modelAndView = new ModelAndView("/user/createaccounts");
//        modelAndView.addObject("user", user);
        List<User> userParents = userService.findAllInactiveParentsBySchool(6);
        for(User user:userParents) {
//            System.out.println("user " + user.getUserCode() + "id " + user.getId() + "name " + user.getParentUser().getParentName());
        }
//да се премести връзката между таблицитие усер и частните в частните, заради извличането на данни????
        return modelAndView;
    }

}
