package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.CreateAdminForm;
import com.health.SchoolHealth.model.entities.Admin;
import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.AdminService;
import com.health.SchoolHealth.services.UserService;
import com.health.SchoolHealth.util.ControllerUtil;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class CreateAdminController {

    // Services
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;


    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    CreateAdminForm createAdminForm = new CreateAdminForm();


    @RequestMapping(value ={"createadmin"}, method=RequestMethod.GET)
    public ModelAndView getAdminsData(HttpSession httpSession) {

        modelAndView = new ModelAndView("createadmin");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));
        if (UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {

        modelAndView = new ModelAndView("createadmin");

        Long adminId = (Long) httpSession.getAttribute("adminId");
        if (adminId != null) {
            createAdminForm.setAdmin(adminService.getAdmin(adminId));
        } else {
            createAdminForm.setAdmin(new Admin());
        }

        httpSession.setAttribute("adminId", null);

        String userCode = (String) httpSession.getAttribute("userCode");
        createAdminForm.setAllAdmins(adminService.findAllAdmins(userCode));

        List<UserType> adminUserTypes = new ArrayList<>();

        adminUserTypes.add(UserType.SYS_ADMIN);
        adminUserTypes.add(UserType.SCHOOL_ADMIN);
        adminUserTypes.add(UserType.GP_ADMIN);

        createAdminForm.setUserTypes(adminUserTypes);

        Map<String, String> adminMap = Stream.of(new Object[][] {
                { UserType.SYS_ADMIN.getCode(), UserType.SYS_ADMIN.getDescription() },
                { UserType.GP_ADMIN.getCode(), UserType.GP_ADMIN.getDescription() },
                { UserType.SCHOOL_ADMIN.getCode(), UserType.SCHOOL_ADMIN.getDescription() },
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

        createAdminForm.setAdminMap(adminMap);
        for (Map.Entry<String,String> entry : createAdminForm.getAdminMap().entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        modelAndView.addObject("createAdminForm", createAdminForm);
        return modelAndView;

    }

    @PostMapping
    @RequestMapping(value = {"createAdminPostData"})
    public ModelAndView createAdminPostData(@ModelAttribute("createAdminForm") CreateAdminForm createAdminForm, HttpSession httpSession) {


        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));
        if (UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {

        User savedUser = null;
        if (createAdminForm.getAdmin() != null && createAdminForm.getAdmin().getUser() != null && createAdminForm.getAdmin().getUser().getEmail() != null) {
            if (createAdminForm.getAdmin().getUser().getId() == null) {
                // Задаваме стойности в полетата на потребителя, за случаите, когато на администратора все още не е създаден акаунт.
                ControllerUtil.setUserData(createAdminForm.getAdmin().getUser(), createAdminForm.getUserTypeCode(), userService, mailSender);
                System.out.println("e-mail: " + createAdminForm.getAdmin().getUser().getEmail());
                savedUser = userService.createOrUpdateUser(createAdminForm.getAdmin().getUser());

                //Ако Потребителя не е съществувал като запис в базата,
                // слагаме вече записания потребител като поле на администратора (със съществуващо вече id)
                createAdminForm.getAdmin().setUser(savedUser);
            } else {
                User foundUser = userService.findUser(createAdminForm.getAdmin().getUser().getId());
                // Актуализираме имейла на GP
                foundUser.setEmail(createAdminForm.getAdmin().getUser().getEmail());
                savedUser = userService.createOrUpdateUser(foundUser);
            }
        }

         Admin admin = adminService.createOrUpdateAdmin(createAdminForm.getAdmin());

    } else {
        modelAndView.addObject("isReturnedErrorOnValidation", "true");
    }
        modelAndView = new ModelAndView("redirect:/createadmin");
        return modelAndView;
    }



    @PostMapping
    @RequestMapping(value = {"editAdminData"})
    public ModelAndView editGPData(@RequestParam(value = "adminId", required = false) Long adminId, HttpSession httpSession) {

        System.out.println("editImmunizationData immunizationId = " + adminId);
//        immunizationForm.getImmunization().setHealthCondition(healthConditionService.getHealthConditionById((Long) httpSession.getAttribute("healthConditionId")));
//        immunizationService.createOrUpdateImmunization(immunizationForm.getImmunization());
        httpSession.setAttribute("adminId", adminId);
        modelAndView = new ModelAndView("redirect:/createadmin");

        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"deleteAdminData"})
    public ModelAndView deleteAdminData(@RequestParam(value = "adminId", required = false) Long adminId, HttpSession httpSession) {


        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));
        if (UserType.SYS_ADMIN.getCode().equals(userTypeCode)) {


            System.out.println("DELETE  adminId = " + adminId);
            Admin foundAdmin = adminService.getAdmin(adminId);

            adminService.deleteAdminById(adminId);

            if (foundAdmin.getUser() != null && foundAdmin.getUser().getId() != null) {
                userService.deleteUserById(foundAdmin.getUser().getId());
            }
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }
        modelAndView = new ModelAndView("redirect:/createadmin");

        return modelAndView;
    }


    @RequestMapping(value = {"createadmin"}, method=RequestMethod.POST)
    public ModelAndView searchAdminData(@RequestParam(value = "adminTelephoneNumber", required = false) String adminTelephoneNumber, HttpSession httpSession) {
        String trimedAdminTelephoneNumber = adminTelephoneNumber.replaceAll(" ", "");
        System.out.println("search  gpTelephoneNumber = " + trimedAdminTelephoneNumber);
//        GP foundGP = gpService.getGP(gpId);
//
//        gpService.deleteGPById(gpId);
//
//        if (foundGP.getUser() != null && foundGP.getUser().getId() != null) {
//            userService.deleteUserById(foundGP.getUser().getId());
//        }

        createAdminForm.setAllAdmins(adminService.findAdminByTelephoneNumber(trimedAdminTelephoneNumber));
System.out.println("SIZE "+ createAdminForm.getAllAdmins().size());
//        modelAndView = new ModelAndView("redirect:/createadmin");

        return modelAndView;
    }
}


