package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "confirmation_flag")
public class ConfirmationFlag {
    @Id
    @Column(name = "confirmation_flag_code")
    private String confirmationFlagCode;

    @Column(name = "confirmation_flag_value")
    private String confirmationFlagValue;

    public String getConfirmationFlagCode() {
        return this.confirmationFlagCode;
    }

    public void setConfirmationFlagCode(String confirmationFlagCode) {
        this.confirmationFlagCode = confirmationFlagCode;
    }

    public String getConfirmationFlagValue() {
        return this.confirmationFlagValue;
    }

    public void setConfirmationFlagValue(String confirmationFlagValue) {
        this.confirmationFlagValue = confirmationFlagValue;
    }
}
