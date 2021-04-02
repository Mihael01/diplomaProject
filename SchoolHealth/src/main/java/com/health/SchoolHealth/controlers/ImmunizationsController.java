package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.*;
import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.Immunization;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.services.AddressService;
import com.health.SchoolHealth.services.HealthConditionService;
import com.health.SchoolHealth.services.ImmunizationService;
import com.health.SchoolHealth.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.health.SchoolHealth.util.RepositoryUtil.iterableToList;


@RestController
public class ImmunizationsController {

    //Services
    @Autowired
    ImmunizationService immunizationService;

    @Autowired
    HealthConditionService healthConditionService;

    //Model
    ModelAndView modelAndView;

    // Forms
    ImmunizationForm immunizationForm = new ImmunizationForm();

    @GetMapping
    @RequestMapping(value = {"/immunization"})
    public ModelAndView getImmunizationData(HttpSession httpSession) {
        System.out.println("in immunization");
        modelAndView = new ModelAndView("immunization");

        Long healthConditionId = (Long) httpSession.getAttribute("healthConditionId");
        Long immunizationId = (Long) httpSession.getAttribute("immunizationId");
        if (immunizationId != null) {
            immunizationForm.setImmunization(immunizationService.findImmunizationById(immunizationId));
        } else {
            immunizationForm.setImmunization(new Immunization());
        }
        System.out.println("healthConditionId: " + healthConditionId);
        System.out.println("immunizationId: " + immunizationId);
        httpSession.setAttribute("immunizationId", null);

        if (healthConditionId != null) {
            immunizationForm.setAllImmunizations(immunizationService.getAllImmunizationOfStudent(healthConditionId));
        } else {
            immunizationForm.setAllImmunizations(new ArrayList<>());
        }

        modelAndView.addObject("immunizationForm", immunizationForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"immunizationPostData"})
    public ModelAndView immunizationPostData(@ModelAttribute("immunizationForm") ImmunizationForm immunizationForm, HttpSession httpSession) {

        System.out.println("immunization name: "+ immunizationForm.getImmunization().getImmunizationName());
        immunizationForm.getImmunization().setHealthCondition(healthConditionService.getHealthConditionById((Long) httpSession.getAttribute("healthConditionId")));
        immunizationService.createOrUpdateImmunization(immunizationForm.getImmunization());
        httpSession.setAttribute("immunizationId", null);
        modelAndView = new ModelAndView("redirect:/immunization");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"editImmunizationData"})
    public ModelAndView editImmunizationData(@RequestParam(value = "immunizationId", required = false) Long immunizationId, HttpSession httpSession) {

        System.out.println("editImmunizationData immunizationId = " + immunizationId);
//        immunizationForm.getImmunization().setHealthCondition(healthConditionService.getHealthConditionById((Long) httpSession.getAttribute("healthConditionId")));
//        immunizationService.createOrUpdateImmunization(immunizationForm.getImmunization());
        httpSession.setAttribute("immunizationId", immunizationId);
        modelAndView = new ModelAndView("redirect:/immunization");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deleteImmunizationData"})
    public ModelAndView deleteImmunizationData(@RequestParam(value = "immunizationId", required = false) Long immunizationId, HttpSession httpSession) {

        System.out.println("DELETE  immunizationId = " + immunizationId);

        immunizationService.deleteImmunizationById(immunizationId);


        modelAndView = new ModelAndView("redirect:/immunization");

        return modelAndView;
    }
}


