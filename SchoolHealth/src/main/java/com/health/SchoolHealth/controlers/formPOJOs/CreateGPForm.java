package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.GP;
import com.health.SchoolHealth.model.entities.Student;
import lombok.Data;

import java.util.List;

@Data
public class CreateGPForm {

    private GP gp;

    private List<GP> allGPs;

    private String gpTelephoneNumber;
}
