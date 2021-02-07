package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_dispensary_observation")
public class StudentDispensaryObservation {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "dispensary_observ_illness_type_code")
    private String dispensaryObservIllnessTypeCode;

    @Column(name = "student_id")
    private Long studentId;

    public String getDispensaryObservIllnessTypeCode() {
        return this.dispensaryObservIllnessTypeCode;
    }

    public void setDispensaryObservIllnessTypeCode(String dispensaryObservIllnessTypeCode) {
        this.dispensaryObservIllnessTypeCode = dispensaryObservIllnessTypeCode;
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
