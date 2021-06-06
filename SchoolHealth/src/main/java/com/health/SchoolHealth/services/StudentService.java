package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.DAOs.StudentDao;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    private final StudentDao studentDao;

    private final SchoolDao schoolDao;


    public StudentService(StudentDao studentDao, SchoolDao schoolDao) {
        this.studentDao = studentDao;
        this.schoolDao = schoolDao;
    }

    public Student createOrUpdateStudent(Student student) {
        return studentDao.save(student);
    }

    public Student findStudentById(Long studentId) {
        return studentId == null ? null: studentDao.findById(studentId).orElse(null);
    }

    public Student findStudentWithLzpkById(Long studentId) {
        return studentId == null ? null: studentDao.findStudentWithLzpkById(studentId).orElse(null);
    }



    public List<Student> findClassmatesByClassAndClassLetter(String class_, String classLetter, Integer schoolId) {

        if (class_ == null || classLetter == null) {
            return new ArrayList<>();
        }

        return RepositoryUtil.iterableToList(studentDao.findAllByClassAndClassLetter(class_, classLetter, schoolId));
    }

    public Integer countAllClassesFofSchool(Integer schoolId) {

        List<String> allClassesAndLetters =  RepositoryUtil.iterableToList(studentDao.countAllClassesFofSchool(schoolId));

        Set<String> distinctClassesAndLetters = new HashSet<>(allClassesAndLetters);

        return distinctClassesAndLetters.size();
    }

    public Integer countAllStudentsFofSchool(Integer schoolId) {

        Integer studentCount =  studentDao.countAllStudentsFofSchool(schoolId).orElse(null);

        return  studentCount;
    }

    public Integer countAllBoysFrom7to14FofSchool(Integer schoolId) {

        Integer studentCount =  studentDao.countAllBoysFrom7to14FofSchool(schoolId).orElse(null);

        return  studentCount;
    }

    public Integer countAllGirlsFrom7to14FofSchool(Integer schoolId) {

        Integer studentCount =  studentDao.countAllGirlsFrom7to14FofSchool(schoolId).orElse(null);

        return  studentCount;
    }

    public Integer countAllBoysFrom14to18FofSchool(Integer schoolId) {

        Integer studentCount =  studentDao.countAllBoysFrom14to18FofSchool(schoolId).orElse(null);

        return  studentCount;
    }

    public Integer countAllGirlsFrom14to18FofSchool(Integer schoolId) {

        Integer studentCount =  studentDao.countAllGirlsFrom14to18FofSchool(schoolId).orElse(null);

        return  studentCount;
    }

}
