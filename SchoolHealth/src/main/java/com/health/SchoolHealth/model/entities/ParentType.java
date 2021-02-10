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

    @OneToOne(mappedBy = "parentTypeCode")
    private Parent parent;

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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
