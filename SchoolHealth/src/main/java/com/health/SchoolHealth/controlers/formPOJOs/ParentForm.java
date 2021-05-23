package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.Parent;
import com.health.SchoolHealth.model.entities.ParentType;
import com.health.SchoolHealth.model.entities.User;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class ParentForm {

    Parent parent1;

    Parent parent2;

    List<ParentType> parentTypes;

}
