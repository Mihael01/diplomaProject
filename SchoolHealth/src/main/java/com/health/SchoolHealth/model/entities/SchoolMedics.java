package com.health.SchoolHealth.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "school_medics")
public class SchoolMedics {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "school_medics_name")
    private String schoolMedicsName;

    @Column(name = "school_medics_telephone_number")
    private String schoolMedicsTelephoneNumber;

    @Column(name = "active_from")
    private java.sql.Date activeFrom;

    @Column(name = "active_to")
    private java.sql.Date activeTo;

    @OneToMany(mappedBy = "schoolMedics")
    private List<School> schools;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolMedicsName() {
        return this.schoolMedicsName;
    }

    public void setSchoolMedicsName(String schoolMedicsName) {
        this.schoolMedicsName = schoolMedicsName;
    }

    public String getSchoolMedicsTelephoneNumber() {
        return this.schoolMedicsTelephoneNumber;
    }

    public void setSchoolMedicsTelephoneNumber(String schoolMedicsTelephoneNumber) {
        this.schoolMedicsTelephoneNumber = schoolMedicsTelephoneNumber;
    }

    public java.sql.Date getActiveFrom() {
        return this.activeFrom;
    }

    public void setActiveFrom(java.sql.Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public java.sql.Date getActiveTo() {
        return this.activeTo;
    }

    public void setActiveTo(java.sql.Date activeTo) {
        this.activeTo = activeTo;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
}
