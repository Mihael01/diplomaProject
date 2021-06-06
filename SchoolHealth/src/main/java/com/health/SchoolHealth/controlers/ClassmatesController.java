package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.ClassmatesForm;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.StudentService;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForStudentListData;


@RestController
public class ClassmatesController {

    // Services
    @Autowired
    private StudentService studentService;

    // Models
    ModelAndView modelAndView;

    // Forms
    ClassmatesForm classmatesForm = new ClassmatesForm();

    @GetMapping
    @RequestMapping(value = { "/classmates"})
    public ModelAndView getClassmatesData(HttpSession httpSession) {

        modelAndView = new ModelAndView("classmates");
        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForStudentListData.contains(userTypeCode)) {

            classmatesForm.setClassesNomenclature(FormUtil.getClassesNomenclature());
            classmatesForm.setClassLettersNomenclature(FormUtil.getClassLettersNomenclature());

            httpSession.getAttribute("class_");
            httpSession.getAttribute("classLetter");

            List<Student> classmates = studentService.findClassmatesByClassAndClassLetter((String) httpSession.getAttribute("class_"),
                    (String) httpSession.getAttribute("classLetter"), (Integer) httpSession.getAttribute("schoolId"));
            classmatesForm.setClassmates(classmates);

            modelAndView.addObject("classmatesForm", classmatesForm);
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"classmatesPostData"})
    public ModelAndView classmatesPostData(@RequestParam(value = "class_", required = false) String class_,
                                           @RequestParam(value = "classLetter", required = false) String classLetter,
            @ModelAttribute("classmateForm") ClassmatesForm classmatesForm, HttpSession httpSession) {

        httpSession.setAttribute("class_", classmatesForm.getClass_());
        httpSession.setAttribute("classLetter", classmatesForm.getClassLetter());

        modelAndView = new ModelAndView("redirect:/classmates");
        modelAndView.addObject("classmatesForm", classmatesForm);

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/cleanSessionDataOnStudentRedirect"})
    public ModelAndView cleanSessionDataOnStudentRedirect(@RequestParam(value = "lzpkNumber", required = false) String lzpkNumber, HttpSession httpSession) {
        httpSession.setAttribute("class_", null);
        httpSession.setAttribute("classLetter", null);
        modelAndView = new ModelAndView("redirect:/lzpkNumber");
        return modelAndView;
    }


}


