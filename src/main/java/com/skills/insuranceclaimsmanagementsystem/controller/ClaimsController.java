package com.skills.insuranceclaimsmanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.ClaimRequestDTO;
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
    @GetMapping("/claim/{claimId}")
    public ResponseDTO getClaim(@PathVariable int claimId){
        return claimsService.getClaim(claimId);
    }

    @GetMapping("/claim-types")
    public ResponseDTO getClaimTypes() throws JsonProcessingException {
        return claimsService.getClaimTypes();
    }
    @GetMapping("/roles")
    public ResponseDTO getRoles() {
        return claimsService.getRoles();
    }
    @GetMapping("/claim-status")
    public ResponseDTO getClaimStatus() throws JsonProcessingException {
        return claimsService.getClaimStatus();
    }
    @GetMapping("/users")
    public ResponseDTO getUsers() throws JsonProcessingException {
        return claimsService.getUsers();
    }



}
