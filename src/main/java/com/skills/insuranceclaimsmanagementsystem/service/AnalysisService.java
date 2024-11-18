package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalysisService {
    private final DataService dataService;

    public Map<String, Long> getClaimTypeBreakdown() {
        List<Claims> claims = dataService.fetchClaims();
        return claims.stream()
                .collect(Collectors.groupingBy(claim -> claim.getClaimType().getName(), Collectors.counting()));
    }

    public byte[] generateClaimStatusBreakdownChart() throws IOException {
        List<Claims> claims = dataService.fetchClaims();

        Map<String, Long> claimStatusBreakdown = claims.stream()
                .collect(Collectors.groupingBy(claim -> claim.getClaimStatus().getName(), Collectors.counting()));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        claimStatusBreakdown.forEach((status, count) -> {
            dataset.addValue(count, "Claim Status", status);  // Adding data to dataset
        });

        JFreeChart chart = ChartFactory.createBarChart(
                "Claim Status Breakdown", // Chart title
                "Claim Status", // X-axis label
                "Number of Claims", // Y-axis label
                dataset // Dataset
        );

        BufferedImage bufferedImage = chart.createBufferedImage(800, 600);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}


