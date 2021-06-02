package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.Analysis3Form;
import com.health.SchoolHealth.controlers.formPOJOs.ClassmatesForm;
import com.health.SchoolHealth.controlers.formPOJOs.DiseasesAndAbnormResults;
import com.health.SchoolHealth.model.entities.DiseasesAndAbnormType;
import com.health.SchoolHealth.services.DiseasesAndAbnormTypeService;
import com.health.SchoolHealth.services.StudentDiseasesAndAbnormService;
import com.health.SchoolHealth.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForAnalysisData;


@RestController
public class Analysis3SchemaController {

    // Services
    @Autowired
    private StudentService studentService;

    @Autowired
    private DiseasesAndAbnormTypeService diseasesAndAbnormTypeService;

    @Autowired
    private StudentDiseasesAndAbnormService studentDiseasesAndAbnormService;


    // Models
    ModelAndView modelAndView;

    // Forms
    Analysis3Form analysis3Form = new Analysis3Form();

    @GetMapping
    @RequestMapping(value = { "/analysis3"})
    public ModelAndView getClassmatesData(HttpSession httpSession) {

        modelAndView = new ModelAndView("analysis3");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForAnalysisData.contains(userTypeCode)) {
        List<DiseasesAndAbnormResults> diseasesAndAbnormResults = new ArrayList<>();
        List<DiseasesAndAbnormType> allDiseasesAndAbnormTypes = diseasesAndAbnormTypeService.getAllDiseasesAndAbnormTypes();

        Collections.sort(allDiseasesAndAbnormTypes, new Comparator<DiseasesAndAbnormType>() {
            @Override
            public int compare(DiseasesAndAbnormType diseasesAndAbnormType1, DiseasesAndAbnormType diseasesAndAbnormType2) {
                return extractInt(diseasesAndAbnormType1.getDiseasesAndAbnormTypeCode()) - extractInt(diseasesAndAbnormType2.getDiseasesAndAbnormTypeCode());
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });

        Integer schoolId = (Integer) httpSession.getAttribute("schoolId");
        for(DiseasesAndAbnormType diseasesAndAbnormType : allDiseasesAndAbnormTypes) {

            DiseasesAndAbnormResults diseasesAndAbnormResult = new DiseasesAndAbnormResults();

            diseasesAndAbnormResult.setDiseasesAndAbnormType(diseasesAndAbnormType);

            diseasesAndAbnormResult.setNumberOfStudentDiseasesAndAbnormalities(
                    studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCode(
                            diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));

            diseasesAndAbnormResult.setNumberOfIClass(
                    studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeIClass(
                            diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));

            diseasesAndAbnormResult.setNumberOfVIIClass(
                    studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeVIIClass(
                            diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));

            diseasesAndAbnormResult.setNumberOfXClass(
                    studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeXClass(
                            diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));

            diseasesAndAbnormResults.add(diseasesAndAbnormResult);
        }

        analysis3Form.setDiseasesAndAbnormResults(diseasesAndAbnormResults);

        modelAndView.addObject("analysis3Form", analysis3Form);
    } else {
        modelAndView.addObject("isReturnedErrorOnValidation", "true");
    }

        return modelAndView;
    }

}


