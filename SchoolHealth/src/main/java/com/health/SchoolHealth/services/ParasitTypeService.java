package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.ParasitTypeDao;
import com.health.SchoolHealth.model.DAOs.SexTypeDao;
import com.health.SchoolHealth.model.entities.ParasitType;
import com.health.SchoolHealth.model.entities.SexType;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParasitTypeService {

    private final ParasitTypeDao parasitTypeDao;


    public ParasitTypeService(ParasitTypeDao parasitTypeDao) {
        this.parasitTypeDao = parasitTypeDao;
    }

    public List<ParasitType> findAllParasitTypes() {
        return RepositoryUtil.iterableToList(parasitTypeDao.findAll());
    }

    public ParasitType getParasitTypeByCode(String parasitTypeCode) {
        return parasitTypeDao.findParasitTypeByCode(parasitTypeCode).orElse(null);
    }
}
