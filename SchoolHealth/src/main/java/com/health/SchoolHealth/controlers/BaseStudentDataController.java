package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.StudentBaseForm;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.SchoolService;
import com.health.SchoolHealth.services.StudentService;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;


@RestController
//@SessionAttributes("studentBaseForm")
public class BaseStudentDataController {
    // Services
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentService studentService;

    // ModelAndViews
    ModelAndView modelAndView;

    // Forms
    //Използваме @SessionAttributes("studentBaseForm"), за да запазим данните от studentBaseForm,
    // които не съшествуват в шаблона, но вече са записани в обекта в базата данни.
    StudentBaseForm studentBaseForm = new StudentBaseForm();

    @GetMapping
    @RequestMapping(value = { "/basedata"})
    public ModelAndView getStudentBaseDatadata(HttpSession httpSession) {

        modelAndView = new ModelAndView("/basedata");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode)) {
        System.out.println("studentId w getStudentBaseDatadata" +  httpSession.getAttribute("studentId"));
        Long studentId = (Long) httpSession.getAttribute("studentId");
System.out.println("studentId w getStudentBaseDatadata" + studentId);
        if (studentId != null) {
            studentBaseForm.setStudent(studentService.findStudentById(studentId));
        } else {
            studentBaseForm.setStudent(new Student());
        }

            modelAndView.addObject("studentBaseForm", studentBaseForm);
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"studentBasePostData"})
    public ModelAndView studentBasePostData(@ModelAttribute("studentBaseForm") StudentBaseForm studentBaseForm,
                                            HttpSession httpSession ) {
        System.out.println("<<<<<<<< getFamilyBurden = " + studentBaseForm.getStudent().getFamilyBurden());
System.out.println("studentBaseForm.getStudent() name " + studentBaseForm.getStudent().getFirstName());

        Student foundStudent = studentService.findStudentById((Long) httpSession.getAttribute("studentId"));
        foundStudent.setFamilyBurden(studentBaseForm.getStudent().getFamilyBurden());
        foundStudent.setPastIllnesses(studentBaseForm.getStudent().getPastIllnesses());
        foundStudent.setAllergies(studentBaseForm.getStudent().getAllergies());
        foundStudent.setBloodType(studentBaseForm.getStudent().getBloodType());
        foundStudent.setRh(studentBaseForm.getStudent().getRh());

        studentService.createOrUpdateStudent(foundStudent);




        modelAndView = new ModelAndView("redirect:/basedata");

        return modelAndView;
    }

}


