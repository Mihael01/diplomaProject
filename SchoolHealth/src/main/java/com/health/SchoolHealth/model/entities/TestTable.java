package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "test_table")
public class TestTable {
    @Id
    @Column(name = "test_code")
    private String testCode;

    @Column(name = "test_name")
    private String testName;

    public String getTestCode() {
        return this.testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestName() {
        return this.testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
