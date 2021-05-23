package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.GP;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.util.UserType;
import lombok.Data;

import java.util.List;

@Data
public class CreateSMForm {

    private SchoolMedics schoolMedic;

    private List<SchoolMedics> allSchoolMedics;

    private String telephoneNumber;

    private List<String> telephoneNumbers;

}
