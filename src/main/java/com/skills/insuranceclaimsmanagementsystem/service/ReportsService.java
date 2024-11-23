package com.skills.insuranceclaimsmanagementsystem.service;
import com.skills.insuranceclaimsmanagementsystem.configurations.SystemConfigs;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.ClaimReportRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ClaimBreakdownResDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ClaimReportDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final DataService dataService;
    private final Utilities utilities;
    private final SystemConfigs systemConfigs;

    public ResponseDTO generateClaimReportByClaimStatus(ClaimReportRequestDTO claimReportRequestDTO) {
        List<Claims> claims = dataService.fetchClaims();

        // Filter claims based on the requested claim status
        List<Claims> filteredClaims = claims.stream()
                .filter(claim -> claim.getClaimStatus().getName().equalsIgnoreCase(claimReportRequestDTO.getName()))
                .toList();

        // Calculate the total number of claims and total amount paid
        int totalClaims = filteredClaims.size();
        BigDecimal totalAmountPaid = filteredClaims.stream()
                .map(Claims::getAmountClaimed)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Breakdown by claim type
        LinkedHashMap<String, Integer> claimBreakdown = new LinkedHashMap<>();
        filteredClaims.forEach(claim -> {
            String claimTypeName = claim.getClaimType().getName(); // Get the name of the claim type
            claimBreakdown.put(claimTypeName, claimBreakdown.getOrDefault(claimTypeName, 0) + 1); // Increment count for this claim type
        });

        // Prepare ClaimReportDTO with the breakdown
        ClaimReportDTO claimReportDTO = new ClaimReportDTO();
        claimReportDTO.setName(claimReportRequestDTO.getName());
        claimReportDTO.setTotalClaims(BigDecimal.valueOf(totalClaims));
        claimReportDTO.setTotalAmountPaid(totalAmountPaid);
        claimReportDTO.setClaimBreakdown(claimBreakdown);

        return utilities.successResponse(systemConfigs.getSuccessMessage(), claimReportDTO);
    }

    public ResponseDTO generateClaimReportByType(ClaimReportRequestDTO claimReportRequestDTO) {
        // Fetch all claims
        List<Claims> claims = dataService.fetchClaims();

        // Filter claims by claim type specified in the request
        List<Claims> filteredClaims = claims.stream()
                .filter(claim -> claim.getClaimType().getName().equalsIgnoreCase(claimReportRequestDTO.getName()))
                .toList();

        // Calculate total number of claims and total amount paid
        int totalClaims = filteredClaims.size();
        BigDecimal totalAmountPaid = filteredClaims.stream()
                .map(Claims::getAmountClaimed)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Prepare ClaimReportDTO
        ClaimBreakdownResDTO claimBreakdownResDTO = new ClaimBreakdownResDTO();
        claimBreakdownResDTO.setName(claimReportRequestDTO.getName());
        claimBreakdownResDTO.setTotalClaims(BigDecimal.valueOf(totalClaims));
        claimBreakdownResDTO.setTotalAmountPaid(totalAmountPaid);

        // Return response
        return utilities.successResponse(systemConfigs.getSuccessMessage(), claimBreakdownResDTO);
    }






}

