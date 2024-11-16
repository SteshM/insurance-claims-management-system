package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.WorkflowRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkflowController {
    private final WorkflowService workflowService;

    @PostMapping("/workflow/claim/{id}")
    public ResponseDTO initiateWorkflow(@PathVariable int id, @RequestBody WorkflowRequestDTO workflowRequestDTO) {
        return workflowService.initiateWorkflow(id,workflowRequestDTO);
    }

    @GetMapping("/claims/{id}/workflow")
    public ResponseDTO getWorkflow(@PathVariable int id) {
        return workflowService.RetrieveClaimHistory(id);
    }
}
