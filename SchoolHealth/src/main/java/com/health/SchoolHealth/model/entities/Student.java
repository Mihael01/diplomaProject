package com.health.SchoolHealth.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "class_")
    private String class_;

    @Column(name = "class_letter")
    private String classLetter;

    @Column(name = "student_number")
    private Integer studentNumber;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    @Column(name = "egn")
    private Long egn;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address studentAddress;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "sex", referencedColumnName = "sex_type_code")
    private SexType sexType;

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

    @ManyToOne
    @JoinColumn(name = "gp_id", referencedColumnName = "id")
    private Gp gp;

    @OneToOne
    @JoinColumn(name = "lzpk_id", referencedColumnName = "id")
    private Lzpk lzpk;

    @OneToOne(mappedBy = "student")
    private Addictions addictions;

    @OneToOne(mappedBy = "student")
    private HealthCondition healthCondition;

    @OneToOne(mappedBy = "student")
    private PhysicalCapacity physicalCapacity;

    //RELATION BETWEEN JOINING TABLES

    @OneToMany(mappedBy = "student")
    List<StudentParasit> studentParasits;

    @OneToMany(mappedBy = "student")
    List<StudentParent> studentParents;

    @OneToMany(mappedBy = "student")
    List<StudentDispensaryObservation> studentDispensaryObservations;

    @OneToMany(mappedBy = "student")
    List<StudentDiseasesAndAbnormalities> studentDiseasesAndAbnormalities;

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

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getClassLetter() {
        return this.classLetter;
    }

    public void setClassLetter(String classLetter) {
        this.classLetter = classLetter;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Long getEgn() {
        return this.egn;
    }

    public void setEgn(Long egn) {
        this.egn = egn;
    }

//    public java.util.Date getBirthDate() {
//        return this.birthDate;
//    }
//
//    public void setBirthDate(java.sql.Date birthDate) {
//        this.birthDate = birthDate;
//    }
//    public String getBirthDate() {
//        return this.birthDate != null ? this.birthDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
//                : null;
//    }

//    public void setBirthDate(String birthDate) {
//        this.birthDate = Date.valueOf(birthDate);
//    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public Addictions getAddictions() {
        return addictions;
    }

    public void setAddictions(Addictions addictions) {
        this.addictions = addictions;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(HealthCondition healthCondition) {
        this.healthCondition = healthCondition;
    }

    public PhysicalCapacity getPhysicalCapacity() {
        return physicalCapacity;
    }

    public void setPhysicalCapacity(PhysicalCapacity physicalCapacity) {
        this.physicalCapacity = physicalCapacity;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Address getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(Address studentAddress) {
        this.studentAddress = studentAddress;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public Gp getGp() {
        return gp;
    }

    public void setGp(Gp gp) {
        this.gp = gp;
    }

    public Lzpk getLzpk() {
        return lzpk;
    }

    public void setLzpk(Lzpk lzpk) {
        this.lzpk = lzpk;
    }

    public List<StudentParasit> getStudentParasits() {
        return studentParasits;
    }

    public void setStudentParasits(List<StudentParasit> studentParasits) {
        this.studentParasits = studentParasits;
    }

    public List<StudentParent> getStudentParents() {
        return studentParents;
    }

    public void setStudentParents(List<StudentParent> studentParents) {
        this.studentParents = studentParents;
    }

    public List<StudentDispensaryObservation> getStudentDispensaryObservations() {
        return studentDispensaryObservations;
    }

    public void setStudentDispensaryObservations(List<StudentDispensaryObservation> studentDispensaryObservations) {
        this.studentDispensaryObservations = studentDispensaryObservations;
    }

    public List<StudentDiseasesAndAbnormalities> getStudentDiseasesAndAbnormalities() {
        return studentDiseasesAndAbnormalities;
    }

    public void setStudentDiseasesAndAbnormalities(List<StudentDiseasesAndAbnormalities> studentDiseasesAndAbnormalities) {
        this.studentDiseasesAndAbnormalities = studentDiseasesAndAbnormalities;
    }
}
