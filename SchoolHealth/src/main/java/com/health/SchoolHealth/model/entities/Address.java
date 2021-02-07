package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "ekatte", referencedColumnName = "ekatte")
    private SettlementPlace settlementPlace;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public SettlementPlace getSettlementPlace() {
        return settlementPlace;
    }

    public void setSettlementPlace(SettlementPlace settlementPlace) {
        this.settlementPlace = settlementPlace;
    }
}
