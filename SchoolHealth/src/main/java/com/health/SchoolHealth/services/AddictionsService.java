package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.AddictionsDao;
import com.health.SchoolHealth.model.DAOs.AnthropologicalIndicatorsDao;
import com.health.SchoolHealth.model.entities.Addictions;
import com.health.SchoolHealth.model.entities.AnthropologicalIndicators;
import org.springframework.stereotype.Service;

@Service
public class AddictionsService {
    private final AddictionsDao addictionsDao;

    public AddictionsService(AddictionsDao addictionsDao) {
        this.addictionsDao = addictionsDao;
    }

    public Addictions getAddictionsByStudentId(Long studentId) {
        return studentId == null ? null: addictionsDao.findAddictionsByStudentId(studentId).orElse(null);
    }

    public Addictions createOrUpdateAddictions(Addictions addictions) {
        return addictions == null ? null :addictionsDao.save(addictions);
    }
}
