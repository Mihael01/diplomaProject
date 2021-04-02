package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.DispensaryObservationIllnessType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface DispensaryObservationIllnessTypeDao extends CrudRepository<DispensaryObservationIllnessType, Long> {

    @Query("select d from DispensaryObservationIllnessType as d where d.dispensaryObservIllnessTypeCode=:dispensaryObservIllnessTypeCode")
    public Optional<DispensaryObservationIllnessType> findByCode(String dispensaryObservIllnessTypeCode);

}