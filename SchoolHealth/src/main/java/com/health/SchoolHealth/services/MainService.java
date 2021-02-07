package com.health.SchoolHealth.services;

import com.health.SchoolHealth.model.DAOs.AddictionsDao;
import com.health.SchoolHealth.model.DAOs.TestDao;
import com.health.SchoolHealth.model.DAOs.impl.AddictionsDaoImpl;
import com.health.SchoolHealth.model.entities.Addictions;

import com.health.SchoolHealth.model.entities.TestTable;
import org.springframework.context.ApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    @Autowired
    AddictionsDao addictionsDao;

    @Autowired
    TestDao testDao;

    public List<String> getAllAddictions() {

        List<Addictions> addictions = new ArrayList<>();
        Addictions addiction = new Addictions();
        addiction.setAddictionsDescription("Пристрастеност към тютюнопушене");
        addiction.setMethodologies("Разговори, примери");
        addiction.setResults("Решава, че цигарите вредят и минава на кокаин");
        addictions.add(addiction);
        addictions = new ArrayList<>();
        Addictions addiction2 = new Addictions();
        addiction2.setAddictionsDescription("Пристрастеност към бягане от час");
        addiction2.setMethodologies("Едно яко контролно");
        addiction2.setResults("Започва да вижда звезди посред бял ден");
        addictions.add(addiction2);
//        addictionsDao.saveAll(addictions);
//        addictionsDao.save(addiction);
//        Iterable<Addictions> savedAddictions = (Iterable<Addictions>) addictionsDao.findAll().iterator();
//        for (Addictions a : savedAddictions) {
//            System.out.println("description: " + a.getAddictionsDescription());
//            System.out.println("methodologies: " + a.getMethodologies());
//            System.out.println("results: " + a.getResults());
//        }

//        Iterator<Addictions> foundAddictions = addictionsDao.findAll().iterator();

//        List<Addictions> selectedAddictions = new ArrayList<>();
//        foundAddictions.forEachRemaining(selectedAddictions::add);
        Iterator<TestTable> foundTestData = testDao.findAll().iterator();

        TestTable testTable = new TestTable();
        testTable.setTestCode("T3");
        testTable.setTestName("Test name 3 from java.");

        testDao.save(testTable);

        foundTestData = testDao.findAll().iterator();
//        while(foundTestData.hasNext()) {
//            System.out.println(foundTestData.next().getTestName());
//        }

        List<TestTable> selectedTestData = new ArrayList<>();
        foundTestData.forEachRemaining(selectedTestData::add);

        List<String> methodologies =
                selectedTestData.stream().map(td -> {System.out.println(td.getTestName());
                    return td.getTestName();} ).collect(Collectors.toList());
        System.out.println("COUNT = " + methodologies.size());
        return methodologies;
    }


}
