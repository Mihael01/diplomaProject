package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Addictions;
import com.health.SchoolHealth.model.entities.AnthropologicalIndicators;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface AddictionsDao extends CrudRepository<Addictions, Long> {

    @Query("select a from Addictions as a where a.student.id=:studentId")
    public Optional<Addictions> findAddictionsByStudentId(Long studentId);

}