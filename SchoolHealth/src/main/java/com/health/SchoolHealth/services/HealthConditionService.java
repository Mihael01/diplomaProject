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

    public Integer countStudentsExemptFromPhysicalEducation(Integer schoolId) {
        return healthConditionDao.countStudentsExemptFromPhysicalEducation(schoolId).orElse(null);
    }


    public Integer countStudentsExemptFromPhysicalEducationGirlsFrom7to14FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsExemptFromPhysicalEducationGirlsFrom7to14FofSchool(schoolId).orElse(null);
    }


    public Integer countStudentsExemptFromPhysicalEducationBoysFrom7to14FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsExemptFromPhysicalEducationBoysFrom7to14FofSchool(schoolId).orElse(null);
    }

    public Integer countStudentsExemptFromPhysicalEducationGirlsFrom14to18FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsExemptFromPhysicalEducationGirlsFrom14to18FofSchool(schoolId).orElse(null);
    }

    public Integer countStudentsExemptFromPhysicalEducationBoysFrom14to18FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsExemptFromPhysicalEducationBoysFrom14to18FofSchool(schoolId).orElse(null);
    }



    public Integer countStudentsIncludInTherapeuticPhysicalEducation(Integer schoolId) {
        return healthConditionDao.countStudentsIncludInTherapeuticPhysicalEducation(schoolId).orElse(null);
    }


    public Integer countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom7to14FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom7to14FofSchool(schoolId).orElse(null);
    }


    public Integer countStudentsIncludInTherapeuticPhysicalEducationBoysFrom7to14FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsIncludInTherapeuticPhysicalEducationBoysFrom7to14FofSchool(schoolId).orElse(null);
    }

    public Integer countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom14to18FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom14to18FofSchool(schoolId).orElse(null);
    }

    public Integer countStudentsIncludInTherapeuticPhysicalEducationBoysFrom14to18FofSchool(Integer schoolId) {
        return healthConditionDao.countStudentsIncludInTherapeuticPhysicalEducationBoysFrom14to18FofSchool(schoolId).orElse(null);
    }
}
