package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_type_code")
    private String parentTypeCode;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "place_of_work")
    private String placeOfWork;

    @Column(name = "business_telephone_number")
    private String businessTelephoneNumber;

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

    public String getParentTypeCode() {
        return this.parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode;
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
}
