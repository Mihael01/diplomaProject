package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface HealthConditionDao extends CrudRepository<HealthCondition, Long> {

    @Query("select hc from HealthCondition as hc where hc.student.id = :studentId")
    public Optional<HealthCondition> findHealthConditionByStudentId(Long studentId);


    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id where hc.exemptFromPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsExemptFromPhysicalEducation(Integer schoolId);

    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id  " +
            "where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'F'  and " +
            "hc.exemptFromPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsExemptFromPhysicalEducationGirlsFrom7to14FofSchool(Integer schoolId);

    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id  " +
            "where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'M'  and " +
            "hc.exemptFromPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsExemptFromPhysicalEducationBoysFrom7to14FofSchool(Integer schoolId);


    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id  " +
            "where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'F'  and " +
            "hc.exemptFromPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsExemptFromPhysicalEducationGirlsFrom14to18FofSchool(Integer schoolId);

    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id " +
            "where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'M'  and " +
            "hc.exemptFromPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsExemptFromPhysicalEducationBoysFrom14to18FofSchool(Integer schoolId);

    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id where hc.therapeuticPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsIncludInTherapeuticPhysicalEducation(Integer schoolId);

    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id  " +
            "where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'F'  and " +
            "hc.therapeuticPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom7to14FofSchool(Integer schoolId);

    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id  " +
            "where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'M'  and " +
            "hc.therapeuticPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsIncludInTherapeuticPhysicalEducationBoysFrom7to14FofSchool(Integer schoolId);


    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id  " +
            "where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'F'  and " +
            "hc.therapeuticPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom14to18FofSchool(Integer schoolId);

    @Query("select count(hc) from  HealthCondition as hc " +
            "join Student as s on hc.student.id = s.id " +
            "where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'M'  and " +
            "hc.therapeuticPhysicalEducation = 'Y' and s.school.id = :schoolId")
    public Optional<Integer> countStudentsIncludInTherapeuticPhysicalEducationBoysFrom14to18FofSchool(Integer schoolId);


//private ConfirmationFlag exemptFromPhysicalEducation;
//
//    @Column(name = "additional_activities_description")
//    private String additionalActivitiesDescription;

}