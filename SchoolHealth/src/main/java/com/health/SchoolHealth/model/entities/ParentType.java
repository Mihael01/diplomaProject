package com.health.SchoolHealth.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "parent_type")
public class ParentType {
    @Id
    @Column(name = "parent_type_code")
    private String parentTypeCode;

    @Column(name = "parent_type_name")
    private String parentTypeName;

    public String getParentTypeCode() {
        return this.parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode;
    }

    public String getParentTypeName() {
        return this.parentTypeName;
    }

    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName;
    }
}
