package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_name")
    private String parentName;

    @ManyToOne
    @JoinColumn(name = "parent_type_code", referencedColumnName = "parent_type_code")
    private ParentType parentType;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address parentAddress;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "place_of_work")
    private String placeOfWork;

    @Column(name = "business_telephone_number")
    private String businessTelephoneNumber;

    @OneToMany(mappedBy = "parent")
    List<StudentParent> studentParents;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPlaceOfWork() {
        return this.placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getBusinessTelephoneNumber() {
        return this.businessTelephoneNumber;
    }

    public void setBusinessTelephoneNumber(String businessTelephoneNumber) {
        this.businessTelephoneNumber = businessTelephoneNumber;
    }

    public Address getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(Address parentAddress) {
        this.parentAddress = parentAddress;
    }

    public List<StudentParent> getStudentParents() {
        return studentParents;
    }

    public void setStudentParents(List<StudentParent> studentParents) {
        this.studentParents = studentParents;
    }

    public ParentType getParentType() {
        return parentType;
    }

    public void setParentType(ParentType parentType) {
        this.parentType = parentType;
    }
}
