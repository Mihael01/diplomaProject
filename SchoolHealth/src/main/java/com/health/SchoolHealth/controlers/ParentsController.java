package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.ParentForm;
import com.health.SchoolHealth.model.entities.Parent;
import com.health.SchoolHealth.model.entities.ParentType;
import com.health.SchoolHealth.services.AddressService;
import com.health.SchoolHealth.services.ParentService;
import com.health.SchoolHealth.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
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


        modelAndView.addObject("parentForm", parentForm);

        // Адрес
        setParentAddress(parentForm.getParent1(), httpSession, addressForm, 1);
        setParentAddress(parentForm.getParent2(), httpSession, addressForm2, 2);

        System.out.println("addressForm.getAddress().getId() " + addressForm.getAddress().getId());
        System.out.println("addressForm2.getAddress2().getId() " + addressForm2.getAddress2().getId());
        return modelAndView;

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

        if (parentForm.getParent1() != null) {
            // Ако нямаме записан адрес, сетвавме полето на null, защото в противен случай при запис на родителя,
            // hibernate търси свързан обект в таблицата с адреси, а нямаме Id
            if (parentForm.getParent1().getParentAddress() != null
                    && parentForm.getParent1().getParentAddress().getId() == null) {
                parentForm.getParent1().setParentAddress(null);
            }

            Parent parent = parentService.createOrUpdateParent(parentForm.getParent1());
            httpSession.setAttribute("parent1Id", parent.getId());
        } else {
            // Ако нямаме записан адрес, сетвавме полето на null, защото в противен случай при запис на родителя,
            // hibernate търси свързан обект в таблицата с адреси, а нямаме Id
            if (parentForm.getParent2().getParentAddress() != null
                    && parentForm.getParent2().getParentAddress().getId() == null) {
                parentForm.getParent2().setParentAddress(null);
            }

            Parent parent = parentService.createOrUpdateParent(parentForm.getParent2());
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
        modelAndView = new ModelAndView("redirect:/lzpk");
        return modelAndView;
    }

}


