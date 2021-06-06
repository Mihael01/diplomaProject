package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.AddressDao;
import com.health.SchoolHealth.model.DAOs.MunicipalityDao;
import com.health.SchoolHealth.model.DAOs.RegionDao;
import com.health.SchoolHealth.model.DAOs.SettlementPlaceDao;
import com.health.SchoolHealth.model.entities.Address;
import com.health.SchoolHealth.model.entities.Municipality;
import com.health.SchoolHealth.model.entities.Region;
import com.health.SchoolHealth.model.entities.SettlementPlace;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressDao addressDao;

    private final RegionDao regionDao;

    private final MunicipalityDao municipalityDao;

    private final SettlementPlaceDao settlementPlaceDao;

    public AddressService(AddressDao addressDao, RegionDao regionDao, MunicipalityDao municipalityDao, SettlementPlaceDao settlementPlaceDao) {
        this.addressDao = addressDao;
        this.regionDao = regionDao;
        this.municipalityDao = municipalityDao;
        this.settlementPlaceDao = settlementPlaceDao;
    }

    public Address createOrUpdateAddress(Address address) {
        return addressDao.save(address);
    }

    public Address findAddressWithSettlementPlaceByAddressId(Long addressId) {
        return addressId == null ? null: addressDao.findAddressWithSettlementPlaceByAddressId(addressId).orElse(null);
    }

    public List<Region> getAllRegions() {
        return regionDao.findAllRegionsOrderedByName();
    }

    public Region getRegionByRegionCode(String regionCode){
        return regionDao.findById(regionCode).orElse(null);
    }

    public List<Municipality> getAllMunicipalitiesInRegion(String regionCode) {
        return RepositoryUtil.iterableToList(municipalityDao.findAllMunicipalitiesByRegion(regionCode));
    }

    public List<SettlementPlace> getAllSettlementPlacesByMunicipality(String municipalityCode) {
        return RepositoryUtil.iterableToList(settlementPlaceDao.findAllSettlementPlacesByMunicipality(municipalityCode));
    }

}
