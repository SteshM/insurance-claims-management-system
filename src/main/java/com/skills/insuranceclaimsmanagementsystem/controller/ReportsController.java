package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.ClaimReportRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportsController {
    private final ReportsService reportsService;

    @GetMapping("/claim/status")
    public ResponseDTO claimStatus(@RequestBody ClaimReportRequestDTO claimReportRequestDTO) {
        return reportsService.generateClaimReportByClaimStatus(claimReportRequestDTO);
    }

    @GetMapping("/claim/type")
    public ResponseDTO claimType(@RequestBody ClaimReportRequestDTO claimReportRequestDTO) {
        return reportsService.generateClaimReportByType(claimReportRequestDTO);
    }


}
