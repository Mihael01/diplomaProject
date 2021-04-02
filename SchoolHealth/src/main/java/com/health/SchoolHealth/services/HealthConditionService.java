package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.HealthConditionDao;
import com.health.SchoolHealth.model.DAOs.LzpkDao;
import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.Lzpk;
import org.springframework.stereotype.Service;

@Service
public class HealthConditionService {

    private final HealthConditionDao healthConditionDao;



    public HealthConditionService(HealthConditionDao healthConditionDao) {
        this.healthConditionDao = healthConditionDao;
    }


    public HealthCondition getHealthConditionById(Long healthConditionId) {
        return healthConditionId == null ? null: healthConditionDao.findById(healthConditionId).orElse(null);
    }

    public HealthCondition getHealthConditionByStudentId(Long studentId) {
        return studentId == null ? null: healthConditionDao.findHealthConditionByStudentId(studentId).orElse(null);
    }

    public HealthCondition createOrUpdateHealthCondition(HealthCondition healthCondition) {
        return healthConditionDao.save(healthCondition);
    }


}
