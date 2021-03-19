package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.DAOs.SexTypeDao;
import com.health.SchoolHealth.model.DAOs.StudentDao;
import com.health.SchoolHealth.model.entities.SexType;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexTypeService {

    private final SexTypeDao sexTypeDao;


    public SexTypeService(SexTypeDao sexTypeDao) {
        this.sexTypeDao = sexTypeDao;
    }

    public List<SexType> findAllSexTypes() {
        return RepositoryUtil.iterableToList(sexTypeDao.findAll());
    }


}
