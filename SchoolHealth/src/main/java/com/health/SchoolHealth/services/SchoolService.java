package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.DAOs.SchoolTypeDao;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.model.entities.SchoolType;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolDao schoolDao;

    private final SchoolTypeDao schoolTypeDao;


    public SchoolService(SchoolDao schoolDao, SchoolTypeDao schoolTypeDao) {
        this.schoolDao = schoolDao;
        this.schoolTypeDao = schoolTypeDao;
    }

    public void createOrUpdateSchool(School school) {
        System.out.println("school ID " + school.getId());
        schoolDao.save(school);
    }

    public School findSchoolById(Integer schoolId) {
        return schoolId == null ? null: schoolDao.findById(schoolId).orElse(null);
    }

    public List<String> getAllSchoolTypeNames() {
        Iterator<SchoolType> allSchoolTypes = schoolTypeDao.findAll().iterator();
        List<SchoolType> selectedSchoolTypesData = new ArrayList<>();
        allSchoolTypes.forEachRemaining(selectedSchoolTypesData::add);

        List<String> schoolTypeNames =
                selectedSchoolTypesData.stream().map(td -> {System.out.println(td.getSchoolTypeName());
                    return td.getSchoolTypeName();} ).collect(Collectors.toList());
        System.out.println("COUNT = " + schoolTypeNames.size());

        return schoolTypeNames;
    }

    public List<SchoolType> getAllSchoolTypes() {

        return RepositoryUtil.iterableToList(schoolTypeDao.findAll());
    }

    public List<School> getAllSchoolsBySchoolMedic(Long medicId) {
        return RepositoryUtil.iterableToList(schoolDao.findAllSchoolsBySchoolMedic(medicId));
    }

    public Integer findSchoolIdBySchoolMedicUserId(Long userId) {
        School school = schoolDao.getFindSchoolBySchoolMedicUserId(userId).orElse(null);
        return school != null ? school.getId() : null;
    }

//    public Long findSchoolIdBySportTeacherUserId(Long userId) {
//        School school = schoolDao.getSchoolBySportTeacherUserId(userId).orElse(null);
//        return school != null ? school.getId() : Long.valueOf(null);
//    }

}
