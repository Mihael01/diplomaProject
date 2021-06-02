package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.StudentDispensaryObservationDao;
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

    public Long getCountByStudentIdAndDispensaryObservIllnessTypeCode(Long studentId, String dispensaryObservIllnessTypeCode, Integer schoolId) {
        Long count = 0L;

        if (studentDispensaryObservationDao.findAllStudentDispensaryObservationsByStudentIdAndCode(studentId, dispensaryObservIllnessTypeCode, schoolId).isPresent()) {
            count = 1L;
        }
        return count;
    }

    public StudentDispensaryObservation getAllStudentDispensaryObservationsByStudentIdAndCode(Long studentId, String dispensaryObservIllnessTypeCode, Integer schoolId) {

        return studentDispensaryObservationDao.findAllStudentDispensaryObservationsByStudentIdAndCode(studentId, dispensaryObservIllnessTypeCode, schoolId).orElse(null);
    }

    public Integer getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged7to14(String dispensaryObservIllnessTypeCode, Integer schoolId) {
        List<StudentDispensaryObservation> result = RepositoryUtil.iterableToList(studentDispensaryObservationDao.findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged7to14(dispensaryObservIllnessTypeCode, schoolId));
        if (result != null) {
            return result.size();
        }
        return 0;
    }


    public Integer getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged7to14(String dispensaryObservIllnessTypeCode, Integer schoolId) {
        List<StudentDispensaryObservation> result = RepositoryUtil.iterableToList(studentDispensaryObservationDao.findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged7to14(dispensaryObservIllnessTypeCode, schoolId));
        if (result != null) {
            return result.size();
        }
        return 0;
    }

    public Integer getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged14to18(String dispensaryObservIllnessTypeCode, Integer schoolId) {
        List<StudentDispensaryObservation> result = RepositoryUtil.iterableToList(studentDispensaryObservationDao.findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged14to18(dispensaryObservIllnessTypeCode, schoolId));
        if (result != null) {
            return result.size();
        }
        return 0;
    }


    public Integer getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged14to18(String dispensaryObservIllnessTypeCode, Integer schoolId) {
        List<StudentDispensaryObservation> result = RepositoryUtil.iterableToList(studentDispensaryObservationDao.findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged14to18(dispensaryObservIllnessTypeCode, schoolId));
        if (result != null) {
            return result.size();
        }
        return 0;
    }


    public void deleteStudentDispensaryObservationById(Long studentDispensaryObservationId) {

        studentDispensaryObservationDao.deleteById(studentDispensaryObservationId);
    }

}
