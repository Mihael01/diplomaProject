package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.PhysicalCapacity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface PhysicalCapacityDao extends CrudRepository<PhysicalCapacity, Long> {

    @Query("select pc from PhysicalCapacity as pc where pc.student.id = :studentId")
    public Optional<PhysicalCapacity> findPhysicalCapacityByStudentId(Long studentId);

    @Query("select count(pc) from PhysicalCapacity as pc " +
            "join Student as s on pc.student.id = s.id where pc.physicalCapacityMark > 3 and s.school.id = :schoolId")
    public Optional<Integer> countStudentsHavingMarkGreaterThan3(Integer schoolId);

    @Query("select count(pc) from PhysicalCapacity as pc " +
            "join Student as s on pc.student.id = s.id  " +
            "where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'F'  and " +
            "pc.physicalCapacityMark > 3 and s.school.id = :schoolId")
    public Optional<Integer> countStudentsHavingMarkGreaterThan3GirlsFrom7to14FofSchool(Integer schoolId);

    @Query("select count(pc) from PhysicalCapacity as pc " +
            "join Student as s on pc.student.id = s.id  " +
            "where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'M'  and " +
            "pc.physicalCapacityMark > 3 and s.school.id = :schoolId")
    public Optional<Integer> countStudentsHavingMarkGreaterThan3BoysFrom7to14FofSchool(Integer schoolId);


    @Query("select count(pc) from PhysicalCapacity as pc " +
            "join Student as s on pc.student.id = s.id  " +
            "where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'F'  and " +
            "pc.physicalCapacityMark > 3 and s.school.id = :schoolId")
    public Optional<Integer> countStudentsHavingMarkGreaterThan3GirlsFrom14to18FofSchool(Integer schoolId);

    @Query("select count(pc) from PhysicalCapacity as pc " +
            "join Student as s on pc.student.id = s.id " +
            "where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'M'  and " +
            "pc.physicalCapacityMark > 3 and s.school.id = :schoolId")
    public Optional<Integer> countStudentsHavingMarkGreaterThan3BoysFrom14to18FofSchool(Integer schoolId);
}