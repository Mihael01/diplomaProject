package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.DiseasesAndAbnormType;
import com.health.SchoolHealth.model.entities.DispensaryObservationIllnessType;
import lombok.Data;

@Data
public class DiseasesAndAbnormResults {

    private DiseasesAndAbnormType diseasesAndAbnormType;

    private Integer numberOfStudentDiseasesAndAbnormalities;

    private Integer numberOfIClass;

    private Integer numberOfVIIClass;

    private Integer numberOfXClass;
}
