package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AddressForm;
import com.health.SchoolHealth.controlers.formPOJOs.SchoolForm;
import com.health.SchoolHealth.model.DAOs.SchoolDao;
import com.health.SchoolHealth.model.entities.School;
import com.health.SchoolHealth.services.AddressService;
import com.health.SchoolHealth.services.SchoolService;
import com.health.SchoolHealth.controlers.formPOJOs.SettlementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.health.SchoolHealth.util.RepositoryUtil.iterableToList;


@RestController
public class ImunisationsController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private SchoolDao schoolDao;

    ModelAndView modelAndView;

    ModelAndView newModelAndView;

    List<SettlementType>  allSettlementPlaceType;

    @GetMapping
    @RequestMapping(value = {"/imunisations"})
    public ModelAndView getImunisationsData() {
        System.out.println("in imunisations");

        return modelAndView;

    }


}


