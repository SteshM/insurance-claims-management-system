package com.skills.insuranceclaimsmanagementsystem.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.*;
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
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClaimsService {
    private final DataService dataService;
    private final Utilities utilities;

    ModelMapper modelMapper = new ModelMapper();
    ObjectMapper objectMapper = new ObjectMapper();


    public ResponseDTO submitClaim(ClaimRequestDTO claimRequestDTO) {
        ClaimType claimType = dataService.findByName(claimRequestDTO.getName());
        log.info("claim {}",claimRequestDTO.getName());
        Claims claim = new Claims();
        claim.setActive(true);
        claim.setDateCreated(new Date());
        claim.setCreatedBy("customer");
        claim.setPolicyNumber(claimRequestDTO.getPolicyNumber());
        claim.setClaimType(claimType);
        claim.setIncidentDate(claimRequestDTO.getIncidentDate());
        claim.setAmountClaimed(claimRequestDTO.getAmountClaimed());
        List<Attachments> attachments = claimRequestDTO.getAttachments().stream().map(attachmentRequest -> {
            Attachments attachment = new Attachments();
            attachment.setType(attachmentRequest.getType());
            attachment.setUrl(attachmentRequest.getUrl());
            attachment.setDescription(attachmentRequest.getDescription());
            attachment.setClaim(claim);
            return attachment;
        }).toList();
        claim.setAttachments(attachments);
        ClaimStatus claimStatus = dataService.findClaimStatusByName("submitted");
        claim.setClaimStatus(claimStatus);
        var savedClaim = dataService.saveClaim(claim);
        var claimResDTO = modelMapper.map(savedClaim,ClaimResDTO.class);
        return utilities.successResponse("Successfully submitted a claim", claimResDTO);
    }

    public ResponseDTO getClaimTypes() throws JsonProcessingException {
        List<ClaimType>claimTypes=dataService.findAll();
        log.info("Fetched data from the db :{}",objectMapper.writeValueAsString(claimTypes));
        var claimTypeResDTO = claimTypes.stream().map(claimType -> modelMapper.map(claimType, ClaimTypeResDTO.class)).toList();
        return utilities.successResponse("Successfully retrieved claim types", claimTypeResDTO);
    }


    public ResponseDTO getClaimStatus() throws JsonProcessingException {
        List<ClaimStatus>claimStatusList = dataService.fetchAll();
        log.info("About to fetch claim status from the db : {}",objectMapper.writeValueAsString(claimStatusList));
        var claimStatusResDTO =claimStatusList.stream().map(claimStatus -> modelMapper.map(claimStatus, ClaimStatusResDTO.class)).toList();
        return utilities.successResponse("Successfully retrieved claim status", claimStatusResDTO);


    }

    public ResponseDTO getClaim(int id) {
        log.info("Retrieving claim with ID: {}", id);
        Optional<Claims> claims = dataService.findByClaimId(id);
        if (claims.isEmpty()) {
            return utilities.failedResponse(1,"Claim not found with ID: " + id,null);
        }

        var claimResDTO = modelMapper.map(claims.get(),ClaimResDTO.class);
        return utilities.successResponse("Successfully retrieved claim", claimResDTO);
    }

    public ResponseDTO getClaims() {
        List<Claims>claims = dataService.fetchClaims();
        List<ClaimResDTO>claimResDTOS = claims.stream()
                .map(claim -> modelMapper.map(claim, ClaimResDTO.class))
                .toList();
        return utilities.successResponse("Successfully retrieved claims", claimResDTOS);
    }
    public ResponseDTO investigationReport(int id, InvestigationReportDTO investigationReportDTO) {
        Optional<Claims> claimOptional = dataService.findByClaimId(id);

        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(1, "Claim not found for the provided ID", null);
        }

        Claims claim = claimOptional.get();

        // Map attachments to the claim
        List<Attachments> attachments = investigationReportDTO.getAttachments().stream().map(attachmentRequest -> {
            Attachments attachment = new Attachments();
            attachment.setType(attachmentRequest.getType());
            attachment.setUrl(attachmentRequest.getUrl());
            attachment.setDescription(attachmentRequest.getDescription());
            attachment.setClaim(claim);  // Associate attachment with the claim
            return attachment;
        }).toList();

        // Update claim's investigation completion date
        claim.setDateInvestigationCompleted(new Date());
        claim.setAttachments(attachments);

        // Update workflow status and stage to "Investigation" and "Completed"
        Workflow workflow = new Workflow();
        workflow.setClaim(claim);
        WorkflowStage investigationStage = dataService.findByStageName(investigationReportDTO.getStageName());
        workflow.setWorkflowStage(investigationStage);

        WorkflowStatus completedStatus = dataService.findByWorkStatus("Completed");
        workflow.setWorkflowStatus(completedStatus);

        // Associate assigned user for this workflow stage
        Optional<Users> assignedUserOptional = dataService.findByUserId(investigationReportDTO.getAssignedTo());
        assignedUserOptional.ifPresent(workflow::setAssignedUser);

        // Save workflow and update claim status
        dataService.saveWorkflow(workflow);
        ClaimStatus investigationStatus = dataService.findClaimStatusByName("pending");
        claim.setClaimStatus(investigationStatus);
        dataService.saveClaim(claim);

        return utilities.successResponse("Investigation report successfully submitted.", null);
    }




}


