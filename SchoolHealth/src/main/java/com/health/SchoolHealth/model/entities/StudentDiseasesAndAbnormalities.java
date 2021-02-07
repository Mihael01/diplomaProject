package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_diseases_and_abnormalities")
public class StudentDiseasesAndAbnormalities {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "diseases_and_abnorm_type_code")
    private String diseasesAndAbnormTypeCode;

    @Column(name = "student_id")
    private Long studentId;

    public String getDiseasesAndAbnormTypeCode() {
        return this.diseasesAndAbnormTypeCode;
    }

    public void setDiseasesAndAbnormTypeCode(String diseasesAndAbnormTypeCode) {
        this.diseasesAndAbnormTypeCode = diseasesAndAbnormTypeCode;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
