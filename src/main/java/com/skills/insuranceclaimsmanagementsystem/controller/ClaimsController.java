package com.skills.insuranceclaimsmanagementsystem.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.*;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.ClaimsService;
import jakarta.validation.Valid;
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
    public ResponseDTO submitClaim(@Valid @RequestBody ClaimRequestDTO claimRequestDTO ){
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
    public ResponseDTO claimInvestigationReport(@Valid @PathVariable int id, @RequestBody InvestigationReportDTO investigationReportDTO) throws JsonProcessingException {
        return claimsService.investigationReport(id,investigationReportDTO);
    }

    @PostMapping("/claim/{id}/approval")
    public ResponseDTO claimApproval(@Valid @PathVariable int id){
        return claimsService.approveClaim(id);

    }

    @PostMapping("/claim/{id}/disbursement")
    public ResponseDTO disbursePayment(@Valid @PathVariable int id, @RequestBody PaymentRequestDTO paymentRequestDTO){
        return claimsService.disburseClaimPayment(id,paymentRequestDTO);
    }



}
