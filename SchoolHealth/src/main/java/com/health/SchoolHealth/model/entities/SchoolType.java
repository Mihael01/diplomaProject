package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "school_type")
public class SchoolType {
    @Id
    @Column(name = "school_type_code")
    private String schoolTypeCode;

    @Column(name = "school_type_name")
    private String schoolTypeName;

    @OneToOne(mappedBy = "schoolType")
    private School school;

    public String getSchoolTypeCode() {
        return this.schoolTypeCode;
    }

    public void setSchoolTypeCode(String schoolTypeCode) {
        this.schoolTypeCode = schoolTypeCode;
    }

    public String getSchoolTypeName() {
        return this.schoolTypeName;
    }

    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
