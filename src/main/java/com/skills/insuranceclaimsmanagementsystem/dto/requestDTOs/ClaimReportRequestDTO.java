package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClaimReportRequestDTO {
    @NotNull(message = "provide name")
    private String name;
}
