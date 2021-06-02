package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.StudentParasitDao;
import com.health.SchoolHealth.model.entities.StudentParasit;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentParasiteService {

    private final StudentParasitDao studentParasitDao;


    public StudentParasiteService(StudentParasitDao studentParasitDao) {
        this.studentParasitDao = studentParasitDao;
    }


    public List<StudentParasit> getParasitesByStudent(Long studentId) {
        return RepositoryUtil.iterableToList(studentParasitDao.findParasitesByStudentId(studentId));
    }

    public List<StudentParasit> getStudentParasitByParasiteCodeAndStudentId(String parasitTypeCode, Long studentId) {

        return RepositoryUtil.iterableToList(studentParasitDao.findStudentParasitByParasiteCodeAndStudentId(parasitTypeCode, studentId));
    }

    public void deleteGPById(Long parasitTypeId) {
        if (parasitTypeId != null) {
            studentParasitDao.deleteById(parasitTypeId);
        }
    }

    public StudentParasit createOrUpdateStudentParasit(StudentParasit studentParasit) {

        return studentParasitDao.save(studentParasit);
    }
}
