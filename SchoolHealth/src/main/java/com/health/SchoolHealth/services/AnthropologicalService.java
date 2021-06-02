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

    public Integer countFirstGroupWeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countFirstGroupWeight(x, s, class_, schoolId, sex).orElse(0);
    }

    public Integer countFirstGroupHeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countFirstGroupHeight(x, s, class_, schoolId, sex).orElse(0);
    }

    public Integer countSecondGroupWeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countSecondGroupWeight(x, s, class_, schoolId, sex).orElse(0);
    }

    public Integer countSecondGroupHeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countSecondGroupHeight(x, s, class_, schoolId, sex).orElse(0);
    }

    public Integer countThirdGroupUnderNormWeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countThirdGroupUnderNormWeight(x, s, class_, schoolId, sex).orElse(0);
    }

    public Integer countThirdGroupUnderNormHeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countThirdGroupUnderNormHeight(x, s, class_, schoolId, sex).orElse(0);
    }

    public Integer countThirdGroupOverNormWeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countThirdGroupOverNormWeight(x, s, class_, schoolId, sex).orElse(0);
    }

    public Integer countThirdGroupOverNormHeight(Double x, Double s, String class_, Integer schoolId, String sex) {
        return anthropologicalIndicatorsDao.countThirdGroupOverNormHeight(x, s, class_, schoolId, sex).orElse(0);
    }

}
