package com.skills.insuranceclaimsmanagementsystem.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.ApprovalRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.ClaimRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.InvestigationReportDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UpdateClaimDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.ClaimsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ClaimsController {
    private final ClaimsService claimsService;

    @PostMapping("/claim")
    public ResponseDTO submitClaim(@RequestBody ClaimRequestDTO claimRequestDTO ){
       return claimsService.submitClaim(claimRequestDTO);

    }
    @GetMapping("/claim/{id}")
    public ResponseDTO getClaim(@PathVariable int id){
        return claimsService.getClaim(id);
    }
    @GetMapping("/claims")
    public ResponseDTO getClaims(){
        return claimsService.getClaims();
    }

    @GetMapping("/claim-types")
    public ResponseDTO getClaimTypes() throws JsonProcessingException {
        return claimsService.getClaimTypes();
    }

    @GetMapping("/claim-status")
    public ResponseDTO getClaimStatus() throws JsonProcessingException {
        return claimsService.getClaimStatus();
    }

    @PostMapping("/claim/{id}/investigation-report")
    public ResponseDTO claimInvestigationReport(@PathVariable int id, @RequestBody InvestigationReportDTO investigationReportDTO){
        return claimsService.investigationReport(id,investigationReportDTO);
    }

    @PostMapping("/claim/{id}/approval")
    public ResponseDTO claimApproval(@PathVariable int id, @RequestBody ApprovalRequestDTO approvalRequestDTO){
        return claimsService.approveClaim(id,approvalRequestDTO);

    }




}
