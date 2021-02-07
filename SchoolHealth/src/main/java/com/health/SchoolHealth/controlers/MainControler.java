package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = {"main"}) ///{pathVariable}
public class MainControler {

    static String currentUrl;

    @Autowired
    private MainService mainService;

    @GetMapping
//    @RequestMapping(value = {"", "/*", "/*/*", "/*/*/*"})
    public List<String> getFilesAndFolders() { //(HttpServletRequest request //@PathVariable("pathVariable")String pathVariable
        System.out.println("Wikam controlera11");


        return mainService.getAllAddictions();

    }



}


