package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.SettlementPlace;
import com.health.SchoolHealth.model.entities.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface StudentDao extends CrudRepository<Student, Long> {

    @Query("select st from Student as st left join Lzpk as lzpk on st.lzpk.id = lzpk.id where st.id = :studentId")
    public Optional<Student> findStudentWithLzpkById(Long studentId);

    //Да се добави ограничение по училище, след като започне да се записва училището при създаването на ученика
    @Query("select s from Student as s where s.class_=:class_ and s.classLetter=:classLetter")
    public Iterable<Student> findAllByClassAndClassLetter(String class_, String classLetter);

}