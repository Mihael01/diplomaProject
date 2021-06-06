package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AnalysisForm;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.AnalysisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForAnalysisData;


@RestController
public class AnalysisSchemaController {

    // Services
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PhysicalCapacityService physicalCapacityService;

    @Autowired
    private HealthConditionService healthConditionService;

    @Autowired
    private AnthropologicalService anthropologicalService;

    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    AnalysisForm analysisForm = new AnalysisForm();

    @GetMapping
    @RequestMapping(value = {"/analysis"})
    public ModelAndView getAnalysisData(HttpSession httpSession) {

        modelAndView = new ModelAndView("analysis");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForAnalysisData.contains(userTypeCode)) {

            Integer schoolId = (Integer) httpSession.getAttribute("schoolId");

            analysisForm.setNumberOfClasses(studentService.countAllClassesFofSchool(schoolId));

            analysisForm.setNumberOfStudents(studentService.countAllStudentsFofSchool(schoolId));

            analysisForm.setAged7To14Girls(studentService.countAllGirlsFrom7to14FofSchool(schoolId));

            analysisForm.setAged7To14Boys(studentService.countAllBoysFrom7to14FofSchool(schoolId));

            analysisForm.setAged14To18Girls(studentService.countAllGirlsFrom14to18FofSchool(schoolId));

            analysisForm.setAged14To18Boys(studentService.countAllBoysFrom14to18FofSchool(schoolId));

            analysisForm.setHavingMarkGreaterThan3(physicalCapacityService.countStudentsHavingMarkGreaterThan3(schoolId));

            analysisForm.setHavingMarkGreaterThan3GirlsAged7To14(
                    physicalCapacityService.countStudentsHavingMarkGreaterThan3GirlsFrom7to14FofSchool(schoolId));

            analysisForm.setHavingMarkGreaterThan3BoysAged7To14(
                    physicalCapacityService.countStudentsHavingMarkGreaterThan3BoysFrom7to14FofSchool(schoolId));

            analysisForm.setHavingMarkGreaterThan3GirlsAged14To18(
                    physicalCapacityService.countStudentsHavingMarkGreaterThan3GirlsFrom14to18FofSchool(schoolId));

            analysisForm.setHavingMarkGreaterThan3BoysAged14To18(
                    physicalCapacityService.countStudentsHavingMarkGreaterThan3BoysFrom14to18FofSchool(schoolId));

            analysisForm.setExemptFromPhysicalEducation(
                    healthConditionService.countStudentsExemptFromPhysicalEducation(schoolId));

            analysisForm.setExemptFromPhysicalEducationGirlsAged7To14(
                    healthConditionService.countStudentsExemptFromPhysicalEducationGirlsFrom7to14FofSchool(schoolId));

            analysisForm.setExemptFromPhysicalEducationBoysAged7To14(
                    healthConditionService.countStudentsExemptFromPhysicalEducationBoysFrom7to14FofSchool(schoolId));

            analysisForm.setExemptFromPhysicalEducationGirlsAged14To18(
                    healthConditionService.countStudentsExemptFromPhysicalEducationGirlsFrom14to18FofSchool(schoolId));

            analysisForm.setExemptFromPhysicalEducationBoysAged14To18(
                    healthConditionService.countStudentsExemptFromPhysicalEducationBoysFrom14to18FofSchool(schoolId));

            analysisForm.setIncludeInTherapeuticPhysicalEducation(
                    healthConditionService.countStudentsIncludInTherapeuticPhysicalEducation(schoolId));

            analysisForm.setIncludeInTherapeuticPhysicalEducationGirlsAged7To14(
                    healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom7to14FofSchool(schoolId));

            analysisForm.setIncludeInTherapeuticPhysicalEducationBoysAged7To14(
                    healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationBoysFrom7to14FofSchool(schoolId));

            analysisForm.setIncludeInTherapeuticPhysicalEducationGirlsAged14To18(
                    healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom14to18FofSchool(schoolId));

            analysisForm.setIncludeInTherapeuticPhysicalEducationBoysAged14To18(
                    healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationBoysFrom14to18FofSchool(schoolId));

            AnalysisUtil.initCounters();

            AnalysisUtil.initializeSWeightMapping();
            AnalysisUtil.initializeSHeightMapping();
            AnalysisUtil.initializeXWeightMapping();
            AnalysisUtil.initializeXHeightMapping();

            AnalysisUtil.calcAndSetWeightFirstGroup(schoolId, anthropologicalService);
            AnalysisUtil.calcAndSetHeightFirstGroup(schoolId, anthropologicalService);
            AnalysisUtil.calcAndSetWeightSecondGroup(schoolId, anthropologicalService);
            AnalysisUtil.calcAndSetHeightSecondGroup(schoolId, anthropologicalService);
            AnalysisUtil.calcAndSetWeightThirdGroupUnderNorm(schoolId, anthropologicalService);
            AnalysisUtil.calcAndSetHeightThirdGroupUnderNorm(schoolId, anthropologicalService);
            AnalysisUtil.calcAndSetWeightThirdGroupOverNorm(schoolId, anthropologicalService);
            AnalysisUtil.calcAndSetHeightThirdGroupOverNorm(schoolId, anthropologicalService);

            analysisForm.setWeightIforAged7to14yearsBoys(AnalysisUtil.weightIforAged7to14yearsBoys.toInteger());
            analysisForm.setWeightIforAged14to18yearsBoys(AnalysisUtil.weightIforAged14to18yearsBoys.toInteger());
            analysisForm.setWeightIforAged7to14yearsGirls(AnalysisUtil.weightIforAged7to14yearsGirls.toInteger());
            analysisForm.setWeightIforAged14to18yearsGirls(AnalysisUtil.weightIforAged14to18yearsGirls.toInteger());

            analysisForm.setHeightIforAged7to14yearsBoys(AnalysisUtil.heightIforAged7to14yearsBoys.toInteger());
            analysisForm.setHeightIforAged14to18yearsBoys(AnalysisUtil.heightIforAged14to18yearsBoys.toInteger());
            analysisForm.setHeightIforAged7to14yearsGirls(AnalysisUtil.heightIforAged7to14yearsGirls.toInteger());
            analysisForm.setHeightIforAged14to18yearsGirls(AnalysisUtil.heightIforAged14to18yearsGirls.toInteger());

            analysisForm.setHeightIIIUnderNormforAged7to14yearsBoys(AnalysisUtil.heightIIIUnderNormforAged7to14yearsBoys.toInteger());
            analysisForm.setHeightIIIUnderNormforAged14to18yearsBoys(AnalysisUtil.heightIIIUnderNormforAged14to18yearsBoys.toInteger());
            analysisForm.setHeightIIIUnderNormforAged7to14yearsGirls(AnalysisUtil.heightIIIUnderNormforAged7to14yearsGirls.toInteger());
            analysisForm.setHeightIIIUnderNormforAged14to18yearsGirls(AnalysisUtil.heightIIIUnderNormforAged14to18yearsGirls.toInteger());

            analysisForm.setWeightIIIOverNormforAged7to14yearsBoys(AnalysisUtil.weightIIIOverNormforAged7to14yearsBoys.toInteger());
            analysisForm.setWeightIIIOverNormforAged14to18yearsBoys(AnalysisUtil.weightIIIOverNormforAged14to18yearsBoys.toInteger());
            analysisForm.setWeightIIIOverNormforAged7to14yearsGirls(AnalysisUtil.weightIIIOverNormforAged7to14yearsGirls.toInteger());
            analysisForm.setWeightIIIOverNormforAged14to18yearsGirls(AnalysisUtil.weightIIIOverNormforAged14to18yearsGirls.toInteger());

            analysisForm.setHeightIIIOverNormforAged7to14yearsBoys(AnalysisUtil.heightIIIOverNormforAged7to14yearsBoys.toInteger());
            analysisForm.setHeightIIIOverNormforAged14to18yearsBoys(AnalysisUtil.heightIIIOverNormforAged14to18yearsBoys.toInteger());
            analysisForm.setHeightIIIOverNormforAged7to14yearsGirls(AnalysisUtil.heightIIIOverNormforAged7to14yearsGirls.toInteger());
            analysisForm.setHeightIIIOverNormforAged14to18yearsGirls(AnalysisUtil.heightIIIOverNormforAged14to18yearsGirls.toInteger());

            analysisForm.setWeightIIIUnderNormforAged7to14yearsBoys(AnalysisUtil.weightIIIUnderNormforAged7to14yearsBoys.toInteger());
            analysisForm.setWeightIIIUnderNormforAged14to18yearsBoys(AnalysisUtil.weightIIIUnderNormforAged14to18yearsBoys.toInteger());
            analysisForm.setWeightIIIUnderNormforAged7to14yearsGirls(AnalysisUtil.weightIIIUnderNormforAged7to14yearsGirls.toInteger());
            analysisForm.setWeightIIIUnderNormforAged14to18yearsGirls(AnalysisUtil.weightIIIUnderNormforAged14to18yearsGirls.toInteger());

            analysisForm.setWeightIIforAged7to14yearsBoys(AnalysisUtil.weightIIforAged7to14yearsBoys.toInteger());
            analysisForm.setWeightIIforAged14to18yearsBoys(AnalysisUtil.weightIIforAged14to18yearsBoys.toInteger());
            analysisForm.setWeightIIforAged7to14yearsGirls(AnalysisUtil.weightIIforAged7to14yearsGirls.toInteger());
            analysisForm.setWeightIIforAged14to18yearsGirls(AnalysisUtil.weightIIforAged14to18yearsGirls.toInteger());

            analysisForm.setHeightIIforAged7to14yearsBoys(AnalysisUtil.heightIIforAged7to14yearsBoys.toInteger());
            analysisForm.setHeightIIforAged14to18yearsBoys(AnalysisUtil.heightIIforAged14to18yearsBoys.toInteger());
            analysisForm.setHeightIIforAged7to14yearsGirls(AnalysisUtil.heightIIforAged7to14yearsGirls.toInteger());
            analysisForm.setHeightIIforAged14to18yearsGirls(AnalysisUtil.heightIIforAged14to18yearsGirls.toInteger());

            modelAndView.addObject("analysisForm", analysisForm);
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        return modelAndView;
    }

}


