package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ClaimBreakdownResDTO {
    private String name;
    private BigDecimal totalClaims;
    private BigDecimal totalAmountPaid;
}
