package com.health.SchoolHealth.model.DAOs;

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

    // Резултатът трябва да се вижда само от личния лекар! Училищните медицински лица нямат право да виждат ученици от други училища.
    @Query("select s from Student as s where s.gp.id=:gpId")
    public Iterable<Student> findAllStudentsByGp(Long gpId);


    //Да се добави ограничение по училище, след като започне да се записва училището при създаването на ученика
    //Да се използва за Общ брой паралелки в ІІ. Обслужван контингент!
   // Не позволява count(distinct ...) на повече от едно поле. Ще броим уникалните резултати в джавата
    @Query("select concat(s.class_, s.classLetter) from Student as s")
    public Iterable<String> countAllClassesFofSchool();


    //Да се добави ограничение по училище, след като започне да се записва училището при създаването на ученика
    //Да се използва за Общ брой ученици в ІІ. Обслужван контингент!
    @Query("select count(s) from Student as s")
    public Optional<Long> countAllStudentsFofSchool();

    //Да се добави ограничение по училище, след като започне да се записва училището при създаването на ученика
    //Да се използва за ІІ. Обслужван контингент!
    @Query("select count(s) from Student as s where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'M'")
    public Optional<Long> countAllBoysFrom7to14FofSchool();

    //Да се добави ограничение по училище, след като започне да се записва училището при създаването на ученика
    //Да се използва за ІІ. Обслужван контингент!
    @Query("select count(s) from Student as s where class_ in ('I', 'II', 'III', 'IV', 'V', 'VI', 'VII') and sex = 'F'")
    public Optional<Long> countAllGirlsFrom7to14FofSchool();

    //Да се добави ограничение по училище, след като започне да се записва училището при създаването на ученика
    //Да се използва за ІІ. Обслужван контингент!
    @Query("select count(s) from Student as s where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'M'")
    public Optional<Long> countAllBoysFrom14to18FofSchool();

    //Да се добави ограничение по училище, след като започне да се записва училището при създаването на ученика
    //Да се използва за ІІ. Обслужван контингент!
    @Query("select count(s) from Student as s where class_ in ('VIII', 'IX', 'X', 'XI', 'XII') and sex = 'F'")
    public Optional<Long> countAllGirlsFrom14to18FofSchool();

}