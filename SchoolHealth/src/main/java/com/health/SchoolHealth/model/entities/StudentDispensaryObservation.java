package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_dispensary_observation")
public class StudentDispensaryObservation {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dispensary_observ_illness_type_code")
    private DispensaryObservationIllnessType dispensaryObservIllnessType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DispensaryObservationIllnessType getDispensaryObservIllnessType() {
        return dispensaryObservIllnessType;
    }

    public void setDispensaryObservIllnessType(DispensaryObservationIllnessType dispensaryObservIllnessType) {
        this.dispensaryObservIllnessType = dispensaryObservIllnessType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
