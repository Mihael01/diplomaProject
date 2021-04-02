package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.StudentDiseasesAndAbnormalitiesDao;
import com.health.SchoolHealth.model.DAOs.StudentDispensaryObservationDao;
import com.health.SchoolHealth.model.entities.StudentDiseasesAndAbnormalities;
import com.health.SchoolHealth.model.entities.StudentDispensaryObservation;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDispensaryObservationService {

    private final StudentDispensaryObservationDao studentDispensaryObservationDao;


    public StudentDispensaryObservationService(StudentDispensaryObservationDao studentDispensaryObservationDao) {

        this.studentDispensaryObservationDao = studentDispensaryObservationDao;
    }

    public List<StudentDispensaryObservation> getAllDispensaryObservationByStudentId(Long studentId) {
        return RepositoryUtil.iterableToList(studentDispensaryObservationDao.findAllStudentDispensaryObservationsByStudentId(studentId));
    }

    public StudentDispensaryObservation createOrUpdateStudentDispensaryObservation(StudentDispensaryObservation studentDispensaryObservation) {
        return studentDispensaryObservation == null? null : studentDispensaryObservationDao.save(studentDispensaryObservation);
    }

    public Long getCountByStudentIdAndDispensaryObservIllnessTypeCode(Long studentId, String dispensaryObservIllnessTypeCode) {
        Long count = 0L;

        if (studentDispensaryObservationDao.findAllStudentDispensaryObservationsByStudentIdAndCode(studentId, dispensaryObservIllnessTypeCode).isPresent()) {
            count = 1L;
        }
        return count;
    }

    public StudentDispensaryObservation findByStudentIdAndDispensaryObservIllnessTypeCode(Long studentId, String dispensaryObservIllnessTypeCode) {

        return studentDispensaryObservationDao.findAllStudentDispensaryObservationsByStudentIdAndCode(studentId, dispensaryObservIllnessTypeCode).orElse(null);
    }

    public void deleteStudentDispensaryObservationById(Long studentDispensaryObservationId) {

        studentDispensaryObservationDao.deleteById(studentDispensaryObservationId);
    }

}
