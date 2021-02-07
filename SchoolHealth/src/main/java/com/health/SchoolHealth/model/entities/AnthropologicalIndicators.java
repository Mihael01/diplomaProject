package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "anthropological_indicators")
public class AnthropologicalIndicators {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "body_height")
    private BigDecimal bodyHeight;

    @Column(name = "body_weight")
    private BigDecimal bodyWeight;

    @Column(name = "body_circumference")
    private BigDecimal bodyCircumference;

    @Column(name = "student_id")
    private Long studentId;

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

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
