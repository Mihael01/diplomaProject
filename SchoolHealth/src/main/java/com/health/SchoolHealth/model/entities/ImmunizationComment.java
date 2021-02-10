package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "immunization_comment")
public class ImmunizationComment {
    @Id
    @Column(name = "immunization_comment_code")
    private String immunizationCommentCode;

    @Column(name = "immunization_comment_value")
    private String immunizationCommentValue;

    @OneToOne(mappedBy = "mandatoryImmunizationFlag")
    private HealthCondition mandatoryImmunizationFlag;

    public String getImmunizationCommentCode() {
        return this.immunizationCommentCode;
    }

    public void setImmunizationCommentCode(String immunizationCommentCode) {
        this.immunizationCommentCode = immunizationCommentCode;
    }

    public String getImmunizationCommentValue() {
        return this.immunizationCommentValue;
    }

    public void setImmunizationCommentValue(String immunizationCommentValue) {
        this.immunizationCommentValue = immunizationCommentValue;
    }

    public HealthCondition getMandatoryImmunizationFlag() {
        return mandatoryImmunizationFlag;
    }

    public void setMandatoryImmunizationFlag(HealthCondition mandatoryImmunizationFlag) {
        this.mandatoryImmunizationFlag = mandatoryImmunizationFlag;
    }

}
