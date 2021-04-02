package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.DispensaryObservationIllnessTypeDao;
import com.health.SchoolHealth.model.entities.DispensaryObservationIllnessType;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispensaryObservationIllnessTypeService {

    private final DispensaryObservationIllnessTypeDao dispensaryObservationIllnessTypeDao;


    public DispensaryObservationIllnessTypeService(DispensaryObservationIllnessTypeDao dispensaryObservationIllnessTypeDao) {

        this.dispensaryObservationIllnessTypeDao = dispensaryObservationIllnessTypeDao;
    }

    public List<DispensaryObservationIllnessType> getAllDispensaryObservationIllnessTypes() {
        return RepositoryUtil.iterableToList(dispensaryObservationIllnessTypeDao.findAll());
    }


    public DispensaryObservationIllnessType getDispensaryObservationIllnessTypeByCode(String dispensaryObservIllnessTypeCode) {
        return dispensaryObservationIllnessTypeDao.findByCode(dispensaryObservIllnessTypeCode).orElse(null);
    }
}
