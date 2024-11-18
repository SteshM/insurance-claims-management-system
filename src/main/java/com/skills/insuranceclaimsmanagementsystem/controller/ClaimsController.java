package com.skills.insuranceclaimsmanagementsystem.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.*;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.ClaimsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ClaimsController {
    private final ClaimsService claimsService;

    @PostMapping("/claim")
    @PreAuthorize("hasAuthority(CAN_SUBMIT_CLAIM)")
    public ResponseDTO submitClaim(@Valid @RequestBody ClaimRequestDTO claimRequestDTO ){
       return claimsService.submitClaim(claimRequestDTO);

    }
    @GetMapping("/claim/{id}")
    @PreAuthorize("hasAuthority(CAN_VIEW_CLAIM)")
    public ResponseDTO getClaim(@PathVariable int id){
        return claimsService.getClaim(id);
    }
    @GetMapping("/claims")
    @PreAuthorize("hasAuthority(CAN_VIEW_CLAIMS)")
    public ResponseDTO getClaims(){
        return claimsService.getClaims();
    }

    @GetMapping("/claim-types")
    @PreAuthorize("hasAuthority(CAN_VIEW_CLAIM_TYPES)")
    public ResponseDTO getClaimTypes() throws JsonProcessingException {
        return claimsService.getClaimTypes();
    }

    @GetMapping("/claim-status")
    @PreAuthorize("hasAuthority(CAN_VIEW_CLAIM_STATUS)")
    public ResponseDTO getClaimStatus() throws JsonProcessingException {
        return claimsService.getClaimStatus();
    }

    @PostMapping("/claim/{id}/investigation-report")
    @PreAuthorize("hasAuthority(CAN_INVESTIGATE_REPORT)")
    public ResponseDTO claimInvestigationReport(@Valid @PathVariable int id, @RequestBody InvestigationReportDTO investigationReportDTO) throws JsonProcessingException {
        return claimsService.investigationReport(id,investigationReportDTO);
    }

    @PostMapping("/claim/{id}/approval")
    @PreAuthorize("hasAuthority(CAN_APPROVE_CLAIM)")
    public ResponseDTO claimApproval(@Valid @PathVariable int id){
        return claimsService.approveClaim(id);

    }

    @PostMapping("/claim/{id}/disbursement")
    @PreAuthorize("hasAuthority(CAN_DISBURSE_CLAIM_PAYMENT)")
    public ResponseDTO disbursePayment(@Valid @PathVariable int id, @RequestBody PaymentRequestDTO paymentRequestDTO){
        return claimsService.disburseClaimPayment(id,paymentRequestDTO);
    }



}
