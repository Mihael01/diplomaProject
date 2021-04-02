package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.Immunization;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface ImmunizationDao extends CrudRepository<Immunization, Long> {


    @Query("select i from Immunization as i where i.healthCondition.id = :healthConditionId")
    public Iterable<Immunization> findAllImmunizationByHealthConditionId(Long healthConditionId);
}