package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.UserDao;
import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createOrUpdateUser(User user) {
        return userDao.save(user);
    }

    public List<User> findAllInactiveParentsBySchool(Integer schoolId) {
       return RepositoryUtil.iterableToList(userDao.findAllInactiveParentsBySchool(schoolId));
    }

    public User findUser(Long userId) {
        return userDao.findById(userId).orElse(null);
    }

    public User findUserByCode(String userCode) {
        return userDao.findUserByCode(userCode).orElse(null);
    }

    public User findUserByPassword(String password) {
        return userDao.findUserByPassword(password).orElse(null);
    }

    public void deleteUserById(Long userId) {
        if (userId != null) {
            userDao.deleteById(userId);
        }
    }

}
