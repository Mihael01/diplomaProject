package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "sex_type")
public class SexType {
    @Id
    @Column(name = "sex_type_code")
    private String sexTypeCode;

    @Column(name = "sex_type_name")
    private String sexTypeName;

    public String getSexTypeCode() {
        return this.sexTypeCode;
    }

    public void setSexTypeCode(String sexTypeCode) {
        this.sexTypeCode = sexTypeCode;
    }

    public String getSexTypeName() {
        return this.sexTypeName;
    }

    public void setSexTypeName(String sexTypeName) {
        this.sexTypeName = sexTypeName;
    }
}
