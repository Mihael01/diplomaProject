package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.SchoolMedics;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface SchoolMedicsDao extends CrudRepository<SchoolMedics, Long> {

    @Query("select sm from SchoolMedics as sm join User as u on sm.user.id=u.id")
    public Iterable<SchoolMedics> findAllSchoolMedicsWithUser();

    @Query("select sm from SchoolMedics as sm join User as u on sm.user.id=u.id where sm.schoolMedicsTelephoneNumber=:schoolMedicsTelephoneNumber")
    public Iterable<SchoolMedics> findSchoolMedicWithUserByTelephoneNumber(String schoolMedicsTelephoneNumber);

    @Query("select sm from SchoolMedics as sm join User as u on sm.user.id = u.id where u.id = :userId")
    public Optional<SchoolMedics> findSchoolMedicByUserId(Long userId);

}