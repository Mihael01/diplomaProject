package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "confirmation_flag")
public class ConfirmationFlag {
    @Id
    @Column(name = "confirmation_flag_code")
    private String confirmationFlagCode;

    @Column(name = "confirmation_flag_value")
    private String confirmationFlagValue;

    @OneToMany(mappedBy = "additionalActivities")
    private List<HealthCondition> additionalActivities;

    @OneToMany(mappedBy = "exemptFromPhysicalEducation")
    private List<HealthCondition> exemptFromPhysicalEducation;

    @OneToMany(mappedBy = "missingImmunizationFlag")
    private List<HealthCondition> missingImmunizationFlag;

    @OneToMany(mappedBy = "therapeuticPhysicalEducation")
    private List<HealthCondition> therapeuticPhysicalEducation;

    @OneToMany(mappedBy = "parasites")
    private List<HealthCondition> parasites;


    public String getConfirmationFlagCode() {
        return confirmationFlagCode;
    }

    public void setConfirmationFlagCode(String confirmationFlagCode) {
        this.confirmationFlagCode = confirmationFlagCode;
    }

    public String getConfirmationFlagValue() {
        return confirmationFlagValue;
    }

    public void setConfirmationFlagValue(String confirmationFlagValue) {
        this.confirmationFlagValue = confirmationFlagValue;
    }

    public List<HealthCondition> getAdditionalActivities() {
        return additionalActivities;
    }

    public void setAdditionalActivities(List<HealthCondition> additionalActivities) {
        this.additionalActivities = additionalActivities;
    }

    public List<HealthCondition> getExemptFromPhysicalEducation() {
        return exemptFromPhysicalEducation;
    }

    public void setExemptFromPhysicalEducation(List<HealthCondition> exemptFromPhysicalEducation) {
        this.exemptFromPhysicalEducation = exemptFromPhysicalEducation;
    }

    public List<HealthCondition> getMissingImmunizationFlag() {
        return missingImmunizationFlag;
    }

    public void setMissingImmunizationFlag(List<HealthCondition> missingImmunizationFlag) {
        this.missingImmunizationFlag = missingImmunizationFlag;
    }

    public List<HealthCondition> getTherapeuticPhysicalEducation() {
        return therapeuticPhysicalEducation;
    }

    public void setTherapeuticPhysicalEducation(List<HealthCondition> therapeuticPhysicalEducation) {
        this.therapeuticPhysicalEducation = therapeuticPhysicalEducation;
    }

    public List<HealthCondition> getParasites() {
        return parasites;
    }

    public void setParasites(List<HealthCondition> parasites) {
        this.parasites = parasites;
    }
}
