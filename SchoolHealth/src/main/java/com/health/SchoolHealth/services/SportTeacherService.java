package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.SportTeacherDao;
import com.health.SchoolHealth.model.entities.SportTeacher;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportTeacherService {

    private final SportTeacherDao sportTeacherDao;



    public SportTeacherService(SportTeacherDao sportTeacherDao) {
        this.sportTeacherDao = sportTeacherDao;
    }

    public SportTeacher getSportTeacher(Long sportTeacherId) {
        return sportTeacherId == null ? null: sportTeacherDao.findById(sportTeacherId).orElse(null);
    }

    public SportTeacher createOrUpdateSportTeacher(SportTeacher sportTeacher) {
        return sportTeacherDao.save(sportTeacher);
    }

    public List<SportTeacher> findAllSportTeachers() {
        return  RepositoryUtil.iterableToList(sportTeacherDao.findAllSportTeachersWithUser());
    }

    public List<SportTeacher> findSportTeacherByTelephoneNumber(String telephoneNumber) {
        return  RepositoryUtil.iterableToList(sportTeacherDao.findSportTeacherWithUserByTelephoneNumber(telephoneNumber));
    }

    public void deleteSportTeacherById(Long sportTeacherId) {
        if (sportTeacherId != null) {
            sportTeacherDao.deleteById(sportTeacherId);
        }
    }

}
