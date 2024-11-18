package com.skills.insuranceclaimsmanagementsystem.controller;
import com.skills.insuranceclaimsmanagementsystem.service.AnalysisService;
import com.skills.insuranceclaimsmanagementsystem.service.ChartService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("analytics")
@RequiredArgsConstructor
public class AnalysisController {
    private final ChartService chartService;
    private final AnalysisService analysisService;


    @GetMapping("/claim-type-breakdown")
    @PreAuthorize("hasAuthority(CAN_VIEW_CLAIM_TYPE_BREAKDOWN)")
    public void getClaimTypeBreakdownChart(HttpServletResponse response) throws IOException {
        Map<String, Long> claimTypeData = analysisService.getClaimTypeBreakdown();
        byte[] chartBytes = chartService.generateClaimTypePieChart(claimTypeData);

        response.setContentType("image/png");
        response.getOutputStream().write(chartBytes);
    }

    @GetMapping("/claim-status-breakdown")
    @PreAuthorize("hasAuthority(CAN_VIEW_CLAIM_STATUS_BREAKDOWN)")
    public ResponseEntity<byte[]> generateClaimStatusBreakdown() throws IOException {
        byte[] chartImage = analysisService.generateClaimStatusBreakdownChart();
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(chartImage);
    }

}
