package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Municipality;
import com.health.SchoolHealth.model.entities.School;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MunicipalityDao extends CrudRepository<Municipality, Long> {

@Query("select m from Municipality as m where m.municipalityCode in " +
        "(select distinct sp.municipality.municipalityCode from SettlementPlace as sp where  sp.region.regionCode = :regionCode)")
    public Iterable<Municipality> findAllMunicipalitiesByRegion(String regionCode); //"order by m.municipalityName"
}