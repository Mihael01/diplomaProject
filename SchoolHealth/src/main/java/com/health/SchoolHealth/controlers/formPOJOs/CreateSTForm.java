package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.SportTeacher;
import lombok.Data;

import java.util.List;

@Data
public class CreateSTForm {

    private SportTeacher sportTeacher;

    private List<SportTeacher> allSportTeachers;

    private String telephoneNumber;

    private List<String> telephoneNumbers;

}
