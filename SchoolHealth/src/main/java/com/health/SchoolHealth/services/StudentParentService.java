package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.StudentParentDao;
import com.health.SchoolHealth.model.entities.StudentParent;
import org.springframework.stereotype.Service;

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
