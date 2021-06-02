package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.ParasitType;
import com.health.SchoolHealth.model.entities.StudentParasit;
import lombok.Data;

import java.util.List;

@Data
public class ParasitsForm {

    private List<StudentParasit> studentParasites;

    List<ParasitType> parasiteTypes;

    String parasiteTypeCode;

}
