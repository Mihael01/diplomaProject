package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "dispensary_observation_illness_type")
public class DispensaryObservationIllnessType {
    @Id
    @Column(name = "dispensary_observ_illness_type_code")
    private String dispensaryObservIllnessTypeCode;

    @Column(name = "dispensary_observ_illness_type_name")
    private String dispensaryObservIllnessTypeName;

    public String getDispensaryObservIllnessTypeCode() {
        return this.dispensaryObservIllnessTypeCode;
    }

    public void setDispensaryObservIllnessTypeCode(String dispensaryObservIllnessTypeCode) {
        this.dispensaryObservIllnessTypeCode = dispensaryObservIllnessTypeCode;
    }

    public String getDispensaryObservIllnessTypeName() {
        return this.dispensaryObservIllnessTypeName;
    }

    public void setDispensaryObservIllnessTypeName(String dispensaryObservIllnessTypeName) {
        this.dispensaryObservIllnessTypeName = dispensaryObservIllnessTypeName;
    }
}
