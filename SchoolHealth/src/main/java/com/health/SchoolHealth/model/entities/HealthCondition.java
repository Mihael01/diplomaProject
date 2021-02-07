package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "health_condition")
public class HealthCondition {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "examination_date")
    private java.sql.Date examinationDate;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment_place")
    private String treatmentPlace;

    @Column(name = "treatment_result")
    private String treatmentResult;

    @Column(name = "additional_activities")
    private String additionalActivities;

    @Column(name = "additional_activities_description")
    private String additionalActivitiesDescription;

    @Column(name = "exempt_from_physical_education")
    private String exemptFromPhysicalEducation;

    @Column(name = "therapeutic_physical_education")
    private String therapeuticPhysicalEducation;

    @Column(name = "dispensarysation")
    private String dispensarysation;

    @Column(name = "observer")
    private String observer;

    @Column(name = "missing_immunization_flag")
    private String missingImmunizationFlag;

    @Column(name = "mandatory_immunization_flag")
    private String mandatoryImmunizationFlag;

    @Column(name = "parasites")
    private String parasites;

    @Column(name = "student_id")
    private Long studentId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Date getExaminationDate() {
        return this.examinationDate;
    }

    public void setExaminationDate(java.sql.Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentPlace() {
        return this.treatmentPlace;
    }

    public void setTreatmentPlace(String treatmentPlace) {
        this.treatmentPlace = treatmentPlace;
    }

    public String getTreatmentResult() {
        return this.treatmentResult;
    }

    public void setTreatmentResult(String treatmentResult) {
        this.treatmentResult = treatmentResult;
    }

    public String getAdditionalActivities() {
        return this.additionalActivities;
    }

    public void setAdditionalActivities(String additionalActivities) {
        this.additionalActivities = additionalActivities;
    }

    public String getAdditionalActivitiesDescription() {
        return this.additionalActivitiesDescription;
    }

    public void setAdditionalActivitiesDescription(String additionalActivitiesDescription) {
        this.additionalActivitiesDescription = additionalActivitiesDescription;
    }

    public String getExemptFromPhysicalEducation() {
        return this.exemptFromPhysicalEducation;
    }

    public void setExemptFromPhysicalEducation(String exemptFromPhysicalEducation) {
        this.exemptFromPhysicalEducation = exemptFromPhysicalEducation;
    }

    public String getTherapeuticPhysicalEducation() {
        return this.therapeuticPhysicalEducation;
    }

    public void setTherapeuticPhysicalEducation(String therapeuticPhysicalEducation) {
        this.therapeuticPhysicalEducation = therapeuticPhysicalEducation;
    }

    public String getDispensarysation() {
        return this.dispensarysation;
    }

    public void setDispensarysation(String dispensarysation) {
        this.dispensarysation = dispensarysation;
    }

    public String getObserver() {
        return this.observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getMissingImmunizationFlag() {
        return this.missingImmunizationFlag;
    }

    public void setMissingImmunizationFlag(String missingImmunizationFlag) {
        this.missingImmunizationFlag = missingImmunizationFlag;
    }

    public String getMandatoryImmunizationFlag() {
        return this.mandatoryImmunizationFlag;
    }

    public void setMandatoryImmunizationFlag(String mandatoryImmunizationFlag) {
        this.mandatoryImmunizationFlag = mandatoryImmunizationFlag;
    }

    public String getParasites() {
        return this.parasites;
    }

    public void setParasites(String parasites) {
        this.parasites = parasites;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
