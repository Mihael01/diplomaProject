package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "diseases_and_abnorm_type")
public class DiseasesAndAbnormType {
    @Id
    @Column(name = "diseases_and_abnorm_type_code")
    private String diseasesAndAbnormTypeCode;

    @Column(name = "diseases_and_abnorm_type_name")
    private String diseasesAndAbnormTypeName;

    @OneToMany(mappedBy = "diseasesAndAbnormType")
    List<StudentDiseasesAndAbnormalities> studentDiseasesAndAbnormalities;

    public String getDiseasesAndAbnormTypeCode() {
        return this.diseasesAndAbnormTypeCode;
    }

    public void setDiseasesAndAbnormTypeCode(String diseasesAndAbnormTypeCode) {
        this.diseasesAndAbnormTypeCode = diseasesAndAbnormTypeCode;
    }

    public String getDiseasesAndAbnormTypeName() {
        return this.diseasesAndAbnormTypeName;
    }

    public void setDiseasesAndAbnormTypeName(String diseasesAndAbnormTypeName) {
        this.diseasesAndAbnormTypeName = diseasesAndAbnormTypeName;
    }

    public List<StudentDiseasesAndAbnormalities> getStudentDiseasesAndAbnormalities() {
        return studentDiseasesAndAbnormalities;
    }

    public void setStudentDiseasesAndAbnormalities(List<StudentDiseasesAndAbnormalities> studentDiseasesAndAbnormalities) {
        this.studentDiseasesAndAbnormalities = studentDiseasesAndAbnormalities;
    }
}
