package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gp")
public class GP {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gp_name")
    private String gpName;

    @Column(name = "gp_telephone_number")
    private String gpTelephoneNumber;

    @Column(name = "medical_center")
    private String medicalCenter;

    @OneToMany(mappedBy = "gp")
    private List<Student> students;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGpName() {
        return this.gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getGpTelephoneNumber() {
        return this.gpTelephoneNumber;
    }

    public void setGpTelephoneNumber(String gpTelephoneNumber) {
        this.gpTelephoneNumber = gpTelephoneNumber;
    }

    public String getMedicalCenter() {
        return this.medicalCenter;
    }

    public void setMedicalCenter(String medicalCenter) {
        this.medicalCenter = medicalCenter;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
