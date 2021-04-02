package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.Immunization;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.SchoolType;
import lombok.Data;

import java.util.List;

@Data
public class ImmunizationForm {

    Immunization immunization;

    List<Immunization> allImmunizations;
}
