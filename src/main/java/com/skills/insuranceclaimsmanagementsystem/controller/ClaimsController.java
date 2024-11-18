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

    @PostMapping("/customer/claim")
    public ResponseDTO submitClaim(@Valid @RequestBody ClaimRequestDTO claimRequestDTO ){
       return claimsService.submitClaim(claimRequestDTO);

    }
    @GetMapping("/claim/{id}")
    @PreAuthorize("hasAnyAuthority('CAN_VIEW_CLAIM','CUSTOMER','INSURER')")
    public ResponseDTO getClaim(@PathVariable int id){
        return claimsService.getClaim(id);
    }
    @GetMapping("/insurer/claims")
    public ResponseDTO getClaims(){
        return claimsService.getClaims();
    }

    @GetMapping("/customer/claim-types")
    public ResponseDTO getClaimTypes() throws JsonProcessingException {
        return claimsService.getClaimTypes();
    }

    @GetMapping("/insurer/claim-status")
    public ResponseDTO getClaimStatus() throws JsonProcessingException {
        return claimsService.getClaimStatus();
    }

    @PostMapping("/insurer/claim/{id}/investigation-report")
    public ResponseDTO claimInvestigationReport(@Valid @PathVariable int id, @RequestBody InvestigationReportDTO investigationReportDTO) throws JsonProcessingException {
        return claimsService.investigationReport(id,investigationReportDTO);
    }

    @PostMapping("/insurer/claim/{id}/approval")
    public ResponseDTO claimApproval(@Valid @PathVariable int id){
        return claimsService.approveClaim(id);

    }

    @PostMapping("/insurer/claim/{id}/disbursement")
    public ResponseDTO disbursePayment(@Valid @PathVariable int id, @RequestBody PaymentRequestDTO paymentRequestDTO){
        return claimsService.disburseClaimPayment(id,paymentRequestDTO);
    }



}
