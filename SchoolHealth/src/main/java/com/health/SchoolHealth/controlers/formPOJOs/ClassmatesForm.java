package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.Student;
import lombok.Data;

import java.util.List;

@Data
public class ClassmatesForm {

    private List<Student> classmates;

    private List<String> classesNomenclature;

    private List<String> classLettersNomenclature;

    private String class_;

    private String classLetter;
}
