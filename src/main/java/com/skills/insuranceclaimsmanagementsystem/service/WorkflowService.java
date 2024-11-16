package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.WorkflowRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.WorkflowResDTO;
import com.skills.insuranceclaimsmanagementsystem.models.*;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkflowService {
    ModelMapper modelMapper = new ModelMapper();
    private final DataService dataService;
    private final Utilities utilities;

    public ResponseDTO initiateWorkflow(int id, WorkflowRequestDTO workflowRequestDTO) {
        Optional<Claims> claimOptional = dataService.findByClaimId(id);

        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(1,"Claim not found for the provided ID", null);
        }
        Workflow workflow = new Workflow();
        workflow.setClaim(claimOptional.get());

        WorkflowStage workflowStage = dataService.findByStageName("investigation");
        workflow.setWorkflowStage(workflowStage);
        WorkflowStatus workflowStatus = dataService.findByWorkStatus("in-progress");
        workflow.setWorkflowStatus(workflowStatus);
        Optional<Users> users = dataService.findByUserId(workflowRequestDTO.getAssignedTo());
        workflow.setAssignedUser(users.get());
      var initiatedWorkflow =  dataService.saveWorkflow(workflow);
      var workflowResDTO = modelMapper.map(initiatedWorkflow, ResponseDTO.class);
      return utilities.successResponse("successfully initiated workflow", workflowResDTO);

    }

    public ResponseDTO RetrieveClaimHistory(int id) {
        Optional<Claims> claimOptional = dataService.findByClaimId(id);

        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(1,"Claim not found for the provided ID", null);
        }
        List<Workflow>history = dataService.fetchWorkflow();
        List<WorkflowResDTO>workflowResDTOS = history.stream()
                .map(
                        workflow -> modelMapper.map(workflow, WorkflowResDTO.class)
                ).toList();
        return utilities.successResponse("successfully retrieved workflow", workflowResDTOS);

    }
}
