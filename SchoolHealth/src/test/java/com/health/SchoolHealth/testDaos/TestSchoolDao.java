package com.health.SchoolHealth.testDaos;

import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TestSchoolDao {

    private final String  SCHOOL_NUMBER = "№ 1";
    private final String  SCHOOL_NAME = "Тестово училище";
    private final String SCHOOL_TELEPHONE_NUMBER = "0123 000 000";
    private final String OTHER_TYPE = "Професионално училище";

    private School school;

    private Integer schoolId;

    List<School> schools = new ArrayList<>();

    @Autowired
    private SchoolDao schoolDao;

    @BeforeAll
    void createSchoolObject() {
        this.school = new School();
        school.setSchoolName(SCHOOL_NAME);
        school.setSchoolNumber(SCHOOL_NUMBER);
        school.setSchoolTypeOther(OTHER_TYPE);
        school.setSchoolTelephoneNumber(SCHOOL_NUMBER);
    }

    @Test
    public void testBaseCrudOperationsForSchool() {
        try {
//          Записваме обект school в базата
            long countOfSchools = schoolDao.count();
            School savedSchool = schoolDao.save(school);
            Assert.assertEquals(++countOfSchools, schoolDao.count());

//            Взимаме Id на записания обект
            schoolId = savedSchool.getId();

//          Намираме обект по Id
            School foundSchool = schoolDao.findById(schoolId).get();
            Assert.assertTrue(schoolDao.findById(schoolId).isPresent());

//          Проверяваме дали даден обект съществува по Id
            Assert.assertTrue(schoolDao.existsById(schoolId));

            Assert.assertEquals(school.getSchoolName(), SCHOOL_NAME);
//            Актуализираме обект school в базата
            String newSchoolName = SCHOOL_NAME + "_NEW";
            savedSchool.setSchoolName(newSchoolName);
            School updatedSchool = schoolDao.save(savedSchool);
            Assert.assertEquals(updatedSchool.getSchoolName(), newSchoolName);
            Assert.assertEquals(updatedSchool.getId(), savedSchool.getId());

//          Изтриваме обект по Id
            countOfSchools = schoolDao.count();
            schoolDao.deleteById(schoolId);
            Assert.assertEquals(--countOfSchools, schoolDao.count());

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Test
    public void testDeleteSchoolObject() {
        try {
            schoolDao.save(school);
            long countOfSchools = schoolDao.count();
            schoolDao.delete(school);
            Assert.assertEquals(--countOfSchools, schoolDao.count());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Test
    public void testSaveFindAndDeleteAllSchools() {
        try {
            School school1 = new School();
            school1.setSchoolName(SCHOOL_NAME + 1);
            school1.setSchoolNumber(SCHOOL_NUMBER + 1);
            school1.setSchoolTypeOther(OTHER_TYPE + 1);
            school1.setSchoolTelephoneNumber(SCHOOL_NUMBER + 1);
            schools.add(school1);

            School school2 = new School();
            school2.setSchoolName(SCHOOL_NAME + 2);
            school2.setSchoolNumber(SCHOOL_NUMBER + 2);
            school2.setSchoolTypeOther(OTHER_TYPE + 2);
            school2.setSchoolTelephoneNumber(SCHOOL_NUMBER + 2);
            schools.add(school2);

            long countOfSchools = schoolDao.count();

            // Запазваме списък от ученици
            Iterable<School> iterable = RepositoryUtil.listToIterable(schools);
            Iterable<School> savedSchool = schoolDao.saveAll(iterable);
            Assert.assertEquals(countOfSchools + 2, schoolDao.count());

            // Намираме списък от ученици
            List<School> savedSchools = RepositoryUtil.iterableToList(schoolDao.findAll());
            Assert.assertFalse(savedSchools.isEmpty());
            Assert.assertEquals(countOfSchools + 2, savedSchools.size());
            // Изтриваме списък от ученици
            schoolDao.deleteAll(schools);
            Assert.assertEquals(countOfSchools, schoolDao.count());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}




