package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.SchoolType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Primary
@Transactional
public interface SchoolTypeDao extends CrudRepository<SchoolType, Long> {

}