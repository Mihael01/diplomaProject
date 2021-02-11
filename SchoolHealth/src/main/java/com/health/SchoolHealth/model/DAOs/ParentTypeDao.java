package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.ParentType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Primary
@Transactional
public interface ParentTypeDao extends CrudRepository<ParentType, Long> {

}