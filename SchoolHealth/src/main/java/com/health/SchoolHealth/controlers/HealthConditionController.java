package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.HealthConditionForm;
import com.health.SchoolHealth.model.entities.ConfirmationFlag;
import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.ImmunizationComment;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.HealthConditionService;
import com.health.SchoolHealth.services.StudentService;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForLZPKData;
import static com.health.SchoolHealth.util.FormUtil.CONFIRM_FLAG;


@RestController
public class HealthConditionController {
    // Services
    @Autowired
    HealthConditionService healthConditionService;

    @Autowired
    StudentService studentService;

    //Models
    ModelAndView modelAndView;

    // Forms
    HealthConditionForm healthConditionForm = new HealthConditionForm();

    @GetMapping
    @RequestMapping(value = {"/healthcondition"})
    public ModelAndView getHealthconditionData(HttpSession httpSession) {

        modelAndView = new ModelAndView("healthcondition");
        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForLZPKData.contains(userTypeCode)) {

            Long studentId = (Long) httpSession.getAttribute("studentId");
            System.out.println("Student id w helt 12345678999990 1234567890" + studentId);

            HealthCondition foundHealthCondition = healthConditionService.getHealthConditionByStudentId(studentId);

            if(foundHealthCondition != null) {
                healthConditionForm.setHealthCondition(foundHealthCondition);
                httpSession.setAttribute("healthConditionId", foundHealthCondition.getId());
            } else {
                healthConditionForm.setHealthCondition(new HealthCondition());
            }



            if (healthConditionForm.getHealthCondition().getMandatoryImmunizationFlag() != null
                    && CONFIRM_FLAG.equals(healthConditionForm.getHealthCondition().getMandatoryImmunizationFlag().getImmunizationCommentCode())) {
                healthConditionForm.setMandatoryImmunizationFlag("on");
            } else {
                healthConditionForm.setMandatoryImmunizationFlag(null);
            }

            if (healthConditionForm.getHealthCondition().getAdditionalActivities() != null
                    && CONFIRM_FLAG.equals(healthConditionForm.getHealthCondition().getAdditionalActivities().getConfirmationFlagCode())) {
                healthConditionForm.setAdditionalActivities("on");
            } else {
                healthConditionForm.setAdditionalActivities(null);
            }

            if (healthConditionForm.getHealthCondition().getTherapeuticPhysicalEducation() != null
                    && CONFIRM_FLAG.equals(healthConditionForm.getHealthCondition().getTherapeuticPhysicalEducation().getConfirmationFlagCode())) {
                healthConditionForm.setTherapeuticPhysicalEducation("on");
            } else {
                healthConditionForm.setTherapeuticPhysicalEducation(null);
            }

            if (healthConditionForm.getHealthCondition().getExemptFromPhysicalEducation() != null
                    && CONFIRM_FLAG.equals(healthConditionForm.getHealthCondition().getExemptFromPhysicalEducation().getConfirmationFlagCode())) {
                healthConditionForm.setExemptFromPhysicalEducation("on");
            } else {
                healthConditionForm.setExemptFromPhysicalEducation(null);
            }

            if (healthConditionForm.getHealthCondition().getMissingImmunizationFlag() != null
                    && CONFIRM_FLAG.equals(healthConditionForm.getHealthCondition().getMissingImmunizationFlag().getConfirmationFlagCode())) {
                healthConditionForm.setMissingImmunizationFlag("on");
            } else {
                healthConditionForm.setMissingImmunizationFlag(null);
            }
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        modelAndView.addObject("healthConditionForm", healthConditionForm);

        return modelAndView;

    }


    @PostMapping
    @RequestMapping(value = {"/healthConditionPostData"})
    public ModelAndView healthConditionPostData(@ModelAttribute("healthConditionForm") HealthConditionForm healthConditionForm,
                                                HttpSession httpSession) {

        System.out.println("healthConditionForm.getMandatoryImmunizationFlag() "+ healthConditionForm.getMandatoryImmunizationFlag());
        // Сетваме в healthCondition избраните чекбоксове
        if (healthConditionForm.getMandatoryImmunizationFlag() != null) {
            healthConditionForm.getHealthCondition().setMandatoryImmunizationFlag(new ImmunizationComment());
            healthConditionForm.getHealthCondition().getMandatoryImmunizationFlag().setImmunizationCommentCode(CONFIRM_FLAG);
        } else {
            healthConditionForm.getHealthCondition().setMandatoryImmunizationFlag(null);
        }

        if (healthConditionForm.getAdditionalActivities() != null) {
            healthConditionForm.getHealthCondition().setAdditionalActivities(new ConfirmationFlag());
            healthConditionForm.getHealthCondition().getAdditionalActivities().setConfirmationFlagCode(CONFIRM_FLAG);
        } else {
            healthConditionForm.getHealthCondition().setAdditionalActivities(null);
        }

        if (healthConditionForm.getTherapeuticPhysicalEducation() != null) {
            healthConditionForm.getHealthCondition().setTherapeuticPhysicalEducation(new ConfirmationFlag());
            healthConditionForm.getHealthCondition().getTherapeuticPhysicalEducation().setConfirmationFlagCode(CONFIRM_FLAG);
        } else {
            healthConditionForm.getHealthCondition().setTherapeuticPhysicalEducation(null);
        }

        if (healthConditionForm.getExemptFromPhysicalEducation() != null) {
            healthConditionForm.getHealthCondition().setExemptFromPhysicalEducation(new ConfirmationFlag());
            healthConditionForm.getHealthCondition().getExemptFromPhysicalEducation().setConfirmationFlagCode(CONFIRM_FLAG);
        } else {
            healthConditionForm.getHealthCondition().setExemptFromPhysicalEducation(null);
        }

        if (healthConditionForm.getMissingImmunizationFlag() != null ) {
            healthConditionForm.getHealthCondition().setMissingImmunizationFlag(new ConfirmationFlag());
            healthConditionForm.getHealthCondition().getMissingImmunizationFlag().setConfirmationFlagCode(CONFIRM_FLAG);
        } else {
            healthConditionForm.getHealthCondition().setMissingImmunizationFlag(null);
        }

        if (healthConditionForm.getHealthCondition().getStudent() == null || healthConditionForm.getHealthCondition().getStudent().getId() == null) {
            Student foundStudent = studentService.findStudentById((Long) httpSession.getAttribute("studentId"));
            healthConditionForm.getHealthCondition().setStudent(foundStudent);
        }

        HealthCondition savedHealthCondition = healthConditionService.createOrUpdateHealthCondition(healthConditionForm.getHealthCondition());
        httpSession.setAttribute("healthConditionId", savedHealthCondition.getId());

        modelAndView = new ModelAndView("redirect:/healthcondition");
        return modelAndView;
    }

}


