package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "parasit_type")
public class ParasitType {
    @Id
    @Column(name = "parasit_type_code")
    private String parasitTypeCode;

    @Column(name = "parasit_type_name")
    private String parasitTypeName;

    public String getParasitTypeCode() {
        return this.parasitTypeCode;
    }

    public void setParasitTypeCode(String parasitTypeCode) {
        this.parasitTypeCode = parasitTypeCode;
    }

    public String getParasitTypeName() {
        return this.parasitTypeName;
    }

    public void setParasitTypeName(String parasitTypeName) {
        this.parasitTypeName = parasitTypeName;
    }
}
