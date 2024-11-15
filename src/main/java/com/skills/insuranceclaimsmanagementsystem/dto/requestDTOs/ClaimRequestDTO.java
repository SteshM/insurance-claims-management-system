package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ClaimRequestDTO {
    private String policyNumber;  // changed to String
    private Date incidentDate;
    private Double amountClaimed;
    private String description;
    private String name;
    private List<AttachmentDTO> attachmentDTOs;
}
