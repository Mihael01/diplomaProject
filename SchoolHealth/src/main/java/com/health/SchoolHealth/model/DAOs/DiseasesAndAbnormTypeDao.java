package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.DiseasesAndAbnormType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface DiseasesAndAbnormTypeDao extends CrudRepository<DiseasesAndAbnormType, Long> {

    @Query("select d from DiseasesAndAbnormType as d where d.diseasesAndAbnormTypeCode=:diseasesAndAbnormTypeCode")
    public Optional<DiseasesAndAbnormType> findByCode(String diseasesAndAbnormTypeCode);
}