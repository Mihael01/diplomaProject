package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.StudentDiseasesAndAbnormalitiesDao;
import com.health.SchoolHealth.model.entities.StudentDiseasesAndAbnormalities;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDiseasesAndAbnormService {

    private final StudentDiseasesAndAbnormalitiesDao studentDiseasesAndAbnormalitiesDao;


    public StudentDiseasesAndAbnormService(StudentDiseasesAndAbnormalitiesDao studentDiseasesAndAbnormalitiesDao) {

        this.studentDiseasesAndAbnormalitiesDao = studentDiseasesAndAbnormalitiesDao;
    }

    public List<StudentDiseasesAndAbnormalities> getAllDiseasesAndAbnormByStudentId(Long studentId) {
        return RepositoryUtil.iterableToList(studentDiseasesAndAbnormalitiesDao.findAllStudentDiseasesAndAbnormalitiesByStudentId(studentId));
    }

    public StudentDiseasesAndAbnormalities createOrUpdateStudentDiseasesAndAbnormalities(StudentDiseasesAndAbnormalities studentDiseasesAndAbnormalities) {
        return studentDiseasesAndAbnormalities == null? null : studentDiseasesAndAbnormalitiesDao.save(studentDiseasesAndAbnormalities);
    }

    public Long findByStudentIdAndDiseasesAndAbnormCode(Long studentId, String diseasesAndAbnormTypeCode) {
        return studentDiseasesAndAbnormalitiesDao.findAllStudentDiseasesAndAbnormalitiesByStudentIdAndCode(studentId, diseasesAndAbnormTypeCode).orElse(null);
    }

    public void deleteStudentDiseasesAndAbnormById(Long studentDiseasesAndAbnormId) {

        studentDiseasesAndAbnormalitiesDao.deleteById(studentDiseasesAndAbnormId);
    }

}
