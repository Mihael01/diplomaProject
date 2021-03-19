package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parent_type")
public class ParentType {
    @Id
    @Column(name = "parent_type_code")
    private String parentTypeCode;

    @Column(name = "parent_type_name")
    private String parentTypeName;

    @OneToMany(mappedBy = "parentType")
    private List<Parent> parents;

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

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}
