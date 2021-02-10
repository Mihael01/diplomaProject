package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "examination")
public class Examination {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "examination_date")
    private java.sql.Date examinationDate;

    @Column(name = "examination_place")
    private String examinationPlace;

    @Column(name = "examination_specialist")
    private String examinationSpecialist;

    @Column(name = "examination_data")
    private String examinationData;

    @Column(name = "mental_development")
    private String mentalDevelopment;

    @Column(name = "new_illness")
    private String newIllness;

    @Column(name = "paraclinical_examinations")
    private String paraclinicalExaminations;

    @ManyToOne
    @JoinColumn(name = "health_condition_id")
    private HealthCondition healthCondition;

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

    public String getExaminationPlace() {
        return this.examinationPlace;
    }

    public void setExaminationPlace(String examinationPlace) {
        this.examinationPlace = examinationPlace;
    }

    public String getExaminationSpecialist() {
        return this.examinationSpecialist;
    }

    public void setExaminationSpecialist(String examinationSpecialist) {
        this.examinationSpecialist = examinationSpecialist;
    }

    public String getExaminationData() {
        return this.examinationData;
    }

    public void setExaminationData(String examinationData) {
        this.examinationData = examinationData;
    }

    public String getMentalDevelopment() {
        return this.mentalDevelopment;
    }

    public void setMentalDevelopment(String mentalDevelopment) {
        this.mentalDevelopment = mentalDevelopment;
    }

    public String getNewIllness() {
        return this.newIllness;
    }

    public void setNewIllness(String newIllness) {
        this.newIllness = newIllness;
    }

    public String getParaclinicalExaminations() {
        return this.paraclinicalExaminations;
    }

    public void setParaclinicalExaminations(String paraclinicalExaminations) {
        this.paraclinicalExaminations = paraclinicalExaminations;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(HealthCondition healthCondition) {
        this.healthCondition = healthCondition;
    }
}
