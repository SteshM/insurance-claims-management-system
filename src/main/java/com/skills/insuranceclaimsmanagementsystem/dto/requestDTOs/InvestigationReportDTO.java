package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import com.skills.insuranceclaimsmanagementsystem.models.Attachments;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class InvestigationReportDTO {
    private int assignedTo;
    private List<Attachments> attachments = new ArrayList<>();
    private String stageName;

}
