package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.Analysis2Form;
import com.health.SchoolHealth.controlers.formPOJOs.DispensaryObservationIllnessTypesResults;
import com.health.SchoolHealth.model.entities.DispensaryObservationIllnessType;
import com.health.SchoolHealth.services.DispensaryObservationIllnessTypeService;
import com.health.SchoolHealth.services.StudentDispensaryObservationService;
import com.health.SchoolHealth.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForAnalysisData;


@RestController
public class Analysis2SchemaController {

    // Services
    @Autowired
    private StudentService studentService;

    @Autowired
    private DispensaryObservationIllnessTypeService dispensaryObservationIllnessTypeService;

    @Autowired
    private StudentDispensaryObservationService studentDispensaryObservationService;
    // Models
    ModelAndView modelAndView;

    // Forms
    Analysis2Form analysis2Form = new Analysis2Form();

    @GetMapping
    @RequestMapping(value = { "/analysis2"})
    public ModelAndView getaAalysis2Data(HttpSession httpSession) {

        modelAndView = new ModelAndView("analysis2");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForAnalysisData.contains(userTypeCode)) {
            List<DispensaryObservationIllnessTypesResults> dispensaryObservationIllnessTypesResults = new ArrayList<>();
            List<DispensaryObservationIllnessType> allDispensaryObservationIllnessTypes = dispensaryObservationIllnessTypeService.getAllDispensaryObservationIllnessTypes();

            Integer schoolId = (Integer) httpSession.getAttribute("schoolId");

            for(DispensaryObservationIllnessType dispensaryObservationIllnessType : allDispensaryObservationIllnessTypes) {

                DispensaryObservationIllnessTypesResults dispensaryObservationIllnessTypesResult = new DispensaryObservationIllnessTypesResults();
                dispensaryObservationIllnessTypesResult.setDispensaryObservationIllnessType(dispensaryObservationIllnessType);

                dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsGirlsAged7to14(
                        studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged7to14(
                                dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

                dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsBoysAged7to14(
                        studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged7to14(
                                dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

                dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsGirlsAged14to18(
                        studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged14to18(
                                dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

                dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsBoysAged14to18(
                        studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged14to18(
                                dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

                dispensaryObservationIllnessTypesResults.add(dispensaryObservationIllnessTypesResult);
            }

            analysis2Form.setDispensaryObservationIllnessTypesResults(dispensaryObservationIllnessTypesResults);

        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        modelAndView.addObject("analysis2Form", analysis2Form);
        return modelAndView;
    }

}


