package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkflowStageResDTO {
    private int id;
    private String stageName;
    private WorkStatusResDTO workStatus;
    private WorkflowStageResDTO workflowStage;
    private int assignedTo;

}
