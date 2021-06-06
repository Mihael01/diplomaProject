package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.ParentDao;
import com.health.SchoolHealth.model.DAOs.ParentTypeDao;
import com.health.SchoolHealth.model.entities.Parent;
import com.health.SchoolHealth.model.entities.ParentType;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {

    private final ParentTypeDao parentTypeDao;

    private final ParentDao parentDao;

    public ParentService(ParentTypeDao parentTypeDao, ParentDao parentDao) {

        this.parentTypeDao = parentTypeDao;
        this.parentDao = parentDao;
    }

    public Parent createOrUpdateParent(Parent parent) {
//        System.out.println("parent ID " + parent.getId());
        return parentDao.save(parent);
    }

    public Parent findParentById(Long parentId) {
        return parentId == null ? null: parentDao.findById(parentId).orElse(null);
    }

    public List<ParentType> getAllParentTypes() {
        return RepositoryUtil.iterableToList(parentTypeDao.findAll());
    }

    public List<Parent> getAllParentsByStudentId(Long studentId) {
        return RepositoryUtil.iterableToList(parentDao.findAllParentsByStudentId(studentId));
    }


}
