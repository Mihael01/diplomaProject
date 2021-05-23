package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "school")
public class School {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "school_number")
    private String schoolNumber;

    @ManyToOne
    @JoinColumn(name = "school_type", referencedColumnName = "id")
    private SchoolType schoolType;

    @Column(name = "school_type_other")
    private String schoolTypeOther;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_telephone_number")
    private String schoolTelephoneNumber;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address schoolAddress;

    @ManyToOne
    @JoinColumn(name = "school_medics_id", referencedColumnName = "id")
    private SchoolMedics schoolMedics;

    @OneToMany(mappedBy = "sportTeacherSchool")
    private List<SportTeacher> sportTeachers;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolNumber() {
        return this.schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public SchoolType getSchoolType() {
        return this.schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolTypeOther() {
        return this.schoolTypeOther;
    }

    public void setSchoolTypeOther(String schoolTypeOther) {
        this.schoolTypeOther = schoolTypeOther;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolTelephoneNumber() {
        return this.schoolTelephoneNumber;
    }

    public void setSchoolTelephoneNumber(String schoolTelephoneNumber) {
        this.schoolTelephoneNumber = schoolTelephoneNumber;
    }

    public Address getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(Address address) {
        this.schoolAddress = address;
    }

    public SchoolMedics getSchoolMedics() {
        return schoolMedics;
    }

    public void setSchoolMedics(SchoolMedics schoolMedics) {
        this.schoolMedics = schoolMedics;
    }

    public List<SportTeacher> getSportTeachers() {
        return sportTeachers;
    }

    public void setSportTeachers(List<SportTeacher> sportTeachers) {
        this.sportTeachers = sportTeachers;
    }
}
