package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "physical_capacity")
public class PhysicalCapacity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "strength_left_hand")
    private String strengthLeftHand;

    @Column(name = "strength_right_hand")
    private String strengthRightHand;

    @Column(name = "running")
    private String running;

    @Column(name = "long_jump")
    private String longJump;

    @Column(name = "throwing_ball")
    private String throwingBall;

    @Column(name = "maximum_squats")
    private Long maximumSquats;

    @Column(name = "physical_capacity_mark")
    private Long physicalCapacityMark;

    @Column(name = "student_id")
    private Long studentId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrengthLeftHand() {
        return this.strengthLeftHand;
    }

    public void setStrengthLeftHand(String strengthLeftHand) {
        this.strengthLeftHand = strengthLeftHand;
    }

    public String getStrengthRightHand() {
        return this.strengthRightHand;
    }

    public void setStrengthRightHand(String strengthRightHand) {
        this.strengthRightHand = strengthRightHand;
    }

    public String getRunning() {
        return this.running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public String getLongJump() {
        return this.longJump;
    }

    public void setLongJump(String longJump) {
        this.longJump = longJump;
    }

    public String getThrowingBall() {
        return this.throwingBall;
    }

    public void setThrowingBall(String throwingBall) {
        this.throwingBall = throwingBall;
    }

    public Long getMaximumSquats() {
        return this.maximumSquats;
    }

    public void setMaximumSquats(Long maximumSquats) {
        this.maximumSquats = maximumSquats;
    }

    public Long getPhysicalCapacityMark() {
        return this.physicalCapacityMark;
    }

    public void setPhysicalCapacityMark(Long physicalCapacityMark) {
        this.physicalCapacityMark = physicalCapacityMark;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
