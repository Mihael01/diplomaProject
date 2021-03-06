package com.health.SchoolHealth.util;

import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ControllerUtil {

    public static final List<String> authorizedForLZPKData = new ArrayList<>();

    static {
        authorizedForLZPKData.add(UserType.SCHOOL_MEDIC.getCode());
        authorizedForLZPKData.add(UserType.GP.getCode());
        authorizedForLZPKData.add(UserType.PARENT.getCode());
        authorizedForLZPKData.add(UserType.SYS_ADMIN.getCode());
        authorizedForLZPKData.add(UserType.SCHOOL_ADMIN.getCode());
        authorizedForLZPKData.add(UserType.GP_ADMIN.getCode());
    }

    public static final List<String> authorizedForGPData = new ArrayList<>();

    static {
        authorizedForGPData.add(UserType.SCHOOL_MEDIC.getCode());
        authorizedForGPData.add(UserType.GP.getCode());
        authorizedForLZPKData.add(UserType.SYS_ADMIN.getCode());
        authorizedForLZPKData.add(UserType.SCHOOL_ADMIN.getCode());
        authorizedForLZPKData.add(UserType.GP_ADMIN.getCode());
    }

    public static final List<String> authorizedForStudentListData = new ArrayList<>();

    static {
        authorizedForStudentListData.add(UserType.SCHOOL_MEDIC.getCode());
        authorizedForStudentListData.add(UserType.SPORT_TEACHER.getCode());
        authorizedForStudentListData.add(UserType.SYS_ADMIN.getCode());
        authorizedForStudentListData.add(UserType.SCHOOL_ADMIN.getCode());
        authorizedForStudentListData.add(UserType.GP_ADMIN.getCode());
    }

    public static final List<String> authorizedForAnalysisData = new ArrayList<>();

    static {
        authorizedForAnalysisData.add(UserType.SCHOOL_MEDIC.getCode());
        authorizedForAnalysisData.add(UserType.SYS_ADMIN.getCode());
        authorizedForAnalysisData.add(UserType.SCHOOL_ADMIN.getCode());
        authorizedForAnalysisData.add(UserType.GP_ADMIN.getCode());
    }

    static public void setUserData(User user, String userTypeCode, UserService userService, JavaMailSender mailSender) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // Генерираме код и парола за потребителя
        String code = null;
        String password = null;
        User foundUser = null;
        do {
            code = RandomStringUtils.randomAlphanumeric(10);
            foundUser = userService.findUserByCode(code);
        } while (foundUser != null);

        do {
            password = RandomStringUtils.randomAlphanumeric(10);
            foundUser = userService.findUserByPassword(password);
        } while (foundUser != null);

        // Да се изпрати имейл!!!!
        // ...
        String encodePassword = bCryptPasswordEncoder.encode(password);

        user.setPassword(encodePassword);
        user.setUserCode(code);
        user.setEnable("Y");
        if(userTypeCode != null) {
            user.setUserType(Integer.valueOf(userTypeCode));
        }

        System.out.println(" user.getEmail() "+  user.getEmail());
        sendEmail(code, password, user.getEmail(), mailSender);
    }

    static public void sendEmail(String code, String password, String emailTo, JavaMailSender mailSender) {
        // use mailSender here...
        System.out.println("// use mailSender here...!!!!!!!!!!!!!");
        String from = "school.health.org@gmail.com";
        String to = emailTo;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Акаунт за ИС \"Училищно здраве\"");
        message.setText("Здравейте,\n\nЗа Вас е създаден акаунт за ИС \"Училищно здраве\". \n\nКод за влизане в системата: "
                + code + "\nПарола: " + password + "\n\nПоздрави,\nАдминистрация на ИС \"Училищно здраве\"");

        mailSender.send(message);
    }
}
