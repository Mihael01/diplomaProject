package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_diseases_and_abnormalities")
public class StudentDiseasesAndAbnormalities {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diseases_and_abnorm_type_code")
    private DiseasesAndAbnormType diseasesAndAbnormType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiseasesAndAbnormType getDiseasesAndAbnormType() {
        return diseasesAndAbnormType;
    }

    public void setDiseasesAndAbnormType(DiseasesAndAbnormType diseasesAndAbnormType) {
        this.diseasesAndAbnormType = diseasesAndAbnormType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
