package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.DAOs.SchoolMedicsDao;
import com.health.SchoolHealth.model.DAOs.SchoolTypeDao;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.SchoolType;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolMedicsService {

    private final SchoolMedicsDao schoolMedicsDao;



    public SchoolMedicsService(SchoolMedicsDao schoolMedicsDao) {
        this.schoolMedicsDao = schoolMedicsDao;
    }

    public SchoolMedics getSchoolMedic(Long schoolMedicId) {
        return schoolMedicId == null ? null: schoolMedicsDao.findById(schoolMedicId).orElse(null);
    }

    public void createOrUpdateSchoolMedic(SchoolMedics schoolMedic) {
        if (schoolMedic.getId() == null) {
            schoolMedic.setActiveFrom(new Date(System.currentTimeMillis()));
        }
        schoolMedicsDao.save(schoolMedic);
    }


}
