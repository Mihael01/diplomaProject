package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.SportTeacher;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Primary
@Transactional
public interface SportTeacherDao extends CrudRepository<SportTeacher, Long> {


    @Query("select st from SportTeacher as st join User as u on st.user.id=u.id")
    public Iterable<SportTeacher> findAllSportTeachersWithUser();

    @Query("select st from SportTeacher as st join User as u on st.user.id=u.id where st.sportTeacherTelephoneNumber=:sportTeacherTelephoneNumber")
    public Iterable<SportTeacher> findSportTeacherWithUserByTelephoneNumber(String sportTeacherTelephoneNumber);
}