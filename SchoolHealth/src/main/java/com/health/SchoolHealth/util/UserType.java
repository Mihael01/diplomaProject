package com.health.SchoolHealth.util;

import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum UserType {

    SYS_ADMIN("Системен администратор", "0"),
    GP_ADMIN("Администратор за личните лекари","1"),
    SCHOOL_ADMIN("Администратор на училище","2"),
    SCHOOL_MEDIC("Училищно медицинско лице", "3"),
    GP("Личен лекар", "4"),
    PARENT("Родител", "5"),
    SPORT_TEACHER("Учител по ФВС", "6");

    private final String description;

    private final String code;



    UserType(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }



}