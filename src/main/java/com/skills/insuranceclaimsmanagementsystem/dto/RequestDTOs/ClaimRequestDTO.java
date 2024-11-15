package com.skills.insuranceclaimsmanagementsystem.dto.RequestDTOs;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class ClaimRequestDTO {
    private String policyNumber;  // changed to String
    private Date incidentDate;
    private Double amountClaimed;
    private String description;
    private String name;
}
