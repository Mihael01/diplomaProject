package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "immunization")
public class Immunization {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "immunization_date")
    private java.sql.Date immunizationDate;

    @Column(name = "immunization_name")
    private String immunizationName;

    @Column(name = "health_condition_id")
    private Long healthConditionId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Date getImmunizationDate() {
        return this.immunizationDate;
    }

    public void setImmunizationDate(java.sql.Date immunizationDate) {
        this.immunizationDate = immunizationDate;
    }

    public String getImmunizationName() {
        return this.immunizationName;
    }

    public void setImmunizationName(String immunizationName) {
        this.immunizationName = immunizationName;
    }

    public Long getHealthConditionId() {
        return this.healthConditionId;
    }

    public void setHealthConditionId(Long healthConditionId) {
        this.healthConditionId = healthConditionId;
    }
}
