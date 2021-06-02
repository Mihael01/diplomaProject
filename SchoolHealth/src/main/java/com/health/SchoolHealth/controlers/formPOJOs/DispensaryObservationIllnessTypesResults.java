package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.DispensaryObservationIllnessType;
import lombok.Data;

import java.util.List;

@Data
public class DispensaryObservationIllnessTypesResults {

    private DispensaryObservationIllnessType dispensaryObservationIllnessType;

    private Integer numberOfStudentDispensaryObservationsGirlsAged7to14;

    private Integer numberOfStudentDispensaryObservationsGirlsAged14to18;

    private Integer numberOfStudentDispensaryObservationsBoysAged7to14;

    private Integer numberOfStudentDispensaryObservationsBoysAged14to18;
}
