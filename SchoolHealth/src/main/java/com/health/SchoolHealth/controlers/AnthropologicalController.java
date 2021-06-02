package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AnthropologicalForm;
import com.health.SchoolHealth.controlers.formPOJOs.StudentBaseForm;
import com.health.SchoolHealth.model.entities.AnthropologicalIndicators;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.AnthropologicalService;
import com.health.SchoolHealth.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;


@RestController
public class AnthropologicalController {

    // Services
    @Autowired
    private AnthropologicalService anthropologicalService;

    @Autowired
    private StudentService studentService;

    // ModelAndViews
    ModelAndView modelAndView;

    // Forms
    AnthropologicalForm anthropologicalForm = new AnthropologicalForm();

    @GetMapping
    @RequestMapping(value = { "/anthropological"})
    public ModelAndView getAnthropologicalData(HttpSession httpSession) {

        modelAndView = new ModelAndView("/anthropological");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode)) {
            System.out.println("studentId w getStudentBaseDatadata" +  httpSession.getAttribute("studentId"));
            Long studentId = (Long) httpSession.getAttribute("studentId");
    System.out.println("studentId w getStudentBaseDatadata" + studentId);
            if (studentId != null) {
                anthropologicalForm.setAnthropologicalIndicators(anthropologicalService.getAnthropologicalIndicatorsByStudentId(studentId));
            } else {
                anthropologicalForm.setAnthropologicalIndicators(new AnthropologicalIndicators());
            }

            modelAndView.addObject("anthropologicalForm", anthropologicalForm);
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }


        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"anthropologicalPostData"})
    public ModelAndView anthropologicalPostData(@ModelAttribute("anthropologicalForm") AnthropologicalForm anthropologicalForm,
                                            HttpSession httpSession ) {
        System.out.println("<<<<<<<< getBodyHeight = " + anthropologicalForm.getAnthropologicalIndicators().getBodyHeight());

        anthropologicalForm.getAnthropologicalIndicators().setStudent(studentService.findStudentById((Long) httpSession.getAttribute("studentId")));

        anthropologicalService.createOrUpdateAnthropologicalIndicators(anthropologicalForm.getAnthropologicalIndicators());




        modelAndView = new ModelAndView("redirect:/anthropological");

        return modelAndView;
    }

}


