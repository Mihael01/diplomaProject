package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "class_")
    private String class_;

    @Column(name = "class_letter")
    private String classLetter;

    @Column(name = "school_id")
    private Long schoolId;

    @Column(name = "egn")
    private Long egn;

    @Column(name = "birth_date")
    private java.sql.Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "sex")
    private String sex;

    @Column(name = "family_burden")
    private String familyBurden;

    @Column(name = "past_illnesses")
    private String pastIllnesses;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "rh")
    private String rh;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "gp_id")
    private Long gpId;

    @Column(name = "lzpk_id")
    private Long lzpkId;

    @OneToOne(mappedBy = "student")
    private Addictions addictions;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClass_() {
        return this.class_;
    }

    public void setClass(String class_) {
        this.class_ = class_;
    }

    public String getClassLetter() {
        return this.classLetter;
    }

    public void setClassLetter(String classLetter) {
        this.classLetter = classLetter;
    }

    public Long getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getEgn() {
        return this.egn;
    }

    public void setEgn(Long egn) {
        this.egn = egn;
    }

    public java.sql.Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(java.sql.Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFamilyBurden() {
        return this.familyBurden;
    }

    public void setFamilyBurden(String familyBurden) {
        this.familyBurden = familyBurden;
    }

    public String getPastIllnesses() {
        return this.pastIllnesses;
    }

    public void setPastIllnesses(String pastIllnesses) {
        this.pastIllnesses = pastIllnesses;
    }

    public String getBloodType() {
        return this.bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRh() {
        return this.rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getAllergies() {
        return this.allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Long getGpId() {
        return this.gpId;
    }

    public void setGpId(Long gpId) {
        this.gpId = gpId;
    }

    public Long getLzpkId() {
        return this.lzpkId;
    }

    public void setLzpkId(Long lzpkId) {
        this.lzpkId = lzpkId;
    }

    public Addictions getAddictions() {
        return addictions;
    }

    public void setAddictions(Addictions addictions) {
        this.addictions = addictions;
    }
}
