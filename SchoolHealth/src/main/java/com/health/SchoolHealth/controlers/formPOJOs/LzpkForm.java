package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.SexType;
import com.health.SchoolHealth.model.entities.Student;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.List;

@Data
public class LzpkForm {

    private Student student;

    private List<SexType> sexNomenclature;

    private List<String> classesNomenclature;

    private List<String> classLettersNomenclature;

}
