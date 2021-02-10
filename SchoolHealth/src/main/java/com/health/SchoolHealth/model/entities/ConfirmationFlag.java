package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "confirmation_flag")
public class ConfirmationFlag {
    @Id
    @Column(name = "confirmation_flag_code")
    private String confirmationFlagCode;

    @Column(name = "confirmation_flag_value")
    private String confirmationFlagValue;

    @OneToOne(mappedBy = "additionalActivities")
    private HealthCondition additionalActivities;

    @OneToOne(mappedBy = "exemptFromPhysicalEducation")
    private HealthCondition exemptFromPhysicalEducation;

    @OneToOne(mappedBy = "missingImmunizationFlag")
    private HealthCondition missingImmunizationFlag;

    @OneToOne(mappedBy = "therapeuticPhysicalEducation")
    private HealthCondition therapeuticPhysicalEducation;

    @OneToOne(mappedBy = "parasites")
    private HealthCondition parasites;


    public String getConfirmationFlagCode() {
        return this.confirmationFlagCode;
    }

    public void setConfirmationFlagCode(String confirmationFlagCode) {
        this.confirmationFlagCode = confirmationFlagCode;
    }

    public String getConfirmationFlagValue() {
        return this.confirmationFlagValue;
    }

    public void setConfirmationFlagValue(String confirmationFlagValue) {
        this.confirmationFlagValue = confirmationFlagValue;
    }

    public HealthCondition getAdditionalActivities() {
        return additionalActivities;
    }

    public void setAdditionalActivities(HealthCondition additionalActivities) {
        this.additionalActivities = additionalActivities;
    }

    public HealthCondition getExemptFromPhysicalEducation() {
        return exemptFromPhysicalEducation;
    }

    public void setExemptFromPhysicalEducation(HealthCondition exemptFromPhysicalEducation) {
        this.exemptFromPhysicalEducation = exemptFromPhysicalEducation;
    }

    public HealthCondition getMissingImmunizationFlag() {
        return missingImmunizationFlag;
    }

    public void setMissingImmunizationFlag(HealthCondition missingImmunizationFlag) {
        this.missingImmunizationFlag = missingImmunizationFlag;
    }
}
