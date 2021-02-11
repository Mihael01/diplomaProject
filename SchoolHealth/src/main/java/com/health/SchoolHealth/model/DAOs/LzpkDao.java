package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Lzpk;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Primary
@Transactional
public interface LzpkDao extends CrudRepository<Lzpk, Long> {

}