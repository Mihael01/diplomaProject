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


    @Query("select sda from StudentDiseasesAndAbnormalities as sda join Student s on sda.student.id = s.id " +
            "where sda.diseasesAndAbnormType.diseasesAndAbnormTypeCode = :diseasesAndAbnormTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDiseasesAndAbnormalities> findNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCode(String diseasesAndAbnormTypeCode, Integer schoolId);

    @Query("select sda from StudentDiseasesAndAbnormalities as sda join Student s on sda.student.id = s.id " +
            "where  s.class_ = 'I' and sda.diseasesAndAbnormType.diseasesAndAbnormTypeCode = :diseasesAndAbnormTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDiseasesAndAbnormalities> findNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeIClass(String diseasesAndAbnormTypeCode, Integer schoolId);

    @Query("select sda from StudentDiseasesAndAbnormalities as sda join Student s on sda.student.id = s.id " +
            "where s.class_ = 'VII' and sda.diseasesAndAbnormType.diseasesAndAbnormTypeCode = :diseasesAndAbnormTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDiseasesAndAbnormalities> findNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeVIIClass(String diseasesAndAbnormTypeCode, Integer schoolId);

    @Query("select sda from StudentDiseasesAndAbnormalities as sda join Student s on sda.student.id = s.id " +
            "where s.class_ = 'X' and sda.diseasesAndAbnormType.diseasesAndAbnormTypeCode = :diseasesAndAbnormTypeCode and  s.school.id = :schoolId")
    public Iterable<StudentDiseasesAndAbnormalities> findNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeXClass(String diseasesAndAbnormTypeCode, Integer schoolId);

}