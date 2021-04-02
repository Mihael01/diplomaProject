package com.health.SchoolHealth.model.DAOs;

import com.health.SchoolHealth.model.entities.StudentDiseasesAndAbnormalities;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Primary
@Transactional
public interface StudentDiseasesAndAbnormalitiesDao extends CrudRepository<StudentDiseasesAndAbnormalities, Long> {


    @Query("select sda from StudentDiseasesAndAbnormalities as sda where sda.student.id=:studentId")
    public Iterable<StudentDiseasesAndAbnormalities> findAllStudentDiseasesAndAbnormalitiesByStudentId(Long studentId);

    @Query("select count(sda) from StudentDiseasesAndAbnormalities as sda where sda.student.id = :studentId and " +
            "sda.diseasesAndAbnormType.diseasesAndAbnormTypeCode = :diseasesAndAbnormTypeCode")
    public Optional<Long> findAllStudentDiseasesAndAbnormalitiesByStudentIdAndCode(Long studentId, String diseasesAndAbnormTypeCode);

}