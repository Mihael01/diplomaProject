package com.health.SchoolHealth.model.entities;

import com.health.SchoolHealth.util.ValidEmail;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_code")
   // @NotNull
    @NotEmpty(message = "Потребителското име е задължително поле.")
    private String userCode;

    @Column(name = "password")
   // @NotNull
    @NotEmpty(message = "Паролата е задължително поле.")
    private String password;

//    @Column(name = "matching_password")
//    @NotNull
//    @NotEmpty(message = "Потвърждаването на паролата е задължително.")
//    private String matchingPassword;


    @Column(name = "email")
//    @ValidEmail(message = "E-mail е задължително поле. Моля, въведете валиден e-mail!")
    private String email;

    @Column(name = "user_type")
//    @NotNull
//    @NotEmpty(message = "Видът на потребителя е задължително поле. Изберете вид, точно съответстващ на Вашата същност!")
    private Integer userType;

    @Column(name = "enable")
    private String enable;

    @OneToOne(mappedBy = "user")
    private SchoolMedics schoolMedics;


    @OneToOne(mappedBy = "user")
    private SportTeacher sportTeacher;


    @OneToOne(mappedBy = "user")
    private Parent parent;


    @OneToOne(mappedBy = "user")
    private GP gp;
//
//    @Column(name = "related_id")
//    private Long relatedId;

//    @OneToOne
//    @JoinColumn(name = "related_id", referencedColumnName = "id")
//    private Parent parentUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
//
//    public Long getRelatedId() {
//        return relatedId;
//    }
//
//    public void setRelatedId(Long relatedId) {
//        this.relatedId = relatedId;
//    }

//    public Parent getParentUser() {
//        return parentUser;
//    }
//
//    public void setParentUser(Parent parentUser) {
//        this.parentUser = parentUser;
//    }


    public SchoolMedics getSchoolMedics() {
        return schoolMedics;
    }

    public void setSchoolMedics(SchoolMedics schoolMedics) {
        this.schoolMedics = schoolMedics;
    }

    public SportTeacher getSportTeacher() {
        return sportTeacher;
    }

    public void setSportTeacher(SportTeacher sportTeacher) {
        this.sportTeacher = sportTeacher;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public GP getGp() {
        return gp;
    }

    public void setGp(GP gp) {
        this.gp = gp;
    }
}
