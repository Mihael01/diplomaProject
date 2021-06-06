package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddictionsForm;
import com.health.SchoolHealth.model.entities.Addictions;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.AddictionsService;
import com.health.SchoolHealth.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;


@RestController
public class AddictionsController {

    // Services
    @Autowired
    private AddictionsService addictionsService;

    @Autowired
    private StudentService studentService;

    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    AddictionsForm addictionsForm = new AddictionsForm();

    @GetMapping
    @RequestMapping(value = {"addictions"})
    public ModelAndView getParasitsdata(HttpSession httpSession) {

        modelAndView = new ModelAndView("addictions");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode)) {

            Long studentId = (Long) httpSession.getAttribute("studentId");

            Addictions addictions = addictionsService.getAddictionsByStudentId(studentId);
            if(addictions != null && addictions.getId() != null) {
                addictionsForm.setAddictions(addictions);
            } else {
                addictionsForm.setAddictions(new Addictions());
            }
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        modelAndView.addObject("addictionsForm", addictionsForm);

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/addictionsPostData"})
    public ModelAndView addictionsPostData(@ModelAttribute("addictionsForm") AddictionsForm addictionsForm,
                                                HttpSession httpSession) {
        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode)) {
            System.out.println("addictionsForm.getAddictions()" + addictionsForm.getAddictions());
            Long studentId = (Long) httpSession.getAttribute("studentId");
            Student foundStudent = studentService.findStudentById(studentId);
            addictionsForm.getAddictions().setStudent(foundStudent);

            Addictions savedAddictions = addictionsService.createOrUpdateAddictions(addictionsForm.getAddictions());
            System.out.println("asavedAddictions " + savedAddictions);

        } else {
                modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }


        modelAndView = new ModelAndView("redirect:/addictions");
        return modelAndView;
    }
}


