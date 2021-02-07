package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_parasit")
public class StudentParasit {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "parasit_type_code")
    private String parasitTypeCode;

    @Column(name = "student_id")
    private Long studentId;

    public String getParasitTypeCode() {
        return this.parasitTypeCode;
    }

    public void setParasitTypeCode(String parasitTypeCode) {
        this.parasitTypeCode = parasitTypeCode;
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
