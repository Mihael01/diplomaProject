package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Addictions;
import com.health.SchoolHealth.model.entities.TestTable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface TestDao extends CrudRepository<TestTable, Long> {

}