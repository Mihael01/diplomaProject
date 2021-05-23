package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.GPDao;
import com.health.SchoolHealth.model.DAOs.StudentDao;
import com.health.SchoolHealth.model.entities.GP;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPService {

    private final GPDao gpDao;

    private final StudentDao studentDao;

    public GPService(GPDao gpDao, StudentDao studentDao) {
        this.gpDao = gpDao;
        this.studentDao = studentDao;
    }

    public GP getGP(Long gpId) {
        return gpId == null ? null: gpDao.findById(gpId).orElse(null);
    }

    public GP getGpOfStudent(Long studentId) {
        return studentId == null ? null: gpDao.findGPByStudentId(studentId).orElse(null);
    }

    public GP createOrUpdateGP(GP gp) {

        return gpDao.save(gp);
    }

    public List<Student> getAllStudentsOfGP(Long gpId) {
        return RepositoryUtil.iterableToList(studentDao.findAllStudentsByGp(gpId));
    }

    public List<GP> findAllGPs() {
        return  RepositoryUtil.iterableToList(gpDao.findAllGPsWithUser());
    }

    public List<GP> findGPByTelephoneNumber(String telephoneNumber) {
        return  RepositoryUtil.iterableToList(gpDao.findGPsWithUserByTelephoneNumber(telephoneNumber));
    }

    public void deleteGPById(Long gpId) {
        if (gpId != null) {
            gpDao.deleteById(gpId);
        }
    }

}
