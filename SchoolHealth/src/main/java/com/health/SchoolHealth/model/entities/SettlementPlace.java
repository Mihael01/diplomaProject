package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "settlement_place")
public class SettlementPlace {
    @Id
    @Column(name = "ekatte")
    private Long ekatte;

    @Column(name = "settlement_place_type")
    private String settlementPlaceType;

    @Column(name = "settlement_place_name")
    private String settlementPlaceName;

    @Column(name = "municipality_code")
    private String municipalityCode;

    @Column(name = "region_code")
    private String regionCode;

    @OneToOne(mappedBy = "settlementPlace")
    private Address address;

    public Long getEkatte() {
        return this.ekatte;
    }

    public void setEkatte(Long ekatte) {
        this.ekatte = ekatte;
    }

    public String getSettlementPlaceType() {
        return this.settlementPlaceType;
    }

    public void setSettlementPlaceType(String settlementPlaceType) {
        this.settlementPlaceType = settlementPlaceType;
    }

    public String getSettlementPlaceName() {
        return this.settlementPlaceName;
    }

    public void setSettlementPlaceName(String settlementPlaceName) {
        this.settlementPlaceName = settlementPlaceName;
    }

    public String getMunicipalityCode() {
        return this.municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
