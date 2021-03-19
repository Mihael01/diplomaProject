package com.health.SchoolHealth.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "lzpk")
public class Lzpk {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lzpk_number")
    private Long lzpkNumber;

    @Column(name = "issue_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String issueDate;

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

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
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

