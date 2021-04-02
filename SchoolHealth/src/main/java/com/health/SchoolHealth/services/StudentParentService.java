package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.DAOs.StudentDao;
import com.health.SchoolHealth.model.DAOs.StudentParentDao;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.model.entities.StudentParent;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentParentService {

    private final StudentParentDao studentParentDao;


    public StudentParentService(StudentParentDao studentParentDao) {
        this.studentParentDao = studentParentDao;
    }

    public StudentParent createOrUpdateStudentParent(StudentParent studentParent) {
        return studentParentDao.save(studentParent);
    }

}
