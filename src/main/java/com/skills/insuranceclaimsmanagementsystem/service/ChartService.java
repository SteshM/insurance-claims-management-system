package com.skills.insuranceclaimsmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartService {


    public byte[] generateClaimTypePieChart(Map<String, Long> claimTypeData) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();

        claimTypeData.forEach(dataset::setValue);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Claim Type Breakdown",
                dataset,
                true, true, false);

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("Medical", new Color(160, 196, 255));
        plot.setSectionPaint("Vehicle", new Color(255, 160, 160));
        plot.setSectionPaint("Property", new Color(160, 255, 160));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(outputStream, pieChart, 600, 400);
        return outputStream.toByteArray();
    }
}

