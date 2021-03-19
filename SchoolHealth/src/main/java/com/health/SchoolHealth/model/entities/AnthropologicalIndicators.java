package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "anthropological_indicators")
public class AnthropologicalIndicators {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body_height")
    private BigDecimal bodyHeight;

    @Column(name = "body_weight")
    private BigDecimal bodyWeight;

    @Column(name = "body_circumference")
    private BigDecimal bodyCircumference;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBodyHeight() {
        return this.bodyHeight;
    }

    public void setBodyHeight(BigDecimal bodyHeight) {
        this.bodyHeight = bodyHeight;
    }

    public BigDecimal getBodyWeight() {
        return this.bodyWeight;
    }

    public void setBodyWeight(BigDecimal bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public BigDecimal getBodyCircumference() {
        return this.bodyCircumference;
    }

    public void setBodyCircumference(BigDecimal bodyCircumference) {
        this.bodyCircumference = bodyCircumference;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
