package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.DispensaryObservationIllnessType;
import lombok.Data;

import java.util.List;

@Data
public class Analysis2Form {

    private List<DispensaryObservationIllnessTypesResults> dispensaryObservationIllnessTypesResults;

}
