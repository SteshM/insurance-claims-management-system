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
        // Step 1: Fetch claims data
        List<Claims> claims = dataService.fetchClaims();

        // Step 2: Prepare data for the chart
        Map<String, Long> claimStatusBreakdown = claims.stream()
                .collect(Collectors.groupingBy(claim -> claim.getClaimStatus().getName(), Collectors.counting()));

        // Step 3: Create the dataset for the bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        claimStatusBreakdown.forEach((status, count) -> {
            dataset.addValue(count, "Claim Status", status);  // Adding data to dataset
        });

        // Step 4: Generate the bar chart using JFreeChart
        JFreeChart chart = ChartFactory.createBarChart(
                "Claim Status Breakdown", // Chart title
                "Claim Status", // X-axis label
                "Number of Claims", // Y-axis label
                dataset // Dataset
        );

        // Step 5: Convert the chart to a PNG image
        BufferedImage bufferedImage = chart.createBufferedImage(800, 600);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);

        // Step 6: Return the byte array representing the chart image
        return byteArrayOutputStream.toByteArray();
    }
}


