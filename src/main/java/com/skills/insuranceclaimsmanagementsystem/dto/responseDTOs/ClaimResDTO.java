package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClaimResDTO {
    private int id;
    private String policyNumber;
    private Date incidentDate;
    private BigDecimal amountClaimed;
    private String description;
    private String name;
    private String url;
    private String type;

}
