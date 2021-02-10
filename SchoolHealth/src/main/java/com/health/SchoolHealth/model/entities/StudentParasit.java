package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_parasit")
public class StudentParasit {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parasit_type_code")
    private ParasitType parasitType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParasitType getParasitType() {
        return parasitType;
    }

    public void setParasitType(ParasitType parasitType) {
        this.parasitType = parasitType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
