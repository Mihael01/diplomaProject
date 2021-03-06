package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "school_type")
public class SchoolType {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "school_type_code")
    private String schoolTypeCode;

    @Column(name = "school_type_name")
    private String schoolTypeName;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
