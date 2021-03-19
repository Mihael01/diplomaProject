package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Transactional
@Repository
public interface SchoolDao extends CrudRepository<School, Integer> {

    @Query("select s from School as s left join SchoolType as st on s.schoolType.id = st.id " +
            "join SettlementPlace as sp on s.schoolAddress.settlementPlace.ekatte = sp.ekatte " +
            "where s.schoolName = :schoolName and s.schoolNumber = :schoolNumber and sp.ekatte = :ekatte")
    public Iterable<School> findAllSchoolsBySchoolName(String schoolName, String schoolNumber, Long ekatte);

    @Query("select s from School as s where s.schoolMedics.id = :medicId")
    public Iterable<School> findAllSchoolsBySchoolMedic(Long medicId) ;

}