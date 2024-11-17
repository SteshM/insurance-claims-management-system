package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateClaimDTO {
    @NotNull(message = "name is required")
    private String name;
}
