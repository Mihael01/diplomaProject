package com.health.SchoolHealth.util;

public enum Norms {

    I_CLASS_GIRLS("I", "F", 4.1, 25.2, 13.0, 19.0, 22.0, 5.5, 124.6),
    II_CLASS_GIRLS("II", "F", 5.4, 27.7, 13.0, 19.0, 22.0, 6.3, 129.4),
    III_CLASS_GIRLS("III", "F",5.3, 31.0, 13.87, 19.07, 22.81, 6.4, 135.6),
    IV_CLASS_GIRLS("IV", "F", 7.5, 35.6, 14.20, 19.86, 24.11, 7.0, 141.4),
    V_CLASS_GIRLS("V", "F", 7.8, 39.3, 14.60, 20.74, 25.42, 7.5, 147.2),
    VI_CLASS_GIRLS("VI", "F", 8.6, 45.4, 14.90, 21.68, 26.67, 6.9, 153.1),
    VII_CLASS_GIRLS("VII", "F", 9.2, 49.9, 15.40, 22.58, 27.76, 6.6, 157.2),
    VIII_CLASS_GIRLS("VIII", "F", 8.4, 53.8, 15.70, 23.34, 28.57, 5.8, 159.2),
    IX_CLASS_GIRLS("IX", "F", 8.2, 56.6, 16.00, 23.94, 29.11, 5.6, 159.9),
    X_CLASS_GIRLS("X", "F", 7.7, 57.3, 16.40, 24.37, 29.43, 6.1, 160.7),
    XI_CLASS_GIRLS("XI", "F", 8.1, 58.1, 16.60, 24.70, 29.69, 5.8, 161.2),
    XII_CLASS_GIRLS("XII", "F", 8.3, 58.4, 16.70, 25.00, 30.00, 6.0, 161.4),
 
    I_CLASS_BOYS("I", "M", 4.2, 25.4, 14.00, 19.0, 22.0, 6.0, 125.0),
    II_CLASS_BOYS("II", "M", 5.00, 28.00, 14.0, 19.0, 22.0, 7.3, 130.6),
    III_CLASS_BOYS("III", "M", 5.5, 31.3, 14.03, 19.10, 22.77, 6.4, 135.2),
    IV_CLASS_BOYS("IV", "M", 6.5, 35.2, 14.40, 19.84, 24.00, 6.4, 140.9),
    V_CLASS_BOYS("V", "M", 7.4, 38.6, 14.80, 20.55, 25.10, 7.5, 145.8),
    VI_CLASS_BOYS("VI", "M", 8.7, 43.3, 15.20, 21.22, 26.02, 8.0, 151.0),
    VII_CLASS_BOYS("VII", "M", 10.1, 49.7, 15.70, 21.92, 26.84, 8.4, 158.3),
    VIII_CLASS_BOYS("VIII", "M", 10.5, 56.2, 16.20, 22.62, 27.63, 8.1, 165.6),
    IX_CLASS_BOYS("IX", "M", 10.1, 60.4, 16.60, 23.29, 28.30, 7.7, 169.6),
    X_CLASS_BOYS("X", "M", 9.4, 64.5, 17.00, 23.30, 28.88, 7.1, 172.3),
    XI_CLASS_BOYS("XI", "M", 8.9, 67.9, 17.30, 24.46, 29.41, 6.4, 173.8),
    XII_CLASS_BOYS("XII", "M", 7.9, 69.6, 17.50, 25.00, 30.00, 6.3, 172.3);

    private final String class_;

    private final String sex;

    private final Double sWeight;

    private final Double xWeight;

    private final Double subnormITM;

    private final Double overnormITM;

    private final Double obesityITM;

    private final Double sHeight;

    private final Double xHeight;

    Norms(String class_, String sex, Double sWeight, Double xWeight, Double subnormITM, Double overnormITM, Double obesityITM, Double sHeight, Double xHeight) {
        this.class_ = class_;
        this.sex = sex;
        this.sWeight = sWeight;
        this.xWeight = xWeight;
        this.subnormITM = subnormITM;
        this.overnormITM = overnormITM;
        this.obesityITM = obesityITM;
        this.sHeight = sHeight;
        this.xHeight = xHeight;
    }

    public String getClass_() {
        return class_;
    }

    public String getSex() {
        return sex;
    }

    public Double getsWeight() {
        return sWeight;
    }

    public Double getxWeight() {
        return xWeight;
    }

    public Double getSubnormITM() {
        return subnormITM;
    }

    public Double getOvernormITM() {
        return overnormITM;
    }

    public Double getObesityITM() {
        return obesityITM;
    }

    public Double getsHeight() {
        return sHeight;
    }

    public Double getxHeight() {
        return xHeight;
    }


    //    public static Double getSWeightByClassAndSex(String class_, String sex) {
//        return
//    }
//    public static void initializeSWeightMapping() {
//
//
//        for (Norms norm : Norms.values()) {
//                if (norm.getSex().equals("F")) {
//                    sGirlsWeigtMap.put(norm.class_, norm.sWeight);
//                } else {
//                    sBoysWeigtMap.put(norm.class_, norm.sWeight);
//                }
//        }
//    }
//    public static HashMap<String, Double> initializeSWeigtMapping() {
//        HashMap<String, Double> sGirlsWeigtMap = new HashMap<>();
//
//        for (Norms norm : Norms.values()) {
//            if (norm.getSex().equals("M")) {
//                sBoysWeigtMap.put(norm.class_, norm.sWeight);
//            }
//        }
//        return sGirlsWeigtMap;
//    }


}