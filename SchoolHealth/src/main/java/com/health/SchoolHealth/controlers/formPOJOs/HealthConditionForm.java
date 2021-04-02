package com.health.SchoolHealth.controlers.formPOJOs;

import com.health.SchoolHealth.model.entities.ConfirmationFlag;
import com.health.SchoolHealth.model.entities.HealthCondition;
import com.health.SchoolHealth.model.entities.ImmunizationComment;
import com.health.SchoolHealth.model.entities.Student;
import lombok.Data;

import java.util.List;

@Data
public class HealthConditionForm {

    private HealthCondition healthCondition;

    private List<ConfirmationFlag> confirmationFlagsNomenclature;

    private List<ImmunizationComment> immunizationCommentsNomenclature;

    private String additionalActivities;

    private String exemptFromPhysicalEducation;

    private String therapeuticPhysicalEducation;

    private String  missingImmunizationFlag;

    private String mandatoryImmunizationFlag;
}
