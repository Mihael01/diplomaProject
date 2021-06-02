package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.StudentDiseasesAndAbnormalities;
import com.health.SchoolHealth.model.entities.StudentDispensaryObservation;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface StudentDispensaryObservationDao extends CrudRepository<StudentDispensaryObservation, Long> {

    @Query("select sdo from StudentDispensaryObservation as sdo where sdo.student.id=:studentId")
    public Iterable<StudentDispensaryObservation> findAllStudentDispensaryObservationsByStudentId(Long studentId);

    @Query("select sdo from StudentDispensaryObservation as sdo join Student s on sdo.student.id = s.id " +
            "where sdo.student.id = :studentId and  s.school.id = :schoolId and " +
            "sdo.dispensaryObservIllnessType.dispensaryObservIllnessTypeCode = :dispensaryObservIllnessTypeCode")
    public Optional<StudentDispensaryObservation> findAllStudentDispensaryObservationsByStudentIdAndCode(Long studentId, String dispensaryObservIllnessTypeCode, Integer schoolId);

    @Query("select sdo from StudentDispensaryObservation as sdo join Student s on sdo.student.id = s.id " +
            "where s.class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'F'" +
            " and sdo.dispensaryObservIllnessType.dispensaryObservIllnessTypeCode = :dispensaryObservIllnessTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDispensaryObservation> findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged7to14(String dispensaryObservIllnessTypeCode, Integer schoolId);

    @Query("select sdo from StudentDispensaryObservation as sdo join Student s on sdo.student.id = s.id " +
            "where s.class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'M'" +
            " and sdo.dispensaryObservIllnessType.dispensaryObservIllnessTypeCode = :dispensaryObservIllnessTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDispensaryObservation> findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged7to14(String dispensaryObservIllnessTypeCode, Integer schoolId);

    @Query("select sdo from StudentDispensaryObservation as sdo join Student s on sdo.student.id = s.id " +
            "where s.class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'F'" +
            " and sdo.dispensaryObservIllnessType.dispensaryObservIllnessTypeCode = :dispensaryObservIllnessTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDispensaryObservation> findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged14to18(String dispensaryObservIllnessTypeCode, Integer schoolId);

    @Query("select sdo from StudentDispensaryObservation as sdo join Student s on sdo.student.id = s.id " +
            "where s.class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'M'" +
            " and sdo.dispensaryObservIllnessType.dispensaryObservIllnessTypeCode = :dispensaryObservIllnessTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDispensaryObservation> findNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged14to18(String dispensaryObservIllnessTypeCode, Integer schoolId);
}