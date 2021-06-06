package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.DiseasesAndAbnormTypeDao;
import com.health.SchoolHealth.model.entities.DiseasesAndAbnormType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseasesAndAbnormTypeService {

    private final DiseasesAndAbnormTypeDao diseasesAndAbnormTypeDao;


    public DiseasesAndAbnormTypeService(DiseasesAndAbnormTypeDao diseasesAndAbnormTypeDao) {

        this.diseasesAndAbnormTypeDao = diseasesAndAbnormTypeDao;
    }

    public List<DiseasesAndAbnormType> getAllDiseasesAndAbnormTypes() {
//        return RepositoryUtil.iterableToList(diseasesAndAbnormTypeDao.findAllOrderByCode());
        return diseasesAndAbnormTypeDao.findAllOrderByCode();
    }

    public DiseasesAndAbnormType getDiseasesAndAbnormTypeByCode(String diseasesAndAbnormTypeCode) {
        return diseasesAndAbnormTypeDao.findByCode(diseasesAndAbnormTypeCode).orElse(null);
    }

}
