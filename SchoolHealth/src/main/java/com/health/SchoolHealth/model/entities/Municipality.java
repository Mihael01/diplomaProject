package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "municipality")
public class Municipality {
    @Id
    @Column(name = "municipality_code")
    private String municipalityCode;

    @Column(name = "municipality_name")
    private String municipalityName;

    public String getMunicipalityCode() {
        return this.municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }
}
