package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.GPForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolMedicForm;
import com.health.SchoolHealth.model.entities.GP;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.model.entities.SchoolMedics;
import com.health.SchoolHealth.model.entities.Student;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.FormUtil;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForGPData;


@RestController
public class GPController {

    // Services
    @Autowired
    private GPService gpService;

    @Autowired
    private StudentService studentService;

    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    GPForm gpForm= new GPForm();

    @GetMapping
    @RequestMapping(value ={"gp"})
    public ModelAndView getSchooldata(@PathVariable(value = "gpId", required = false) Optional<Long> gpIdParam, HttpSession httpSession) {

        modelAndView = new ModelAndView("gp");

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));



        if (authorizedForGPData.contains(userTypeCode)) {

            // Запис в базата за личния лекар може да бъде създаден:
            // I. При регистрация на личния лекар
            // II. При запис на ученик от училищното медицинско лице, ако личния лекар не може да бъде намерен в базата данни по телефонен номер
            // Телефонния номер на личния лекар е задължително поле; При регистрацията на личния лекар се прави проверка
            // дали съществува лекар с такъв телефонен номер, ако съществува се актуализира записа

            System.out.println("STUDENT ID " + httpSession.getAttribute("studentId"));
            Long studentId = (Long) httpSession.getAttribute("studentId");

            // Училищно медицинско лице
            Long gpId = gpIdParam.orElse(1L);

            // Ако в системата се е логнал личният лекар, тогава го намираме по неговото id,
            // но aко в системата се е логналo училищното медицинско лице, тогава GP се намира studentId (За случая, ако вече на ученика е записано GP)
            GP gp = gpService.getGP(gpId);
            GP gpOfStudent = gpService.getGpOfStudent(studentId);

            if (gp != null) {
                gpForm.setGp(gp);
            } else if (gpOfStudent != null) {
                gpForm.setGp(new GP());
            } else {
                gpForm.setGp(new GP());
            }

            modelAndView.addObject("gpForm", gpForm);

            gpForm.setIsListOfStudentsVisible(isListOfStudentsVisible());

            gpForm.setStudentsOfGP(gpService.getAllStudentsOfGP(gpId));
        } else {
            modelAndView.addObject("isReturnedErrorOnValidation", "true");
        }

        return modelAndView;

    }

    private Boolean isListOfStudentsVisible() {
        // Да се добави функционалност, ако логнатият потребител е GP, връщаме true, иначе false
        return true;
    }

    @PostMapping
    @RequestMapping(value = {"gpPostData"})
    public ModelAndView gpPostData(@ModelAttribute("gpForm") GPForm gpForm, HttpSession httpSession) {


         boolean isNotGPExistInDB = true;

         if (gpForm.getGp().getId() != null) {
             isNotGPExistInDB = false;
         }

         GP gp = gpService.createOrUpdateGP(gpForm.getGp());
         // Акo в системата е влязло училищното медицинско лице и създава за пръв път запис за личния лекар на ученика,
        // то тогава трябва да се запише id на GP в записа на конкретния ученик. Или в случая, когато личния лекар
        // въвежда данните за ученик, също трябва да се запише id на GP в записа на ученикa.
       // Student student = studentService.findStudentById(studentId);
       // сет гп ид и ъпдейт на ученика

        modelAndView = new ModelAndView("redirect:/gp");
        return modelAndView;
    }
}


