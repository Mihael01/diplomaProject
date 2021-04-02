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

}