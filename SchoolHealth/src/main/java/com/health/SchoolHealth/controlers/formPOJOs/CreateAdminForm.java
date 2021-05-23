package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.Admin;
import com.health.SchoolHealth.util.UserType;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreateAdminForm {

    private Admin admin;

    private List<Admin> allAdmins;

    private String adminTelephoneNumber;

    private List<UserType> userTypes;

    private String userTypeCode;

    private Map<String, String> adminMap;

}
