package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
public class ClaimReportDTO {
    private String name;
    private BigDecimal totalClaims;
    private BigDecimal totalAmountPaid;
    private Map<String, Integer> claimBreakdown;
}
