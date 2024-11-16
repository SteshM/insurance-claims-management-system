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

    @GetMapping("/workflow-status")
    public ResponseDTO getWorkflowStatus() {
        return workflowService.getWorkflowStatus();
    }

    @GetMapping("/workflow-stages")
    public ResponseDTO getWorkflowStages() {
        return workflowService.getWorkflowStages();
    }
    @PostMapping("/claim/{id}/workflow")
    public ResponseDTO initiateWorkflow(@PathVariable int id, @RequestBody WorkflowRequestDTO workflowRequestDTO) {
        return workflowService.initiateWorkflow(id,workflowRequestDTO);
    }

    @GetMapping("/claim/{id}/workflow")
    public ResponseDTO getWorkflow(@PathVariable int id) {
        return workflowService.retrieveClaimHistory(id);
    }

}
