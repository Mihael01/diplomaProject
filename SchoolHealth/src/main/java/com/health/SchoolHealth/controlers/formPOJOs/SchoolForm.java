package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.SchoolType;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class SchoolForm {

    String settlementPlaceType;

//    @Autowired
    School school;

    List<SchoolType> schoolTypes;

    SchoolMedics schoolMedics;
}
