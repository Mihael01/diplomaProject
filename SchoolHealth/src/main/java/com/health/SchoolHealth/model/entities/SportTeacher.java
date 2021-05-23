package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "sport_teacher")
public class SportTeacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sport_teacher_name")
    private String sportTeacherName;

    @Column(name = "sport_teacher_telephone_number")
    private String sportTeacherTelephoneNumber;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School sportTeacherSchool;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSportTeacherName() {
        return sportTeacherName;
    }

    public void setSportTeacherName(String sportTeacherName) {
        this.sportTeacherName = sportTeacherName;
    }

    public String getSportTeacherTelephoneNumber() {
        return sportTeacherTelephoneNumber;
    }

    public void setSportTeacherTelephoneNumber(String sportTeacherTelephoneNumber) {
        this.sportTeacherTelephoneNumber = sportTeacherTelephoneNumber;
    }

    public School getSportTeacherSchool() {
        return sportTeacherSchool;
    }

    public void setSportTeacherSchool(School sportTeacherSchool) {
        this.sportTeacherSchool = sportTeacherSchool;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
