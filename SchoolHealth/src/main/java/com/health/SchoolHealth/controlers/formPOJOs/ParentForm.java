package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class ParentForm {

    Parent parent1;

    Parent parent2;

    List<ParentType> parentTypes;

}
