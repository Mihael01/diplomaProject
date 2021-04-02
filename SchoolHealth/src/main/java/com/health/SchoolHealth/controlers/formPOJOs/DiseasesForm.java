package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.*;
import lombok.Data;

import java.util.List;

@Data
public class DiseasesForm {


    List<StudentDiseasesAndAbnormalities> studentDiseasesAndAbnormalities;

    List<DiseasesAndAbnormType> diseasesAndAbnormTypes;

    String diseasesAndAbnormTypeCode;

    List<StudentDispensaryObservation> studentDispensaryObservations;

    List<DispensaryObservationIllnessType> dispensaryObservationIllnessTypes;

    String dispensaryObservIllnessTypeCode;
}
