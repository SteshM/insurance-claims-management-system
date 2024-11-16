package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkflowResDTO {
    private int id;
    private ClaimResDTO claim;
    private UserResDTO assignedUser;
    private WorkflowStageResDTO workflowStage;
    private WorkStatusResDTO workflowStatus;




}
