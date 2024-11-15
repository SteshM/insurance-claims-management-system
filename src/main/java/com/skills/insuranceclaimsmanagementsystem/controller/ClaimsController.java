package com.skills.insuranceclaimsmanagementsystem.controller;

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

    @GetMapping("/claim-types")
    public ResponseDTO getClaimTypes() {
        return claimsService.getClaimTypes();
    }



}
