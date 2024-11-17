package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import com.skills.insuranceclaimsmanagementsystem.models.Attachments;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class InvestigationReportDTO {
    @NotNull(message = "Attachments required")
    private List<Attachments> attachments = new ArrayList<>();

}
