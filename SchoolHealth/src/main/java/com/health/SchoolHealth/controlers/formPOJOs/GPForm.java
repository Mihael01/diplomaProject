package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.GP;
import com.health.SchoolHealth.model.entities.Student;
import lombok.Data;

import java.util.List;

@Data
public class GPForm {

    private GP gp;

    Boolean isListOfStudentsVisible;

    List<Student> studentsOfGP;

}
