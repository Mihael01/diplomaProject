package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class AddressForm {

    String addressAbout;

    @Autowired
    Address address;

    @Autowired
    Address address2;

    Integer schoolId;

    Long studentId;

    Long parent1Id;

    Long parent2Id;

    List<Region> regions;

    String regionCode;

    String regionCode2;

    Boolean isMunicipalityEnable;

    List<Municipality> municipalities;

    Boolean isMunicipalityEnable2;

    List<Municipality> municipalities2;

    String municipalityCode;

    String municipalityCode2;

    Boolean isSettlementPlaceEnable;

    List<SettlementPlace> settlementPlaces;

    List<String> settlementPlacesNomenclature;

    Long settlementPlaceCode;

    Boolean isSettlementPlaceEnable2;

    List<SettlementPlace> settlementPlaces2;

    Long settlementPlaceCode2;

    //Флаг дали имаме записан притежател на адреса
    Boolean isOwnerNotPresent;

    Boolean isOwner2NotPresent;


}
