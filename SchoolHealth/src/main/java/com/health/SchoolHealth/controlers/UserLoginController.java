package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.SchoolMedicsService;
import com.health.SchoolHealth.services.SchoolService;
import com.health.SchoolHealth.services.UserService;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class UserLoginController {

    // Services
    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    SchoolMedicsService schoolMedicsService;

    // ModelAndViews
    ModelAndView modelAndView;

    // Forms
    User user = new User();

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ModelAndView getUserRegistrationData(ModelAndView modelAndView) {

        if (!modelAndView.hasView()) {
            modelAndView = new ModelAndView("/user/login");
        }

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/user/login", method=RequestMethod.POST)
    public ModelAndView userRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes, ModelAndView modelAndView, HttpSession httpSession) {

        User foundUser = userService.findUserByCode(user.getUserCode());
        //Ако потребителят е въвел некоректни стойности за код или парола се извежда съобщение за грешка
        boolean hasUserRights = true;

        if (foundUser != null) {
            // В зависимост от вида потребител имаме нива на достъп
            Integer userTypeCode = foundUser.getUserType();
            httpSession.setAttribute("userTypeCode", userTypeCode);


            Integer schoolId = null;

            if (UserType.SCHOOL_MEDIC.getCode().equals(String.valueOf(userTypeCode))) {
                schoolId = schoolService.findSchoolIdBySchoolMedicUserId(foundUser.getId());
                System.out.println("schoolId in login" + schoolId);
                SchoolMedics schoolMedic = schoolMedicsService.getSchoolMedicByUserId(foundUser.getId());
                System.out.println("school medic id " + schoolMedic.getId());
                httpSession.setAttribute("schoolMedic", schoolMedic);
            }

//            if (UserType.SPORT_TEACHER.getCode().equals(String.valueOf(userTypeCode))) {
//                schoolService.findSchoolIdBySportTeacherUserId(foundUser.getId());
//            }

            httpSession.setAttribute("schoolId", schoolId);

            bCryptPasswordEncoder = new BCryptPasswordEncoder();

            if (bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                modelAndView = new ModelAndView("redirect:/home");
            } else {
                hasUserRights = false;
            }

        } else {
            hasUserRights = false;
        }

        if (!hasUserRights) {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        return modelAndView;
    }
}
