package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.Address;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface AddressDao extends CrudRepository<Address, Long> {

    @Query("select a from Address as a join SettlementPlace as sp on a.settlementPlace.ekatte = sp.ekatte " +
            "join Region as r on sp.region.regionCode = r.regionCode " +
            "join Municipality as m on sp.municipality.municipalityCode = m.municipalityCode " +
            "where a.id = :addressId order by sp.settlementPlaceName")
    public Optional<Address> findAddressWithSettlementPlaceByAddressId(Long addressId);

}