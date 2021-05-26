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

}