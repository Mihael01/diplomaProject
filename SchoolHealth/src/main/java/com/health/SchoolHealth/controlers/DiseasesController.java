package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.DiseasesForm;
import com.health.SchoolHealth.controlers.formPOJOs.ImmunizationForm;
import com.health.SchoolHealth.model.entities.Immunization;
import com.health.SchoolHealth.model.entities.StudentDiseasesAndAbnormalities;
import com.health.SchoolHealth.model.entities.StudentDispensaryObservation;
import com.health.SchoolHealth.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;


@RestController
public class DiseasesController {

    //Services
    @Autowired
    DiseasesAndAbnormTypeService diseasesAndAbnormTypeService;

    @Autowired
    DispensaryObservationIllnessTypeService dispensaryObservationIllnessTypeService;

    @Autowired
    StudentDiseasesAndAbnormService studentDiseasesAndAbnormService;

    @Autowired
    StudentDispensaryObservationService studentDispensaryObservationService;

    @Autowired
    HealthConditionService healthConditionService;

    @Autowired
    StudentService studentService;

    //Model
    ModelAndView modelAndView;

    // Forms
    DiseasesForm diseasesForm = new DiseasesForm();

    @GetMapping
    @RequestMapping(value = {"/diseases"})
    public ModelAndView getDiseasesData(HttpSession httpSession) {

        modelAndView = new ModelAndView("diseases");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode)) {
            Long healthConditionId = (Long) httpSession.getAttribute("healthConditionId");
            Long studentId = (Long) httpSession.getAttribute("studentId");

            System.out.println("studentId in getDiseasesData " + studentId);
            if (healthConditionId != null) {
                diseasesForm.setDiseasesAndAbnormTypes(diseasesAndAbnormTypeService.getAllDiseasesAndAbnormTypes());
                diseasesForm.setDispensaryObservationIllnessTypes(dispensaryObservationIllnessTypeService.getAllDispensaryObservationIllnessTypes());
                diseasesForm.setStudentDiseasesAndAbnormalities(studentDiseasesAndAbnormService.getAllDiseasesAndAbnormByStudentId(studentId));
                diseasesForm.setStudentDispensaryObservations(studentDispensaryObservationService.getAllDispensaryObservationByStudentId(studentId));
            } else {
                diseasesForm.setDiseasesAndAbnormTypes(new ArrayList<>());
                diseasesForm.setDispensaryObservationIllnessTypes(new ArrayList<>());
                diseasesForm.setStudentDiseasesAndAbnormalities(new ArrayList<>());
                diseasesForm.setStudentDispensaryObservations(new ArrayList<>());
            }
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        modelAndView.addObject("diseasesForm", diseasesForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"diseasesPostData"})
    public ModelAndView diseasesPostData(@ModelAttribute("diseasesForm") DiseasesForm diseasesForm, HttpSession httpSession) {

        // Ако не същестувава запис в свързващите таблици в базата между учениците и хроничните заболявания или аномалиите, го създаваме
        Long studentId = (Long) httpSession.getAttribute("studentId");
        Long count1 = 0L;

        if (diseasesForm.getDiseasesAndAbnormTypeCode() != null && !"Изберете".equals(diseasesForm.getDiseasesAndAbnormTypeCode()) && studentId != null) {
            count1 = studentDiseasesAndAbnormService.findByStudentIdAndDiseasesAndAbnormCode(studentId, diseasesForm.getDiseasesAndAbnormTypeCode());
            if (count1 == 0) {
                StudentDiseasesAndAbnormalities studentDiseasesAndAbnormalities = new StudentDiseasesAndAbnormalities();
                System.out.println("diseasesAndAbnormTypeService.getDiseasesAndAbnormTypeByCode(diseasesForm.getDiseasesAndAbnormTypeCode()) "
                        + diseasesAndAbnormTypeService.getDiseasesAndAbnormTypeByCode(diseasesForm.getDiseasesAndAbnormTypeCode()) + " <= " + diseasesForm.getDiseasesAndAbnormTypeCode());
                studentDiseasesAndAbnormalities.setDiseasesAndAbnormType(
                        diseasesAndAbnormTypeService.getDiseasesAndAbnormTypeByCode(diseasesForm.getDiseasesAndAbnormTypeCode()));
                studentDiseasesAndAbnormalities.setStudent(studentService.findStudentById(studentId));
                studentDiseasesAndAbnormService.createOrUpdateStudentDiseasesAndAbnormalities(studentDiseasesAndAbnormalities);
            }
        }

        Long count2 = 0L;
        if (diseasesForm.getDispensaryObservIllnessTypeCode() != null  && !"Изберете".equals(diseasesForm.getDispensaryObservIllnessTypeCode()) && studentId != null) {
            Integer schoolId = (Integer) httpSession.getAttribute("schoolId");
            count2 = studentDispensaryObservationService.getCountByStudentIdAndDispensaryObservIllnessTypeCode(studentId, diseasesForm.getDispensaryObservIllnessTypeCode(), schoolId);
            if (count2 == 0) {
                StudentDispensaryObservation studentDispensaryObservation = new StudentDispensaryObservation();
                studentDispensaryObservation.setDispensaryObservIllnessType(
                        dispensaryObservationIllnessTypeService.getDispensaryObservationIllnessTypeByCode(diseasesForm.getDispensaryObservIllnessTypeCode()));
                studentDispensaryObservation.setStudent(studentService.findStudentById(studentId));
                studentDispensaryObservationService.createOrUpdateStudentDispensaryObservation(studentDispensaryObservation);
            }
        }
        
        modelAndView = new ModelAndView("redirect:/diseases");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deleteStudentDispensaryObservationData"})
    public ModelAndView deleteStudentDispensaryObservationData(@RequestParam(value = "observationId", required = false) Long observationId, HttpSession httpSession) {

        System.out.println("DELETE  observationId = " + observationId);
        Long studentId = (Long) httpSession.getAttribute("studentId");
        if (observationId != null) {
            studentDispensaryObservationService.deleteStudentDispensaryObservationById(observationId);
        }


        modelAndView = new ModelAndView("redirect:/immunization");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deletestudentDiseasesAndAbnormalitiesData"})
    public ModelAndView deletestudentDiseasesAndAbnormalitiesData(@RequestParam(value = "abnormalitiesId", required = false) Long abnormalitiesId, HttpSession httpSession) {

        System.out.println("DELETE  abnormalitiesId = " + abnormalitiesId);
        Long studentId = (Long) httpSession.getAttribute("studentId");
        if (abnormalitiesId != null) {
            studentDiseasesAndAbnormService.deleteStudentDiseasesAndAbnormById(abnormalitiesId);
        }


        modelAndView = new ModelAndView("redirect:/immunization");

        return modelAndView;
    }
}


