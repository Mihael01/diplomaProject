package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "school_number")
    private String schoolNumber;

    @Column(name = "school_type")
    private String schoolType;

    @Column(name = "school_type_other")
    private String schoolTypeOther;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_telephone_number")
    private String schoolTelephoneNumber;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "school_medics_id")
    private Long schoolMedicsId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolNumber() {
        return this.schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getSchoolType() {
        return this.schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolTypeOther() {
        return this.schoolTypeOther;
    }

    public void setSchoolTypeOther(String schoolTypeOther) {
        this.schoolTypeOther = schoolTypeOther;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolTelephoneNumber() {
        return this.schoolTelephoneNumber;
    }

    public void setSchoolTelephoneNumber(String schoolTelephoneNumber) {
        this.schoolTelephoneNumber = schoolTelephoneNumber;
    }

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getSchoolMedicsId() {
        return this.schoolMedicsId;
    }

    public void setSchoolMedicsId(Long schoolMedicsId) {
        this.schoolMedicsId = schoolMedicsId;
    }
}
