package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.GPForm;
import com.health.SchoolHealth.controlers.formPOJOs.ImmunizationForm;
import com.health.SchoolHealth.controlers.formPOJOs.PhysicalCapacityForm;
import com.health.SchoolHealth.model.entities.PhysicalCapacity;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.GPService;
import com.health.SchoolHealth.services.PhysicalCapacityService;
import com.health.SchoolHealth.services.StudentService;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;


@RestController
public class PhysicalCapacityController {

    // Services
    @Autowired
    private PhysicalCapacityService physicalCapacityService;

    @Autowired
    private StudentService studentService;

    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    PhysicalCapacityForm physicalCapacityForm = new PhysicalCapacityForm();

    @GetMapping
    @RequestMapping(value = {"physicalcapacity"})
    public ModelAndView getParasitsdata(HttpSession httpSession) {

        modelAndView = new ModelAndView("physicalcapacity");
        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode) || UserType.SPORT_TEACHER.getCode().equals(userTypeCode)) {
        Long studentId = (Long) httpSession.getAttribute("studentId");
        PhysicalCapacity foundPhysicalCapacity = physicalCapacityService.getPhysicalCapacityDaoByStudentId(studentId);
        if (foundPhysicalCapacity != null) {
            physicalCapacityForm.setPhysicalCapacity(foundPhysicalCapacity);
        } else {
            physicalCapacityForm.setPhysicalCapacity(new PhysicalCapacity());
        }

        modelAndView.addObject("physicalCapacityForm" , physicalCapacityForm);
    } else {
        modelAndView.addObject("isReturnedErrorOnValidation", "true");
    }

        return modelAndView;

    }
//    physicalCapacityPostData
    @PostMapping
    @RequestMapping(value = {"physicalCapacityPostData"})
    public ModelAndView physicalCapacityPostData(@ModelAttribute("physicalCapacityForm") PhysicalCapacityForm physicalCapacityForm, HttpSession httpSession) {

        System.out.println("physicalCapacityForm strengthLeftHand: "+ physicalCapacityForm.getPhysicalCapacity().getStrengthLeftHand());
        Long studentId = (Long) httpSession.getAttribute("studentId");
        Student student = studentService.findStudentById(studentId);
        physicalCapacityForm.getPhysicalCapacity().setStudent(student);
        physicalCapacityService.createOrUpdatePhysicalCapacity(physicalCapacityForm.getPhysicalCapacity());
        modelAndView = new ModelAndView("redirect:/physicalcapacity");

        return modelAndView;
    }

}


