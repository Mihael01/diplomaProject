package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.AnthropologicalIndicatorsDao;
import com.health.SchoolHealth.model.entities.AnthropologicalIndicators;
import org.springframework.stereotype.Service;

@Service
public class AnthropologicalService {
    private final AnthropologicalIndicatorsDao anthropologicalIndicatorsDao;

    public AnthropologicalService(AnthropologicalIndicatorsDao anthropologicalIndicatorsDao) {
        this.anthropologicalIndicatorsDao = anthropologicalIndicatorsDao;
    }

    public AnthropologicalIndicators getAnthropologicalIndicatorsByStudentId(Long studentId) {
        return studentId == null ? null: anthropologicalIndicatorsDao.findAnthropologicalIndicatorsByStudentId(studentId).orElse(null);
    }

    public AnthropologicalIndicators createOrUpdateAnthropologicalIndicators(AnthropologicalIndicators anthropologicalIndicators) {
        return anthropologicalIndicatorsDao.save(anthropologicalIndicators);
    }
}
