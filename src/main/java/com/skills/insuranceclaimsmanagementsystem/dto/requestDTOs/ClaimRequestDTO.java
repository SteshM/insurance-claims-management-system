package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import com.skills.insuranceclaimsmanagementsystem.models.Attachments;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ClaimRequestDTO {
    private String policyNumber;
    private Date incidentDate;
    private BigDecimal amountClaimed;
    private String description;
    private String name;
    private List<Attachments> attachments = new ArrayList<>();
}
