package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.ParasitType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface ParasitTypeDao extends CrudRepository<ParasitType, Long> {

    @Query("select pt from ParasitType as pt where pt.parasitTypeCode=:parasitTypeCode")
    public Optional<ParasitType> findParasitTypeByCode(String parasitTypeCode);
}