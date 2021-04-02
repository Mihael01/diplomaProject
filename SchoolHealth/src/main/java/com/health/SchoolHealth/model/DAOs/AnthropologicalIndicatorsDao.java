package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.AnthropologicalIndicators;
import com.health.SchoolHealth.model.entities.StudentDiseasesAndAbnormalities;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface AnthropologicalIndicatorsDao extends CrudRepository<AnthropologicalIndicators, Long> {

    @Query("select ai from AnthropologicalIndicators as ai where ai.student.id=:studentId")
    public Optional<AnthropologicalIndicators> findAnthropologicalIndicatorsByStudentId(Long studentId);
}