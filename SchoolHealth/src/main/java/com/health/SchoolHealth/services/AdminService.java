package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.AdminDao;
import com.health.SchoolHealth.model.entities.Admin;
import com.health.SchoolHealth.util.RepositoryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminDao adminDao;

    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Admin getAdmin(Long adminId) {
        return adminId == null ? null: adminDao.findById(adminId).orElse(null);
    }


    public Admin createOrUpdateAdmin(Admin admin) {

        return adminDao.save(admin);
    }

    public List<Admin> findAllAdmins(String userCode) {
        return  RepositoryUtil.iterableToList(adminDao.findAllAdminsWithUser(userCode));
    }

    public List<Admin> findAdminByTelephoneNumber(String telephoneNumber) {
        return  RepositoryUtil.iterableToList(adminDao.findAdminsWithUserByTelephoneNumber(telephoneNumber));
    }

    public void deleteAdminById(Long adminId) {
        if (adminId != null) {
            adminDao.deleteById(adminId);
        }
    }

}
