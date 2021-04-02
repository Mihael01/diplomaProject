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

    @Query("select sdo from StudentDispensaryObservation as sdo where sdo.student.id = :studentId and  " +
            "sdo.dispensaryObservIllnessType.dispensaryObservIllnessTypeCode = :dispensaryObservIllnessTypeCode")
    public Optional<StudentDispensaryObservation> findAllStudentDispensaryObservationsByStudentIdAndCode(Long studentId, String dispensaryObservIllnessTypeCode);

}