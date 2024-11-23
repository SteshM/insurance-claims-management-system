package com.skills.insuranceclaimsmanagementsystem.controller;
import com.skills.insuranceclaimsmanagementsystem.service.AnalysisService;
import com.skills.insuranceclaimsmanagementsystem.service.ChartService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("insurer/claim-type-breakdown")
    public void getClaimTypeBreakdownChart(HttpServletResponse response) throws IOException {
        Map<String, Long> claimTypeData = analysisService.getClaimTypeBreakdown();
        byte[] chartBytes = chartService.generateClaimTypePieChart(claimTypeData);

        response.setContentType("image/png");
        response.getOutputStream().write(chartBytes);
    }

    @GetMapping("insurer/claim-status-breakdown")
    public ResponseEntity<byte[]> generateClaimStatusBreakdown() throws IOException {
        byte[] chartImage = analysisService.generateClaimStatusBreakdownChart();
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(chartImage);
    }

}
