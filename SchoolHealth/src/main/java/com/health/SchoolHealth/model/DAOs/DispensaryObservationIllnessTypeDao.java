package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.DispensaryObservationIllnessType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Primary
@Transactional
public interface DispensaryObservationIllnessTypeDao extends CrudRepository<DispensaryObservationIllnessType, Long> {

}