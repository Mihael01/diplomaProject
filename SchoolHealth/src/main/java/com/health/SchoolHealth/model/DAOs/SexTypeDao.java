package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.SexType;
import com.health.SchoolHealth.model.entities.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface SexTypeDao extends CrudRepository<SexType, Long> {

    @Query("select st from SexType as st where st.sexTypeCode = :sexTypeCode")
    public Optional<SexType> findSexTypeByCode(String sexTypeCode);


}