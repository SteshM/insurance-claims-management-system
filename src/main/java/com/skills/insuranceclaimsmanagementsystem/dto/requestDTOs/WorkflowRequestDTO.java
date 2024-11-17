package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkflowRequestDTO {
    @NotNull(message = "assignedTo is required")
    private int assignedTo;
    @NotNull(message = "provide stage name")
    private String stageName;
}
