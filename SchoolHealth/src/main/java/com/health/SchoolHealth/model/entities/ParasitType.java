package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parasit_type")
public class ParasitType {
    @Id
    @Column(name = "parasit_type_code")
    private String parasitTypeCode;

    @Column(name = "parasit_type_name")
    private String parasitTypeName;

    @OneToMany(mappedBy = "parasitType")
    List<StudentParasit> studentParasits;

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

    public List<StudentParasit> getStudentParasits() {
        return studentParasits;
    }

    public void setStudentParasits(List<StudentParasit> studentParasits) {
        this.studentParasits = studentParasits;
    }
}
