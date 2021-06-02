package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AnalysisForm;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.Norms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.health.SchoolHealth.util.ControllerUtil.*;


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

    private HashMap<String, Double> sGirlsWeightMap =  new HashMap<>();

    private HashMap<String, Double> sBoysWeightMap =  new HashMap<>();

    private HashMap<String, Double> xGirlsWeightMap =  new HashMap<>();

    private HashMap<String, Double> xBoysWeightMap =  new HashMap<>();

    private HashMap<String, Double> sGirlsHeightMap =  new HashMap<>();

    private HashMap<String, Double> sBoysHeightMap =  new HashMap<>();

    private HashMap<String, Double> xGirlsHeightMap =  new HashMap<>();

    private HashMap<String, Double> xBoysHeightMap =  new HashMap<>();

    @GetMapping
    @RequestMapping(value = {"/analysis"})
    public ModelAndView getAnalysisData(HttpSession httpSession) {

        modelAndView = new ModelAndView("analysis");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForAnalysisData.contains(userTypeCode)) {

            Integer schoolId = (Integer) httpSession.getAttribute("schoolId");

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

    //        subnormWeight
    //        overnormWeight
    //        subnormHeight
    //        overnormHeight

            initializeSWeightMapping();
            initializeSHeightMapping();
            initializeXWeightMapping();
            initializeXHeightMapping();

            initCounters();

            calcAndSetWeightFirstGroup(schoolId);
            calcAndSetHeightFirstGroup(schoolId);
            calcAndSetWeightSecondGroup(schoolId);
            calcAndSetHeightSecondGroup(schoolId);
            calcAndSetWeightThirdGroupUnderNorm(schoolId);
            calcAndSetHeightThirdGroupUnderNorm(schoolId);
            calcAndSetWeightThirdGroupOverNorm(schoolId);
            calcAndSetHeightThirdGroupOverNorm(schoolId);

//            lowLimit
//            highLimit

            modelAndView.addObject("analysisForm", analysisForm);
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        return modelAndView;
    }

    private void calcAndSetWeightFirstGroup(Integer schoolId) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countFirstGroupWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            System.out.println("sbwm " + sbwm.getKey() + " " + sbwm.getValue());
            class_ = sbwm.getKey();
            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            countFirstGroupWeight = anthropologicalService.countFirstGroupWeight(xWeight, sWeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIforAged7to14yearsBoys(analysisForm.getWeightIforAged7to14yearsBoys() + countFirstGroupWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIforAged14to18yearsBoys(analysisForm.getWeightIforAged14to18yearsBoys() + countFirstGroupWeight);
            }
            System.out.println("countFirstGroupWeight" + countFirstGroupWeight);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            System.out.println("sbwm " + sgwm.getKey() + " " + sgwm.getValue());
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countFirstGroupWeight = anthropologicalService.countFirstGroupWeight(xWeight, sWeight, class_, schoolId, GIRL);
            System.out.println("countFirstGroupWeight Girls xWeight, sWeight, class_, schoolId " + countFirstGroupWeight
                    + " " + xWeight + " " +  sWeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIforAged7to14yearsGirls(analysisForm.getWeightIforAged7to14yearsGirls() + countFirstGroupWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIforAged14to18yearsGirls(analysisForm.getWeightIforAged14to18yearsGirls() + countFirstGroupWeight);
            }
        }

    }

    private void calcAndSetHeightFirstGroup(Integer schoolId) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countFirstGroupHeight;

        for (Map.Entry<String, Double> sbhm : sBoysHeightMap.entrySet()) {
            System.out.println("sbhm " + sbhm.getKey() + " " + sbhm.getValue());
            class_ = sbhm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_);
            countFirstGroupHeight = anthropologicalService.countFirstGroupHeight(xHeight, sHeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                System.out.println("analysisForm != null " + analysisForm != null );
                System.out.println("analysisForm.getHeightIforAged7to14yearsBoys  != null " + analysisForm.getHeightIforAged7to14yearsBoys() != null );
                analysisForm.setHeightIforAged7to14yearsBoys(analysisForm.getHeightIforAged7to14yearsBoys() + countFirstGroupHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIforAged14to18yearsBoys(analysisForm.getHeightIforAged14to18yearsBoys() + countFirstGroupHeight);
            }
            System.out.println("countFirstGroupHeight" + countFirstGroupHeight);
        }

        for (Map.Entry<String, Double> sghm : sGirlsHeightMap.entrySet()) {
            System.out.println("sbwm " + sghm.getKey() + " " + sghm.getValue());
            class_ = sghm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countFirstGroupHeight = anthropologicalService.countFirstGroupHeight(xHeight, sHeight, class_, schoolId, GIRL);
            System.out.println("countFirstGroupHeight Girls xHeight, sHeight, class_, schoolId " + countFirstGroupHeight
                    + " " + xHeight + " " +  sHeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setHeightIforAged7to14yearsGirls(analysisForm.getHeightIforAged7to14yearsGirls() + countFirstGroupHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIforAged14to18yearsGirls(analysisForm.getHeightIforAged14to18yearsGirls() + countFirstGroupHeight);
            }
        }

    }

    private void calcAndSetWeightSecondGroup(Integer schoolId) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countSecondGroupWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            System.out.println("sbwm " + sbwm.getKey() + " " + sbwm.getValue());
            class_ = sbwm.getKey();
            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            System.out.println("xWeight sWeight " + xWeight + " " + sWeight);
            countSecondGroupWeight = anthropologicalService.countSecondGroupWeight(xWeight, sWeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIIforAged7to14yearsBoys(analysisForm.getWeightIIforAged7to14yearsBoys() + countSecondGroupWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIIforAged14to18yearsBoys(analysisForm.getWeightIIforAged14to18yearsBoys() + countSecondGroupWeight);
            }
            System.out.println("countSecondGroupWeight" + countSecondGroupWeight);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            System.out.println("sbwm " + sgwm.getKey() + " " + sgwm.getValue());
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countSecondGroupWeight = anthropologicalService.countSecondGroupWeight(xWeight, sWeight, class_, schoolId, GIRL);
            System.out.println("countFirstGroupWeight Girls xWeight, sWeight, class_, schoolId " + countSecondGroupWeight
                    + " " + xWeight + " " +  sWeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIIforAged7to14yearsGirls(analysisForm.getWeightIIforAged7to14yearsGirls() + countSecondGroupWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIIforAged14to18yearsGirls(analysisForm.getWeightIIforAged14to18yearsGirls() + countSecondGroupWeight);
            }
        }

    }

    private void calcAndSetHeightSecondGroup(Integer schoolId) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countSecondGroupHeight;

        for (Map.Entry<String, Double> sbwm : sBoysHeightMap.entrySet()) {
            System.out.println("sbwm " + sbwm.getKey() + " " + sbwm.getValue());
            class_ = sbwm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_);
            countSecondGroupHeight = anthropologicalService.countSecondGroupHeight(xHeight, sHeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                analysisForm.setHeightIIforAged7to14yearsBoys(analysisForm.getHeightIIforAged7to14yearsBoys() + countSecondGroupHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIIforAged14to18yearsBoys(analysisForm.getHeightIIforAged14to18yearsBoys() + countSecondGroupHeight);
            }
            System.out.println("countSecondGroupHeight" + countSecondGroupHeight);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsHeightMap.entrySet()) {
            System.out.println("sbwm " + sgwm.getKey() + " " + sgwm.getValue());
            class_ = sgwm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countSecondGroupHeight = anthropologicalService.countSecondGroupHeight(xHeight, sHeight, class_, schoolId, GIRL);
            System.out.println("countFirstGroupHeight Girls xHeight, sHeight, class_, schoolId " + countSecondGroupHeight
                    + " " + xHeight + " " +  sHeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setHeightIIforAged7to14yearsGirls(analysisForm.getHeightIIforAged7to14yearsGirls() + countSecondGroupHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIIforAged14to18yearsGirls(analysisForm.getHeightIIforAged14to18yearsGirls() + countSecondGroupHeight);
            }
        }

    }

    private void calcAndSetWeightThirdGroupUnderNorm(Integer schoolId) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countThirdGroupUnderNormWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            System.out.println("sbwm " + sbwm.getKey() + " " + sbwm.getValue());
            class_ = sbwm.getKey();
            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            System.out.println("xWeight sWeight " + xWeight + " " + sWeight);
            countThirdGroupUnderNormWeight = anthropologicalService.countThirdGroupUnderNormWeight(xWeight, sWeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIIIUnderNormforAged7to14yearsBoys(analysisForm.getWeightIIIUnderNormforAged7to14yearsBoys() + countThirdGroupUnderNormWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIIIUnderNormforAged14to18yearsBoys(analysisForm.getWeightIIIUnderNormforAged14to18yearsBoys() + countThirdGroupUnderNormWeight);
            }
            System.out.println("countThirdGroupUnderNormWeight" + countThirdGroupUnderNormWeight);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            System.out.println("sbwm " + sgwm.getKey() + " " + sgwm.getValue());
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countThirdGroupUnderNormWeight = anthropologicalService.countThirdGroupUnderNormWeight(xWeight, sWeight, class_, schoolId, GIRL);
            System.out.println("countFirstGroupWeight Girls xWeight, sWeight, class_, schoolId " + countThirdGroupUnderNormWeight
                    + " " + xWeight + " " +  sWeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIIIUnderNormforAged7to14yearsGirls(analysisForm.getWeightIIIUnderNormforAged7to14yearsGirls() + countThirdGroupUnderNormWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIIIUnderNormforAged14to18yearsGirls(analysisForm.getWeightIIIUnderNormforAged14to18yearsGirls() + countThirdGroupUnderNormWeight);
            }
        }

    }

    private void calcAndSetHeightThirdGroupUnderNorm(Integer schoolId) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countThirdGroupUnderNormHeight;

        for (Map.Entry<String, Double> sbwm : sBoysHeightMap.entrySet()) {
            System.out.println("sbwm " + sbwm.getKey() + " " + sbwm.getValue());
            class_ = sbwm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_);
            countThirdGroupUnderNormHeight = anthropologicalService.countThirdGroupUnderNormHeight(xHeight, sHeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                analysisForm.setHeightIIIUnderNormforAged7to14yearsBoys(analysisForm.getHeightIIIUnderNormforAged7to14yearsBoys() + countThirdGroupUnderNormHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIIIUnderNormforAged14to18yearsBoys(analysisForm.getHeightIIIUnderNormforAged14to18yearsBoys() + countThirdGroupUnderNormHeight);
            }
            System.out.println("countThirdGroupUnderNormHeight" + countThirdGroupUnderNormHeight);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsHeightMap.entrySet()) {
            System.out.println("sbwm " + sgwm.getKey() + " " + sgwm.getValue());
            class_ = sgwm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countThirdGroupUnderNormHeight = anthropologicalService.countThirdGroupUnderNormHeight(xHeight, sHeight, class_, schoolId, GIRL);
            System.out.println("countThirdGroupUnderNormHeight Girls xHeight, sHeight, class_, schoolId " + countThirdGroupUnderNormHeight
                    + " " + xHeight + " " +  sHeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setHeightIIIUnderNormforAged7to14yearsGirls(analysisForm.getHeightIIIUnderNormforAged7to14yearsGirls() + countThirdGroupUnderNormHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIIIUnderNormforAged14to18yearsGirls(analysisForm.getHeightIIIUnderNormforAged14to18yearsGirls() + countThirdGroupUnderNormHeight);
            }
        }

    }

    private void calcAndSetWeightThirdGroupOverNorm(Integer schoolId) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countThirdGroupOverNormWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            System.out.println("sbwm " + sbwm.getKey() + " " + sbwm.getValue());
            class_ = sbwm.getKey();

            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            System.out.println("xWeight sWeight " + xWeight + " " + sWeight);
            countThirdGroupOverNormWeight = anthropologicalService.countThirdGroupOverNormWeight(xWeight, sWeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIIIOverNormforAged7to14yearsBoys(analysisForm.getWeightIIIOverNormforAged7to14yearsBoys() + countThirdGroupOverNormWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIIIOverNormforAged14to18yearsBoys(analysisForm.getWeightIIIOverNormforAged14to18yearsBoys() + countThirdGroupOverNormWeight);
            }
            System.out.println("countThirdGroupOverNormWeight" + countThirdGroupOverNormWeight);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            System.out.println("sbwm " + sgwm.getKey() + " " + sgwm.getValue());
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countThirdGroupOverNormWeight = anthropologicalService.countThirdGroupOverNormWeight(xWeight, sWeight, class_, schoolId, GIRL);
            System.out.println("countThirdGroupOverNormWeight Girls xWeight, sWeight, class_, schoolId " + countThirdGroupOverNormWeight
                    + " " + xWeight + " " +  sWeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setWeightIIIOverNormforAged7to14yearsGirls(analysisForm.getWeightIIIOverNormforAged7to14yearsGirls() + countThirdGroupOverNormWeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setWeightIIIOverNormforAged14to18yearsGirls(analysisForm.getWeightIIIOverNormforAged14to18yearsGirls() + countThirdGroupOverNormWeight);
            }
        }

    }

    private void calcAndSetHeightThirdGroupOverNorm(Integer schoolId) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countThirdGroupOverNormHeight;

        for (Map.Entry<String, Double> sbwm : sBoysHeightMap.entrySet()) {
            System.out.println("sbwm " + sbwm.getKey() + " " + sbwm.getValue());
            class_ = sbwm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_);
            countThirdGroupOverNormHeight = anthropologicalService.countThirdGroupOverNormHeight(xHeight, sHeight, class_, schoolId, BOY);
            if(aged7to14years.contains(class_)) {
                analysisForm.setHeightIIIOverNormforAged7to14yearsBoys(analysisForm.getHeightIIIOverNormforAged7to14yearsBoys() + countThirdGroupOverNormHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIIIOverNormforAged14to18yearsBoys(analysisForm.getHeightIIIOverNormforAged14to18yearsBoys() + countThirdGroupOverNormHeight);
            }
            System.out.println("countThirdGroupOverNormHeight" + countThirdGroupOverNormHeight);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsHeightMap.entrySet()) {
            System.out.println("sbwm " + sgwm.getKey() + " " + sgwm.getValue());
            class_ = sgwm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countThirdGroupOverNormHeight  = anthropologicalService.countThirdGroupOverNormHeight(xHeight, sHeight, class_, schoolId, GIRL);
            System.out.println("countThirdGroupOverNormHeight Girls xHeight, sHeight, class_, schoolId " + countThirdGroupOverNormHeight
                    + " " + xHeight + " " +  sHeight + " " +  class_ + " " + schoolId);
            if(aged7to14years.contains(class_)) {
                analysisForm.setHeightIIIOverNormforAged7to14yearsGirls(analysisForm.getHeightIIIOverNormforAged7to14yearsGirls() + countThirdGroupOverNormHeight);
            }
            if(aged14to18years.contains(class_)) {
                analysisForm.setHeightIIIOverNormforAged14to18yearsGirls(analysisForm.getHeightIIIOverNormforAged14to18yearsGirls() + countThirdGroupOverNormHeight);
            }
        }

    }
    private void initializeSWeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                sGirlsWeightMap.put(norm.getClass_(), norm.getsWeight());
            } else {
                sBoysWeightMap.put(norm.getClass_(), norm.getsWeight());
            }
        }
    }

    private void initializeSHeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                sGirlsHeightMap.put(norm.getClass_(), norm.getsHeight());
            } else {
                sBoysHeightMap.put(norm.getClass_(), norm.getsHeight());
            }
        }
    }


    private void initializeXWeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                xGirlsWeightMap.put(norm.getClass_(), norm.getxWeight());
            } else {
                xBoysWeightMap.put(norm.getClass_(), norm.getxWeight());
            }
        }
    }

    private void initializeXHeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                xGirlsHeightMap.put(norm.getClass_(), norm.getxHeight());
            } else {
                xBoysHeightMap.put(norm.getClass_(), norm.getxHeight());
            }
        }
    }

    private void initCounters() {
        analysisForm.setWeightIforAged7to14yearsGirls(0);
        analysisForm.setWeightIIforAged7to14yearsGirls(0);
        analysisForm.setWeightIIIUnderNormforAged7to14yearsGirls(0);
        analysisForm.setWeightIIIOverNormforAged7to14yearsGirls(0);
        analysisForm.setWeightIforAged14to18yearsGirls(0);
        analysisForm.setWeightIIforAged14to18yearsGirls(0);
        analysisForm.setWeightIIIUnderNormforAged14to18yearsGirls(0);
        analysisForm.setWeightIIIOverNormforAged14to18yearsGirls(0);

        analysisForm.setWeightIforAged7to14yearsBoys(0);
        analysisForm.setWeightIIforAged7to14yearsBoys(0);
        analysisForm.setWeightIIIUnderNormforAged7to14yearsBoys(0);
        analysisForm.setWeightIIIOverNormforAged7to14yearsBoys(0);
        analysisForm.setWeightIforAged14to18yearsBoys(0);
        analysisForm.setWeightIIforAged14to18yearsBoys(0);
        analysisForm.setWeightIIIUnderNormforAged14to18yearsBoys(0);
        analysisForm.setWeightIIIOverNormforAged14to18yearsBoys(0);

        analysisForm.setHeightIforAged7to14yearsGirls(0);
        analysisForm.setHeightIIforAged7to14yearsGirls(0);
        analysisForm.setHeightIIIUnderNormforAged7to14yearsGirls(0);
        analysisForm.setHeightIIIOverNormforAged7to14yearsGirls(0);
        analysisForm.setHeightIforAged14to18yearsGirls(0);
        analysisForm.setHeightIIforAged14to18yearsGirls(0);
        analysisForm.setHeightIIIUnderNormforAged14to18yearsGirls(0);
        analysisForm.setHeightIIIOverNormforAged14to18yearsGirls(0);

        analysisForm.setHeightIforAged7to14yearsBoys(0);
        analysisForm.setHeightIIforAged7to14yearsBoys(0);
        analysisForm.setHeightIIIUnderNormforAged7to14yearsBoys(0);
        analysisForm.setHeightIIIOverNormforAged7to14yearsBoys(0);
        analysisForm.setHeightIforAged14to18yearsBoys(0);
        analysisForm.setHeightIIforAged14to18yearsBoys(0);
        analysisForm.setHeightIIIUnderNormforAged14to18yearsBoys(0);
        analysisForm.setHeightIIIOverNormforAged14to18yearsBoys(0);
    }
}


