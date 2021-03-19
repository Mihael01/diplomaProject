package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.ClassmatesForm;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.StudentService;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


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

        classmatesForm.setClassesNomenclature(FormUtil.getClassesNomenclature());
        classmatesForm.setClassLettersNomenclature(FormUtil.getClassLettersNomenclature());

        httpSession.getAttribute("class_");
        httpSession.getAttribute("classLetter");

        List<Student> classmates = studentService.findClassmatesByClassAndClassLetter((String) httpSession.getAttribute("class_"),
                (String) httpSession.getAttribute("classLetter"));
        classmatesForm.setClassmates(classmates);
        System.out.println("IN GET classmates");
        if (classmates != null) {
            for (Student student : classmates) {
                System.out.println("IN GET student name " + student.getFirstName());
            }
        }

        modelAndView.addObject("classmatesForm", classmatesForm);

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"classmatesPostData"})
    public ModelAndView classmatesPostData(@RequestParam(value = "class_", required = false) String class_,
                                           @RequestParam(value = "classLetter", required = false) String classLetter,
            @ModelAttribute("classmateForm") ClassmatesForm classmatesForm, HttpSession httpSession) {

        System.out.println("class_ " + class_);
        System.out.println("classLetter " + classLetter);
        System.out.println("form.classLetter " + classmatesForm.getClassLetter());
        System.out.println("form.class_ " + classmatesForm.getClass_());
        httpSession.setAttribute("class_", classmatesForm.getClass_());
        httpSession.setAttribute("classLetter", classmatesForm.getClassLetter());
//
//        List<Student> classmates = studentService.findClassmatesByClassAndClassLetter(classmatesForm.getClass_(), classmatesForm.getClassLetter());
//
//        for(Student student : classmates){
//            System.out.println("student name "+ student.getFirstName());
//        }
        System.out.println("'''''''''''''''''''''''''''''''");
//        httpSession.setAttribute("studentId", student.getId());
//        System.out.println("(String) httpSession.getAttribute(\"redirect\") " + (String) httpSession.getAttribute("redirect"));
//
//        classmatesForm.setClassmates(classmates);
        modelAndView = new ModelAndView("redirect:/classmates");
        modelAndView.addObject("classmatesForm", classmatesForm);

        return modelAndView;
    }

}


