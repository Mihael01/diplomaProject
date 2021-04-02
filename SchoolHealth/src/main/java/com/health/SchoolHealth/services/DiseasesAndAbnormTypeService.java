package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.DiseasesAndAbnormTypeDao;
import com.health.SchoolHealth.model.entities.*;
import com.health.SchoolHealth.util.FormUtil;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseasesAndAbnormTypeService {

    private final DiseasesAndAbnormTypeDao diseasesAndAbnormTypeDao;


    public DiseasesAndAbnormTypeService(DiseasesAndAbnormTypeDao diseasesAndAbnormTypeDao) {

        this.diseasesAndAbnormTypeDao = diseasesAndAbnormTypeDao;
    }

    public List<DiseasesAndAbnormType> getAllDiseasesAndAbnormTypes() {
        return RepositoryUtil.iterableToList(diseasesAndAbnormTypeDao.findAll());
    }

    public DiseasesAndAbnormType getDiseasesAndAbnormTypeByCode(String diseasesAndAbnormTypeCode) {
        return diseasesAndAbnormTypeDao.findByCode(diseasesAndAbnormTypeCode).orElse(null);
    }

}
