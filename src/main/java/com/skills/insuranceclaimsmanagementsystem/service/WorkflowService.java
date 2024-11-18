package com.skills.insuranceclaimsmanagementsystem.service;
import com.skills.insuranceclaimsmanagementsystem.configurations.SystemConfigs;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.WorkflowRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.*;
import com.skills.insuranceclaimsmanagementsystem.models.*;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkflowService {
    ModelMapper modelMapper = new ModelMapper();
    private final DataService dataService;
    private final Utilities utilities;
    private final SystemConfigs systemConfigs;

    public ResponseDTO getWorkflowStatus() {
        List<WorkflowStatus>statuses = dataService.fetchWorkflowStatus();
        List<WorkStatusResDTO>workStatusResDTOS =statuses.stream().map(
                workflowStatus -> modelMapper.map(workflowStatus, WorkStatusResDTO.class)
        ).toList();
        return utilities.successResponse(systemConfigs.getSuccessMessage(), workStatusResDTOS);
    }


    public ResponseDTO getWorkflowStages() {
        List<WorkflowStage>stages = dataService.fetchWorkflowStages();
        List<WorkflowStageResDTO>stageResDTOS = stages.stream().map(
                workflowStage -> modelMapper.map(workflowStage, WorkflowStageResDTO.class)
        ).toList();
        return utilities.successResponse(systemConfigs.getSuccessMessage(), stageResDTOS);
    }


    public ResponseDTO initiateWorkflow(int id, WorkflowRequestDTO workflowRequestDTO) {
        Optional<Claims> claimOptional = dataService.findByClaimId(id);

        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(systemConfigs.getFailedStatusCode(), "Claim not found for the provided ID", null);
        }
        var claim = claimOptional.get();
        Workflow workflow = new Workflow();
        workflow.setClaim(claim);

        WorkflowStage workflowStage = dataService.findByStageName(workflowRequestDTO.getStageName());
        workflow.setWorkflowStage(workflowStage);
        WorkflowStatus workflowStatus = dataService.findByWorkStatus("in-progress");
        workflow.setWorkflowStatus(workflowStatus);
        Optional<Users> users = dataService.findByUserId(workflowRequestDTO.getAssignedTo());
        workflow.setAssignedUser(users.get());
        var initiatedWorkflow = dataService.saveWorkflow(workflow);
        ClaimStatus claimStatus = dataService.findClaimStatusByName("pending");
        claim.setClaimStatus(claimStatus);
        dataService.saveClaim(claim);
        if (workflowRequestDTO.getStageName().equalsIgnoreCase("disbursement")){
            log.info("This is a disbursement workflow. Proceed to initiate a payment");
            var payment = new Payments();
            payment.setClaim(claim);
            payment.setAmount(claim.getAmountClaimed());
            payment.setStatus(dataService.findByStatusName("pending"));
            dataService.savePayment(payment);
        }
        var workflowResDTO = modelMapper.map(initiatedWorkflow, WorkflowResDTO.class);
        return utilities.successResponse(systemConfigs.getSuccessMessage(), workflowResDTO);
    }



        public ResponseDTO retrieveClaimHistory(int id) {
        Optional<Claims> claimOptional = dataService.findByClaimId(id);
        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(1,"Claim not found for the provided ID", null);
        }
        List<Workflow>history = dataService.fetchWorkflow();
        List<WorkflowResDTO>workflowResDTOS = history.stream()
                .map(
                        workflow -> modelMapper.map(workflow, WorkflowResDTO.class)
                ).toList();
        return utilities.successResponse(systemConfigs.getSuccessMessage(), workflowResDTOS);

    }


}
