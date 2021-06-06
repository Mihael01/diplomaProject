package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SchoolMedicsDao;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SchoolMedicsService {

    private final SchoolMedicsDao schoolMedicsDao;



    public SchoolMedicsService(SchoolMedicsDao schoolMedicsDao) {
        this.schoolMedicsDao = schoolMedicsDao;
    }

    public SchoolMedics getSchoolMedic(Long schoolMedicId) {
        return schoolMedicId == null ? null: schoolMedicsDao.findById(schoolMedicId).orElse(null);
    }

    public SchoolMedics createOrUpdateSchoolMedic(SchoolMedics schoolMedic) {
        if (schoolMedic.getId() == null) {
            schoolMedic.setActiveFrom(new Date(System.currentTimeMillis()));
        }
        return schoolMedicsDao.save(schoolMedic);
    }

    public List<SchoolMedics> findAllSchoolMedics() {
        return  RepositoryUtil.iterableToList(schoolMedicsDao.findAllSchoolMedicsWithUser());
    }

    public List<SchoolMedics> findSchoolMedicByTelephoneNumber(String telephoneNumber) {
        return  RepositoryUtil.iterableToList(schoolMedicsDao.findSchoolMedicWithUserByTelephoneNumber(telephoneNumber));
    }

    public void deleteSchoolMedicById(Long schoolMedicId) {
        if (schoolMedicId != null) {
            schoolMedicsDao.deleteById(schoolMedicId);
        }
    }

    public SchoolMedics getSchoolMedicByUserId(Long userId) {
        return schoolMedicsDao.findSchoolMedicByUserId(userId).orElse(null);
    }

}
