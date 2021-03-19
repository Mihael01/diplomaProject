package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.DAOs.SchoolTypeDao;
import com.health.SchoolHealth.model.DAOs.StudentDao;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.model.entities.SchoolType;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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



    public List<Student> findClassmatesByClassAndClassLetter(String class_, String classLetter) {

        if (class_ == null || classLetter == null) {
            return new ArrayList<>();
        }

        return RepositoryUtil.iterableToList(studentDao.findAllByClassAndClassLetter(class_, classLetter));
    }
}
