package com.health.SchoolHealth.util;

import com.health.SchoolHealth.services.AnthropologicalService;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnalysisUtil {

    public static MutableInt weightIforAged7to14yearsGirls;

    public static MutableInt weightIIforAged7to14yearsGirls;

    public static MutableInt weightIIIUnderNormforAged7to14yearsGirls;

    public static MutableInt weightIIIOverNormforAged7to14yearsGirls;

    public static MutableInt weightIforAged14to18yearsGirls;

    public static MutableInt weightIIforAged14to18yearsGirls;

    public static MutableInt weightIIIUnderNormforAged14to18yearsGirls;

    public static MutableInt weightIIIOverNormforAged14to18yearsGirls;

    public static MutableInt weightIforAged7to14yearsBoys;

    public static MutableInt weightIIforAged7to14yearsBoys;

    public static MutableInt weightIIIUnderNormforAged7to14yearsBoys;

    public static MutableInt weightIIIOverNormforAged7to14yearsBoys;

    public static MutableInt weightIforAged14to18yearsBoys;

    public static MutableInt weightIIforAged14to18yearsBoys;

    public static MutableInt weightIIIUnderNormforAged14to18yearsBoys;

    public static MutableInt weightIIIOverNormforAged14to18yearsBoys;

    public static MutableInt heightIforAged7to14yearsGirls;

    public static MutableInt heightIIforAged7to14yearsGirls;

    public static MutableInt heightIIIUnderNormforAged7to14yearsGirls;

    public static MutableInt heightIIIOverNormforAged7to14yearsGirls;

    public static MutableInt heightIforAged14to18yearsGirls;

    public static MutableInt heightIIforAged14to18yearsGirls;

    public static MutableInt heightIIIUnderNormforAged14to18yearsGirls;

    public static MutableInt heightIIIOverNormforAged14to18yearsGirls;

    public static MutableInt heightIforAged7to14yearsBoys;

    public static MutableInt heightIIforAged7to14yearsBoys;

    public static MutableInt heightIIIUnderNormforAged7to14yearsBoys;

    public static MutableInt heightIIIOverNormforAged7to14yearsBoys;

    public static MutableInt heightIforAged14to18yearsBoys;

    public static MutableInt heightIIforAged14to18yearsBoys;

    public static MutableInt heightIIIUnderNormforAged14to18yearsBoys;

    public static MutableInt heightIIIOverNormforAged14to18yearsBoys;

    public static HashMap<String, Double> sGirlsWeightMap =  new HashMap<>();

    public static HashMap<String, Double> sBoysWeightMap =  new HashMap<>();

    public static HashMap<String, Double> xGirlsWeightMap =  new HashMap<>();

    public static HashMap<String, Double> xBoysWeightMap =  new HashMap<>();

    public static HashMap<String, Double> sGirlsHeightMap =  new HashMap<>();

    public static HashMap<String, Double> sBoysHeightMap =  new HashMap<>();

    public static HashMap<String, Double> xGirlsHeightMap =  new HashMap<>();

    public static HashMap<String, Double> xBoysHeightMap =  new HashMap<>();

    public static final String BOY = "M";

    public static final String GIRL = "F";

    public static final List<String> aged7to14years = new ArrayList<>();

    static {
        aged7to14years.add("I");
        aged7to14years.add("II");
        aged7to14years.add("III");
        aged7to14years.add("IV");
        aged7to14years.add("V");
        aged7to14years.add("VI");
        aged7to14years.add("VII");
    }

    public static final List<String> aged14to18years = new ArrayList<>();

    static {
        aged14to18years.add("VIII");
        aged14to18years.add("IX");
        aged14to18years.add("X");
        aged14to18years.add("XI");
        aged14to18years.add("XII");;
    }


    public static void calcAndSetWeightFirstGroup(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countFirstGroupWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            class_ = sbwm.getKey();
            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            countFirstGroupWeight = anthropologicalService.countFirstGroupWeight(xWeight, sWeight, class_, schoolId, BOY);
            calcSum(class_, countFirstGroupWeight, weightIforAged7to14yearsBoys, weightIforAged14to18yearsBoys);

        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countFirstGroupWeight = anthropologicalService.countFirstGroupWeight(xWeight, sWeight, class_, schoolId, GIRL);
            calcSum(class_, countFirstGroupWeight, weightIforAged7to14yearsGirls, weightIforAged14to18yearsGirls);
        }
    }

    private static void calcSum(String class_, Integer countFirstGroupWeight, MutableInt forAged7to14years, MutableInt forAged14to18years) {
        if(aged7to14years.contains(class_)) {
            forAged7to14years.add(countFirstGroupWeight);
        }
        if(aged14to18years.contains(class_)) {
            forAged14to18years.add(countFirstGroupWeight);
        }
    }

    public static void calcAndSetHeightFirstGroup(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countFirstGroupHeight;

        for (Map.Entry<String, Double> sbhm : sBoysHeightMap.entrySet()) {
            class_ = sbhm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_); sbhm.getValue();
            countFirstGroupHeight = anthropologicalService.countFirstGroupHeight(xHeight, sHeight, class_, schoolId, BOY);
            calcSum(class_, countFirstGroupHeight, heightIforAged7to14yearsBoys, heightIforAged14to18yearsBoys);
        }

        for (Map.Entry<String, Double> sghm : sGirlsHeightMap.entrySet()) {
            class_ = sghm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countFirstGroupHeight = anthropologicalService.countFirstGroupHeight(xHeight, sHeight, class_, schoolId, GIRL);
            calcSum(class_, countFirstGroupHeight, heightIforAged7to14yearsGirls, heightIforAged14to18yearsGirls);
        }


    }

    public static void calcAndSetWeightSecondGroup(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countSecondGroupWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            class_ = sbwm.getKey();
            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            countSecondGroupWeight = anthropologicalService.countSecondGroupWeight(xWeight, sWeight, class_, schoolId, BOY);
            calcSum(class_, countSecondGroupWeight, weightIIforAged7to14yearsBoys, weightIIforAged14to18yearsBoys);

        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countSecondGroupWeight = anthropologicalService.countSecondGroupWeight(xWeight, sWeight, class_, schoolId, GIRL);
            calcSum(class_, countSecondGroupWeight, weightIIforAged7to14yearsGirls, weightIIforAged14to18yearsGirls);
        }

    }

    public static void calcAndSetHeightSecondGroup(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countSecondGroupHeight;

        for (Map.Entry<String, Double> sbwm : sBoysHeightMap.entrySet()) {
            class_ = sbwm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_);
            countSecondGroupHeight = anthropologicalService.countSecondGroupHeight(xHeight, sHeight, class_, schoolId, BOY);
            calcSum(class_, countSecondGroupHeight, heightIIforAged7to14yearsBoys, heightIIforAged14to18yearsBoys);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsHeightMap.entrySet()) {
            class_ = sgwm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countSecondGroupHeight = anthropologicalService.countSecondGroupHeight(xHeight, sHeight, class_, schoolId, GIRL);
            calcSum(class_, countSecondGroupHeight, heightIIforAged7to14yearsGirls, heightIIforAged14to18yearsGirls);
        }
    }

    public static void calcAndSetWeightThirdGroupUnderNorm(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countThirdGroupUnderNormWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            class_ = sbwm.getKey();
            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            countThirdGroupUnderNormWeight = anthropologicalService.countThirdGroupUnderNormWeight(xWeight, sWeight, class_, schoolId, BOY);
            calcSum(class_, countThirdGroupUnderNormWeight, weightIIIUnderNormforAged7to14yearsBoys, weightIIIUnderNormforAged14to18yearsBoys);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countThirdGroupUnderNormWeight = anthropologicalService.countThirdGroupUnderNormWeight(xWeight, sWeight, class_, schoolId, GIRL);

            calcSum(class_, countThirdGroupUnderNormWeight, weightIIIUnderNormforAged7to14yearsGirls, weightIIIUnderNormforAged14to18yearsGirls);
        }


    }

    public static void calcAndSetHeightThirdGroupUnderNorm(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countThirdGroupUnderNormHeight;

        for (Map.Entry<String, Double> sbwm : sBoysHeightMap.entrySet()) {
            class_ = sbwm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_);
            countThirdGroupUnderNormHeight = anthropologicalService.countThirdGroupUnderNormHeight(xHeight, sHeight, class_, schoolId, BOY);
            calcSum(class_, countThirdGroupUnderNormHeight, heightIIIUnderNormforAged7to14yearsBoys, heightIIIUnderNormforAged14to18yearsBoys);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsHeightMap.entrySet()) {
            class_ = sgwm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countThirdGroupUnderNormHeight = anthropologicalService.countThirdGroupUnderNormHeight(xHeight, sHeight, class_, schoolId, GIRL);

            calcSum(class_, countThirdGroupUnderNormHeight, heightIIIUnderNormforAged7to14yearsGirls, heightIIIUnderNormforAged14to18yearsGirls);
        }
    }

    public static void calcAndSetWeightThirdGroupOverNorm(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xWeight;
        Double sWeight;
        Integer countThirdGroupOverNormWeight;

        for (Map.Entry<String, Double> sbwm : sBoysWeightMap.entrySet()) {
            class_ = sbwm.getKey();

            xWeight = xBoysWeightMap.get(class_);
            sWeight = sBoysWeightMap.get(class_);
            countThirdGroupOverNormWeight = anthropologicalService.countThirdGroupOverNormWeight(xWeight, sWeight, class_, schoolId, BOY);
            calcSum(class_, countThirdGroupOverNormWeight, weightIIIOverNormforAged7to14yearsBoys, weightIIIOverNormforAged14to18yearsBoys);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsWeightMap.entrySet()) {
            class_ = sgwm.getKey();
            xWeight = xGirlsWeightMap.get(class_);
            sWeight = sGirlsWeightMap.get(class_);
            countThirdGroupOverNormWeight = anthropologicalService.countThirdGroupOverNormWeight(xWeight, sWeight, class_, schoolId, GIRL);

            calcSum(class_, countThirdGroupOverNormWeight, weightIIIOverNormforAged7to14yearsGirls, weightIIIOverNormforAged14to18yearsGirls);
        }

    }

    public static void calcAndSetHeightThirdGroupOverNorm(Integer schoolId, AnthropologicalService anthropologicalService) {

        String class_;
        Double xHeight;
        Double sHeight;
        Integer countThirdGroupOverNormHeight;

        for (Map.Entry<String, Double> sbwm : sBoysHeightMap.entrySet()) {
            class_ = sbwm.getKey();
            xHeight = xBoysHeightMap.get(class_);
            sHeight = sBoysHeightMap.get(class_);
            countThirdGroupOverNormHeight = anthropologicalService.countThirdGroupOverNormHeight(xHeight, sHeight, class_, schoolId, BOY);
            calcSum(class_, countThirdGroupOverNormHeight, heightIIIOverNormforAged7to14yearsBoys, heightIIIOverNormforAged14to18yearsBoys);
        }

        for (Map.Entry<String, Double> sgwm : sGirlsHeightMap.entrySet()) {
            class_ = sgwm.getKey();
            xHeight = xGirlsHeightMap.get(class_);
            sHeight = sGirlsHeightMap.get(class_);
            countThirdGroupOverNormHeight  = anthropologicalService.countThirdGroupOverNormHeight(xHeight, sHeight, class_, schoolId, GIRL);
            calcSum(class_, countThirdGroupOverNormHeight, heightIIIOverNormforAged7to14yearsGirls, heightIIIOverNormforAged14to18yearsGirls);
        }

    }

    public static void initializeSWeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                sGirlsWeightMap.put(norm.getClass_(), norm.getsWeight());
            } else {
                sBoysWeightMap.put(norm.getClass_(), norm.getsWeight());
            }
        }
    }

    public static void initializeSHeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                sGirlsHeightMap.put(norm.getClass_(), norm.getsHeight());
            } else {
                sBoysHeightMap.put(norm.getClass_(), norm.getsHeight());
            }
        }
    }


    public static void initializeXWeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                xGirlsWeightMap.put(norm.getClass_(), norm.getxWeight());
            } else {
                xBoysWeightMap.put(norm.getClass_(), norm.getxWeight());
            }
        }
    }

    public static void initializeXHeightMapping() {


        for (Norms norm : Norms.values()) {
            if (norm.getSex().equals("F")) {
                xGirlsHeightMap.put(norm.getClass_(), norm.getxHeight());
            } else {
                xBoysHeightMap.put(norm.getClass_(), norm.getxHeight());
            }
        }
    }

    public static void initCounters() {
        AnalysisUtil.weightIforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIIforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIIIUnderNormforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIIIOverNormforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIIforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIIIUnderNormforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIIIOverNormforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.weightIforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.weightIIforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.weightIIIUnderNormforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.weightIIIOverNormforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.weightIforAged14to18yearsBoys = new MutableInt(0);
        AnalysisUtil.weightIIforAged14to18yearsBoys = new MutableInt(0);
        AnalysisUtil.weightIIIUnderNormforAged14to18yearsBoys = new MutableInt(0);
        AnalysisUtil.weightIIIOverNormforAged14to18yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIIforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIIIUnderNormforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIIIOverNormforAged7to14yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIIforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIIIUnderNormforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIIIOverNormforAged14to18yearsGirls = new MutableInt(0);
        AnalysisUtil.heightIforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIIforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIIIUnderNormforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIIIOverNormforAged7to14yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIforAged14to18yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIIforAged14to18yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIIIUnderNormforAged14to18yearsBoys = new MutableInt(0);
        AnalysisUtil.heightIIIOverNormforAged14to18yearsBoys = new MutableInt(0);
    }
}
