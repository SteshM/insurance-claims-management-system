package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePaymentStatusDTO {
    @NotNull(message = "status name is required")
    private String statusName;

}
