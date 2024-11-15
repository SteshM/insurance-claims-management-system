package com.skills.insuranceclaimsmanagementsystem.service;
import com.skills.insuranceclaimsmanagementsystem.dto.RequestDTOs.ClaimRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.ResponseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.models.ClaimStatus;
import com.skills.insuranceclaimsmanagementsystem.models.ClaimType;
import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClaimsService {
    private final DataService dataService;
    private final Utilities utilities;


    public ResponseDTO submitClaim(ClaimRequestDTO claimRequestDTO) {
        ClaimType claimType = dataService.findByName(claimRequestDTO.getName());
        log.info("claim {}",claimRequestDTO.getName());
        Claims claim = new Claims();
        claim.setActive(true);
        claim.setDateCreated(new Date());
        claim.setCreatedBy("customer");
        claim.setPolicyNumber(claimRequestDTO.getPolicyNumber());
        claim.setClaimType(claimType);
        claim.setIncidentDate(claimRequestDTO.getIncidentDate());
        claim.setAmountClaimed(claimRequestDTO.getAmountClaimed());

        ClaimStatus claimStatus = dataService.findClaimStatusByName("Pending");
        claim.setClaimStatus(claimStatus);
        var claimResDto = dataService.saveClaim(claim);
        return utilities.successResponse("Successfully submitted a claim", claimResDto);
    }

}


