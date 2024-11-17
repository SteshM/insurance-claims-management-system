package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkflowRequestDTO {
    private int assignedTo;
    private String stageName;
}
