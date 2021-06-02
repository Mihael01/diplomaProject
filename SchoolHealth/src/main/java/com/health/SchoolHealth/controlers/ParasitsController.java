package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.ParasitsForm;
import com.health.SchoolHealth.model.entities.ParasitType;
import com.health.SchoolHealth.model.entities.StudentParasit;
import com.health.SchoolHealth.services.ParasitTypeService;
import com.health.SchoolHealth.services.StudentParasiteService;
import com.health.SchoolHealth.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;


@RestController
public class ParasitsController {

    // Services
    @Autowired
    private StudentParasiteService studentParasiteService;

    @Autowired
    private ParasitTypeService parasitTypeService;

    @Autowired
    private StudentService studentService;

    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    ParasitsForm parasitsForm = new ParasitsForm();

    @GetMapping
    @RequestMapping(value = {"parasits"})
    public ModelAndView getParasitsdata(HttpSession httpSession) {

        modelAndView = new ModelAndView("parasits");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode)) {
            parasitsForm.setParasiteTypes(parasitTypeService.findAllParasitTypes());
//

            Long studentId = (Long) httpSession.getAttribute("studentId");
            parasitsForm.setStudentParasites(studentParasiteService.getParasitesByStudent(studentId));
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }


        modelAndView.addObject("parasitsForm", parasitsForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"parasitsPostData"})
    public ModelAndView parasitsPostData(@ModelAttribute("parasitsForm") ParasitsForm parasitsForm, HttpSession httpSession) {

        List<StudentParasit> studentParasits = parasitsForm.getStudentParasites();

        List<String> parasitTypeCodes = new ArrayList<>();
        if (parasitsForm.getStudentParasites() != null) {
            List<ParasitType> parasitTypes = parasitsForm.getStudentParasites().stream().map(StudentParasit::getParasitType).collect(Collectors.toList());

            parasitTypeCodes = parasitTypes.stream().map(ParasitType::getParasitTypeCode).collect(Collectors.toList());
        }

        Long studentId = (Long) httpSession.getAttribute("studentId");
        //Правим проверка дали вече този паразит не е записан за ученика
        if (parasitsForm.getParasiteTypeCode() != null && !parasitTypeCodes.contains(parasitsForm.getParasiteTypeCode())) {
            StudentParasit newStudentParasit = new StudentParasit();
            newStudentParasit.setStudent(studentService.findStudentById(studentId));
            newStudentParasit.setParasitType(parasitTypeService.getParasitTypeByCode(parasitsForm.getParasiteTypeCode()));
            StudentParasit savedStudentParasit = studentParasiteService.createOrUpdateStudentParasit(newStudentParasit);
        }

        modelAndView = new ModelAndView("redirect:/parasits");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deleteSPData"})
    public ModelAndView deleteSPData(@RequestParam(value = "currentParasiteCode", required = false) String currentParasiteCode, HttpSession httpSession) {

        Long studentId = (Long) httpSession.getAttribute("studentId");
        List<StudentParasit> foundStudentParasites = studentParasiteService.getStudentParasitByParasiteCodeAndStudentId(currentParasiteCode, studentId);

        for (StudentParasit studentParasit : foundStudentParasites) {
            studentParasiteService.deleteGPById(studentParasit.getId());
        }

        modelAndView = new ModelAndView("redirect:/parasits");

        return modelAndView;
    }
}


