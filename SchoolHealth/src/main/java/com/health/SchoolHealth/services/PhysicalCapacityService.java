package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.PhysicalCapacityDao;
import com.health.SchoolHealth.model.DAOs.SexTypeDao;
import com.health.SchoolHealth.model.entities.PhysicalCapacity;
import com.health.SchoolHealth.model.entities.SexType;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysicalCapacityService {

    private final PhysicalCapacityDao physicalCapacityDao;


    public PhysicalCapacityService(PhysicalCapacityDao physicalCapacityDao) {
        this.physicalCapacityDao = physicalCapacityDao;
    }

    public PhysicalCapacity getPhysicalCapacityDaoByStudentId(Long studentId) {
        return physicalCapacityDao.findPhysicalCapacityByStudentId(studentId).orElse(null);
    }

    public PhysicalCapacity createOrUpdatePhysicalCapacity(PhysicalCapacity physicalCapacity) {
        return physicalCapacityDao.save(physicalCapacity);
    }

}
