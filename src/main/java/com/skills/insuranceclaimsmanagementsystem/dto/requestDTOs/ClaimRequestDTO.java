package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import com.skills.insuranceclaimsmanagementsystem.models.Attachments;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ClaimRequestDTO {
    @NotNull(message = "policy number cannot be null")
    private String policyNumber;
    @NotNull(message = "incident date cannot be null")
    private Date incidentDate;
    @NotNull(message = "amount claimed cannot be null")
    private BigDecimal amountClaimed;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "Attachments required")
    private List<Attachments> attachments = new ArrayList<>();
}
