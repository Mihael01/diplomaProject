package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.AnthropologicalIndicators;
import com.health.SchoolHealth.model.entities.StudentDiseasesAndAbnormalities;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface AnthropologicalIndicatorsDao extends CrudRepository<AnthropologicalIndicators, Long> {

    @Query("select ai from AnthropologicalIndicators as ai where ai.student.id=:studentId")
    public Optional<AnthropologicalIndicators> findAnthropologicalIndicatorsByStudentId(Long studentId);

    //(Х +/- 1S) тегло от нормална група
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex and ai.bodyWeight >= :x - :s and ai.bodyWeight <= :x + :s")
    public Optional<Integer> countFirstGroupWeight(Double x, Double s, String class_, Integer schoolId, String sex);

    //(Х +/- 1S) височина от нормална група
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex and ai.bodyHeight >= :x - :s and ai.bodyHeight <= :x + :s")
    public Optional<Integer> countFirstGroupHeight(Double x, Double s, String class_, Integer schoolId, String sex);

    //    брой ученици между (Х +/- 1S) и (Х +/- 2S) тегло от разширена група
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex " +
            "and ((ai.bodyWeight >= :x - 2.0 * :s and ai.bodyWeight < :x - :s) or (ai.bodyWeight > :x + :s and ai.bodyWeight <= :x + 2.0 * :s))")
    public Optional<Integer> countSecondGroupWeight(Double x, Double s, String class_, Integer schoolId, String sex);

    //    брой ученици между (Х +/- 1S) и (Х +/- 2S) височина от разширена група
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex " +
            "and ((ai.bodyHeight >= :x - 2.0 * :s and ai.bodyHeight < :x - :s) or (ai.bodyHeight > :x + :s and ai.bodyHeight <= :x + 2.0 * :s))")
    public Optional<Integer> countSecondGroupHeight(Double x, Double s, String class_, Integer schoolId, String sex);

    //    под (Х - 2S) тегло извън норма
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex and ai.bodyWeight < :x - 2.0 * :s")
    public Optional<Integer> countThirdGroupUnderNormWeight(Double x, Double s, String class_, Integer schoolId, String sex);

    //    под (Х - 2S) тегло извън норма
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex and ai.bodyHeight < :x - 2.0 * :s")
    public Optional<Integer> countThirdGroupUnderNormHeight(Double x, Double s, String class_, Integer schoolId, String sex);

    //    над (Х - 2S) тегло извън норма
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex and ai.bodyWeight > :x - 2.0 * :s")
    public Optional<Integer> countThirdGroupOverNormWeight(Double x, Double s, String class_, Integer schoolId, String sex);

    //    над (Х - 2S) тегло извън норма
    @Query("select count(s) from Student as s join AnthropologicalIndicators as ai on s.id = ai.student.id " +
            "where s.school.id = :schoolId and s.class_= :class_ and s.sexType.sexTypeCode = :sex and ai.bodyHeight > :x - 2.0 * :s")
    public Optional<Integer> countThirdGroupOverNormHeight(Double x, Double s, String class_, Integer schoolId, String sex);
}