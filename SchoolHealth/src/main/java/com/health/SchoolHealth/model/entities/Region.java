package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "region_name")
    private String regionName;

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
