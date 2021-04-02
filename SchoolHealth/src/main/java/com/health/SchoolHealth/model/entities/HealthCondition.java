package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "health_condition")
public class HealthCondition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "examination_date")
    private java.sql.Date examinationDate;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment_place")
    private String treatmentPlace;

    @Column(name = "treatment_result")
    private String treatmentResult;

    @ManyToOne
    @JoinColumn(name = "additional_activities", referencedColumnName = "confirmation_flag_code")
    private ConfirmationFlag additionalActivities;

    @ManyToOne
    @JoinColumn(name = "exempt_from_physical_education", referencedColumnName = "confirmation_flag_code")
    private ConfirmationFlag exemptFromPhysicalEducation;

    @Column(name = "additional_activities_description")
    private String additionalActivitiesDescription;

    @ManyToOne
    @JoinColumn(name = "therapeutic_physical_education", referencedColumnName = "confirmation_flag_code")
    private ConfirmationFlag therapeuticPhysicalEducation;

    @Column(name = "dispensarysation")
    private String dispensarysation;

    @Column(name = "observer")
    private String observer;

    @ManyToOne
    @JoinColumn(name = "mandatory_immunization_flag", referencedColumnName = "immunization_comment_code")
    private ImmunizationComment mandatoryImmunizationFlag;

    @ManyToOne
    @JoinColumn(name = "missing_immunization_flag", referencedColumnName = "confirmation_flag_code")
    private ConfirmationFlag missingImmunizationFlag;

    @ManyToOne
    @JoinColumn(name = "parasites", referencedColumnName = "confirmation_flag_code")
    private ConfirmationFlag parasites;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToMany(mappedBy = "healthCondition")
    private List<Examination> examinations;

    @OneToMany(mappedBy = "healthCondition")
    private List<Immunization> immunizations;

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

    public String getAdditionalActivitiesDescription() {
        return this.additionalActivitiesDescription;
    }

    public void setAdditionalActivitiesDescription(String additionalActivitiesDescription) {
        this.additionalActivitiesDescription = additionalActivitiesDescription;
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

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

    public ConfirmationFlag getAdditionalActivities() {
        return additionalActivities;
    }

    public void setAdditionalActivities(ConfirmationFlag additionalActivities) {
        this.additionalActivities = additionalActivities;
    }

    public ConfirmationFlag getExemptFromPhysicalEducation() {
        return exemptFromPhysicalEducation;
    }

    public void setExemptFromPhysicalEducation(ConfirmationFlag exemptFromPhysicalEducation) {
        this.exemptFromPhysicalEducation = exemptFromPhysicalEducation;
    }

    public ConfirmationFlag getTherapeuticPhysicalEducation() {
        return therapeuticPhysicalEducation;
    }

    public void setTherapeuticPhysicalEducation(ConfirmationFlag therapeuticPhysicalEducation) {
        this.therapeuticPhysicalEducation = therapeuticPhysicalEducation;
    }

    public ConfirmationFlag getMissingImmunizationFlag() {
        return missingImmunizationFlag;
    }

    public void setMissingImmunizationFlag(ConfirmationFlag missingImmunizationFlag) {
        this.missingImmunizationFlag = missingImmunizationFlag;
    }

    public ConfirmationFlag getParasites() {
        return parasites;
    }

    public void setParasites(ConfirmationFlag parasites) {
        this.parasites = parasites;
    }

    public void setMandatoryImmunizationFlag(ImmunizationComment mandatoryImmunizationFlag) {
        this.mandatoryImmunizationFlag = mandatoryImmunizationFlag;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ImmunizationComment getMandatoryImmunizationFlag() {
        return mandatoryImmunizationFlag;
    }

    public List<Immunization> getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(List<Immunization> immunizations) {
        this.immunizations = immunizations;
    }
}
