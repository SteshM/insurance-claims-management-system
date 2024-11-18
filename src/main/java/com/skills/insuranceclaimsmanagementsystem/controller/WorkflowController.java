package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.WorkflowRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkflowController {
    private final WorkflowService workflowService;

    @GetMapping("/workflow-status")
    @PreAuthorize("hasAuthority(CAN_VIEW_WORKFLOW_STATUS)")
    public ResponseDTO getWorkflowStatus() {
        return workflowService.getWorkflowStatus();
    }

    @GetMapping("/workflow-stages")
    @PreAuthorize("hasAuthority(CAN_VIEW_WORKFLOW_STAGES)")
    public ResponseDTO getWorkflowStages() {
        return workflowService.getWorkflowStages();
    }
    @PostMapping("/claim/{id}/workflow")
    @PreAuthorize("hasAuthority(CAN_INITIATE_WORKFLOW)")
    public ResponseDTO initiateWorkflow(@PathVariable int id, @RequestBody WorkflowRequestDTO workflowRequestDTO) {
        return workflowService.initiateWorkflow(id,workflowRequestDTO);
    }

    @GetMapping("/claim/{id}/workflow")
    @PreAuthorize("hasAuthority(CAN_VIEW_WORKFLOW)")
    public ResponseDTO getWorkflow(@PathVariable int id) {
        return workflowService.retrieveClaimHistory(id);
    }

}
