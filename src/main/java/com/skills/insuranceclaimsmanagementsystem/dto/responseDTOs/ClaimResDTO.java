package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private ClaimStatusResDTO claimStatus;
    private ClaimTypeResDTO claimType;
    private List<AttachmentResDTO> attachments;


}
