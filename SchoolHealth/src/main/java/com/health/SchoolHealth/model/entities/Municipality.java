package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "municipality")
public class Municipality {
    @Id
    @Column(name = "municipality_code")
    private String municipalityCode;

    @Column(name = "municipality_name")
    private String municipalityName;

    @OneToMany(mappedBy = "municipality")
    private List<SettlementPlace> settlementPlaces;

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

    public List<SettlementPlace> getSettlementPlaces() {
        return settlementPlaces;
    }

    public void setSettlementPlaces(List<SettlementPlace> settlementPlaces) {
        this.settlementPlaces = settlementPlaces;
    }
}
