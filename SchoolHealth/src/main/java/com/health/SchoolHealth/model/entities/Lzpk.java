package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "lzpk")
public class Lzpk {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "lzpk_number")
    private Long lzpkNumber;

    @Column(name = "issue_date")
    private java.sql.Date issueDate;

    @Column(name = "issued_from")
    private String issuedFrom;

    @OneToOne(mappedBy = "lzpk")
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLzpkNumber() {
        return this.lzpkNumber;
    }

    public void setLzpkNumber(Long lzpkNumber) {
        this.lzpkNumber = lzpkNumber;
    }

    public java.sql.Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(java.sql.Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuedFrom() {
        return this.issuedFrom;
    }

    public void setIssuedFrom(String issuedFrom) {
        this.issuedFrom = issuedFrom;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

