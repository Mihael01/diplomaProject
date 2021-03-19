package com.health.SchoolHealth.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class HomeController {
    // ModelAndViews
    ModelAndView modelAndView;

    @GetMapping
    @RequestMapping(value ={"/", "home"})
    public ModelAndView getIndexPage(HttpSession httpSession) {
        System.out.println("in getIndexPage  GETTER");

        httpSession.setAttribute("studentId", null);
        System.out.println("HOME studentId " + httpSession.getAttribute("studentId"));

        modelAndView = new ModelAndView("home");
        return modelAndView;
    }

}


