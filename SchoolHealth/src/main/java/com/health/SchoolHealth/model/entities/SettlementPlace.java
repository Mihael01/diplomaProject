package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "municipality_code", referencedColumnName = "municipality_code")
    private Municipality municipality;

    @ManyToOne
    @JoinColumn(name = "region_code", referencedColumnName = "region_code")
    private Region region;

    @OneToMany(mappedBy = "settlementPlace")
    private List<Address> address;

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

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
