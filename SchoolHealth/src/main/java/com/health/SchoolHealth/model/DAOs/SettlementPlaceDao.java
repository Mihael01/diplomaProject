package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Municipality;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.model.entities.SettlementPlace;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Primary
@Transactional
public interface SettlementPlaceDao extends CrudRepository<SettlementPlace, Long> {

    @Query("select sp from SettlementPlace as sp where sp.municipality.municipalityCode = :municipalityCode order by  sp.settlementPlaceType, sp.settlementPlaceName")
    public Iterable<SettlementPlace> findAllSettlementPlacesByMunicipality(String municipalityCode);

}