package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "addictions")
public class Addictions {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "addictions_description")
    private String addictionsDescription;

    @Column(name = "methodologies")
    private String methodologies;

    @Column(name = "results")
    private String results;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddictionsDescription() {
        return this.addictionsDescription;
    }

    public void setAddictionsDescription(String addictionsDescription) {
        this.addictionsDescription = addictionsDescription;
    }

    public String getMethodologies() {
        return this.methodologies;
    }

    public void setMethodologies(String methodologies) {
        this.methodologies = methodologies;
    }

    public String getResults() {
        return this.results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
