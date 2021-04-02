package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.HealthConditionDao;
import com.health.SchoolHealth.model.DAOs.ImmunizationDao;
import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.Immunization;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmunizationService {

    private final ImmunizationDao immunizationDao;



    public ImmunizationService(ImmunizationDao immunizationDao) {
        this.immunizationDao = immunizationDao;
    }

    public Immunization findImmunizationById(Long immunizationId) {

        return immunizationId == null ? null: immunizationDao.findById(immunizationId).orElse(null);
    }

    public List<Immunization> getAllImmunizationOfStudent(Long healthConditionId) {
        return RepositoryUtil.iterableToList(immunizationDao.findAllImmunizationByHealthConditionId(healthConditionId));
    }

    public Immunization createOrUpdateImmunization(Immunization immunization) {
        return immunizationDao.save(immunization);
    }

    public void deleteImmunizationById(Long immunizationId) {

        immunizationDao.deleteById(immunizationId);
    }

}
