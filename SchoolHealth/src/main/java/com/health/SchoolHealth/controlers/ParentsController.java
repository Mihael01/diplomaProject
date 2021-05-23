package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.ParentForm;
import com.health.SchoolHealth.model.entities.Parent;
import com.health.SchoolHealth.model.entities.ParentType;
import com.health.SchoolHealth.model.entities.StudentParent;
import com.health.SchoolHealth.model.entities.User;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.ControllerUtil;
import com.health.SchoolHealth.util.FormUtil;
import com.health.SchoolHealth.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class ParentsController {

    // Services
    @Autowired
    private ParentService parentService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentParentService studentParentService;

    @Autowired
    private UserService userService;


    @Autowired
    private JavaMailSender mailSender;

    ModelAndView modelAndView;

    // Forms
    ParentForm parentForm = new ParentForm();

    AddressForm addressForm = new AddressForm();

    AddressForm addressForm2 = new AddressForm();

    @GetMapping
    @RequestMapping(value = { "/parents", "/parents/{parentNumber}"})
    public ModelAndView getParentData(HttpSession httpSession) {

        modelAndView = new ModelAndView("parents");
        httpSession.setAttribute("redirect", "redirect:/parents");

        System.out.println("STUDENT ID " +  httpSession.getAttribute("studentId"));
        Long studentId = (Long) httpSession.getAttribute("studentId");
//        studentId = 2L; //REMOVE HARDCODE
//        httpSession.setAttribute("studentId", studentId); // заради хардкода по време на тестване

System.out.println("IN getParentData httpsession address about " + httpSession.getAttribute("addressAbout"));
//        двамата родители в сесията в списък
//        от parents да се подава с ajax номера на родителя като входен параметър

        if (parentForm.getParentTypes() == null || parentForm.getParentTypes().isEmpty()) {
            List<ParentType> parentTypes = parentService.getAllParentTypes();

            parentForm.setParentTypes(parentTypes);
        }


        List<Parent>  foundParents = parentService.getAllParentsByStudentId(studentId);




        if (foundParents.size() > 0) {
//            if (parentNumber != null && parentNumber == 0 && foundParents.size() > parentNumber) {
//            parentForm.setParent1(foundParents.get(0));
            System.out.println("PARENT TYPE 1" + foundParents.get(0).getParentType().getParentTypeName());
            parentForm.setParent1(foundParents.get(0));
//            parentForm.getParent1().setParentType(foundParents.get(0).getParentType());
//            System.out.println(">>>> "+ parentForm.getParent1().getParentType().getParentTypeCode());
        } else {
            parentForm.setParent1(new Parent());
        }

        if (foundParents.size() > 1) {

//            parentForm.setParent2(foundParents.get(1));
            System.out.println("PARENT TYPE 2" + foundParents.get(1).getParentType().getParentTypeName());
            parentForm.setParent2(foundParents.get(1));
//            parentForm.getParent2().setParentType(foundParents.get(1).getParentType());
            System.out.println(">>>> "+ parentForm.getParent1().getParentType().getParentTypeCode());
        } else {
            parentForm.setParent2(new Parent());
        }

//        for (Parent parent : foundParents) {
//            System.out.println("!!!!! "+parentNumber+" !!!!! " + parent.getParentName());
//        }

        // Потребител
        parentForm.getParent1().setUser(setParentUser(parentForm.getParent1(), addressForm));
        parentForm.getParent2().setUser(setParentUser(parentForm.getParent2(), addressForm2));

        modelAndView.addObject("parentForm", parentForm);

        // Адрес
        setParentAddress(parentForm.getParent1(), httpSession, addressForm, 1);
        setParentAddress(parentForm.getParent2(), httpSession, addressForm2, 2);

        System.out.println("addressForm.getAddress().getId() " + addressForm.getAddress().getId());
        System.out.println("addressForm2.getAddress2().getId() " + addressForm2.getAddress2().getId());

        return modelAndView;

    }

    private User setParentUser(Parent parent, AddressForm addressForm) {
        User user = new User();
        if (parent.getUser() != null && parent.getUser().getId() != null) {
            user = userService.findUser(parent.getUser().getId());

        }
        return user;
    }

    //addressId, ownerId, adressAbout like post parameters

    private void setParentAddress(Parent parent, HttpSession httpSession, AddressForm addressForm, int parentNumber) {

        Long addressId1= null;
        Long addressId2 = null;
        if (parent.getParentAddress() != null &&  parent.getParentAddress().getId() != null) {
            if (parentNumber == 1) {
                addressId1 = parent.getParentAddress().getId();
            } else {
                addressId2 = parent.getParentAddress().getId();
            }
        }

System.out.println("ADDRESS 1 ID addressId1 " + addressId1);
        System.out.println("ADDRESS 2 ID addressId2 " + addressId2);


        FormUtil.setAddressForm(FormUtil.ADDRESS_ABOUT_PARENT, addressId1, addressId2,
                addressService, addressForm, httpSession);

        // Пазим parentId в addressForm, за да можем след събмит на формата да актуализираме родителя
        if (parentNumber == 1) {
            addressForm.setParent1Id(parentForm.getParent1().getId());
            addressForm.setIsOwnerNotPresent(parent == null || parent.getId() == null);
            modelAndView.addObject("addressForm" , addressForm);
        } else {
            addressForm.setParent2Id(parentForm.getParent2().getId());
            addressForm.setIsOwner2NotPresent(parent == null || parent.getId() == null);
            modelAndView.addObject("addressForm2" , addressForm);
        }


//        modelAndView.addObject("addressForm" , addressForm);
    }

    @PostMapping
    @RequestMapping(value = {"parentPostData"})
    public ModelAndView parentPostData(@ModelAttribute("parentForm") ParentForm parentForm, HttpSession httpSession) {

        User savedUser = null;

        if (parentForm.getParent1() != null) {

            if (parentForm.getParent1().getUser() != null && parentForm.getParent1().getUser().getEmail() != null) {
                if (parentForm.getParent1().getUser().getId() == null) {
                    // Задаваме стойности в полетата на потребителя, за случаите, когато на родителя все още не е създаден акаунт.
                    ControllerUtil.setUserData(parentForm.getParent1().getUser(), UserType.PARENT.getCode(), userService, mailSender);
                    System.out.println("e-mail: " + parentForm.getParent1().getUser().getEmail());
                    savedUser = userService.createOrUpdateUser(parentForm.getParent1().getUser());

                    //Ако Потребителя не е съществувал като запис в базата,
                    // слагаме вече записания потребител като поле на родителя (със съществуващо вече id)
                    parentForm.getParent1().setUser(savedUser);
                } else {
                    User foundUser = userService.findUser(parentForm.getParent1().getUser().getId());
                    // Актуализираме имейла на родителя
                    foundUser.setEmail(parentForm.getParent1().getUser().getEmail());
                    savedUser = userService.createOrUpdateUser(foundUser);
                }
            }

            // Ако нямаме записан адрес, сетвавме полето на null, защото в противен случай при запис на родителя,
            // hibernate търси свързан обект в таблицата с адреси, а нямаме Id
            if (parentForm.getParent1().getParentAddress() != null
                    && parentForm.getParent1().getParentAddress().getId() == null) {
                parentForm.getParent1().setParentAddress(null);
            }

            boolean isNotParentExistInDB = true;

            if (parentForm.getParent1().getId() != null) {
                isNotParentExistInDB = false;
            }

            Parent parent = parentService.createOrUpdateParent(parentForm.getParent1());

            if (isNotParentExistInDB && parent != null && (Long)httpSession.getAttribute("studentId") != null) {
                StudentParent studentParent = new StudentParent();
                studentParent.setParent(parent);
                studentParent.setStudent(studentService.findStudentById((Long)httpSession.getAttribute("studentId")));
                studentParentService.createOrUpdateStudentParent(studentParent);
            }

            System.out.println("parent1Id " + parent.getId());
            System.out.println("StudentId  " + httpSession.getAttribute("studentId"));
            httpSession.setAttribute("parent1Id", parent.getId());
        } else if (parentForm.getParent2() != null) {

            if (parentForm.getParent2().getUser() != null &&  parentForm.getParent2().getUser().getEmail() != null) {
                if (parentForm.getParent2().getUser().getId() == null) {
                    System.out.println("e-mail 2: " + parentForm.getParent2().getUser().getEmail());
                    // Задаваме стойности в полетата на потребителя, за случаите, когато на родителя все още не е създаден акаунт.
                    ControllerUtil.setUserData(parentForm.getParent2().getUser(), UserType.PARENT.getCode(), userService, mailSender);

                    savedUser = userService.createOrUpdateUser(parentForm.getParent2().getUser());

                    //Ако Потребителя не е съществувал като запис в базата,
                    // слагаме вече записания потребител като поле на родителя (със съществуващо вече id)
                    parentForm.getParent2().setUser(savedUser);
                } else {
                    User foundUser = userService.findUser(parentForm.getParent2().getUser().getId());
                    // Актуализираме имейла на родителя
                    foundUser.setEmail(parentForm.getParent2().getUser().getEmail());
                    savedUser = userService.createOrUpdateUser(foundUser);
                }
            }

            // Ако нямаме записан адрес, сетвавме полето на null, защото в противен случай при запис на родителя,
            // hibernate търси свързан обект в таблицата с адреси, а нямаме Id
            if (parentForm.getParent2().getParentAddress() != null
                    && parentForm.getParent2().getParentAddress().getId() == null) {
                parentForm.getParent2().setParentAddress(null);
            }

            boolean isNotParentExistInDB = true;

            if (parentForm.getParent2().getId() != null) {
                isNotParentExistInDB = false;
            }

            Parent parent = parentService.createOrUpdateParent(parentForm.getParent2());

            if (isNotParentExistInDB && parent != null && (Long)httpSession.getAttribute("studentId") != null) {
                StudentParent studentParent = new StudentParent();
                studentParent.setParent(parent);
                studentParent.setStudent(studentService.findStudentById((Long)httpSession.getAttribute("studentId")));
                studentParentService.createOrUpdateStudentParent(studentParent);
            }
            System.out.println("parent2Id " + parent.getId());
            System.out.println("StudentId  " + httpSession.getAttribute("studentId"));
            httpSession.setAttribute("parent2Id", parent.getId());
        }

        modelAndView = new ModelAndView("redirect:/parents");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping(value = {"/cleanSessionParentDataOnLzpkRedirect"})
    public ModelAndView cleanSessionParentDataOnLzpkRedirect(HttpSession httpSession) {

        httpSession.setAttribute("parent1Id", null);
        httpSession.setAttribute("paren21Id", null);
        httpSession.setAttribute("regionCode", null);
        httpSession.setAttribute("municipalityCode", null);
        modelAndView = new ModelAndView("redirect:/lzpk");
        return modelAndView;
    }

}


