package com.skills.insuranceclaimsmanagementsystem.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.ClaimRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UpdateClaimDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.*;
import com.skills.insuranceclaimsmanagementsystem.models.*;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
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
        List<Attachments> attachments = claimRequestDTO.getAttachmentDTOs().stream().map(attachmentRequest -> {
            Attachments attachment = new Attachments();
            attachment.setType(attachmentRequest.getType());
            attachment.setUrl(attachmentRequest.getUrl());
            dataService.saveAttachment(attachment);
            return attachment;
        }).collect(Collectors.toList());

        claim.setAttachments(attachments);

        ClaimStatus claimStatus = dataService.findClaimStatusByName("pending");
        claim.setClaimStatus(claimStatus);
        var claimResDto = dataService.saveClaim(claim);
        return utilities.successResponse("Successfully submitted a claim", claimResDto);
    }

    public ResponseDTO getClaimTypes() throws JsonProcessingException {
        List<ClaimType>claimTypes=dataService.findAll();
        log.info("Fetched data from the db :{}",objectMapper.writeValueAsString(claimTypes));
        var claimTypeResDTO = claimTypes.stream().map(claimType -> modelMapper.map(claimType, ClaimTypeResDTO.class)).toList();
        return utilities.successResponse("Successfully retrieved claim types", claimTypeResDTO);
    }

    public ResponseDTO getRoles() {
        List<Roles>roles = dataService.findRoles();
        log.info("data {}", roles.size());
        var retrievedRoles = roles.stream().map(role -> modelMapper.map(role, RolesResDTO.class)).toList();
        return utilities.successResponse("Successfully retrieved roles", retrievedRoles);

    }

    public ResponseDTO getClaimStatus() throws JsonProcessingException {
        List<ClaimStatus>claimStatusList = dataService.fetchAll();
        log.info("About to fetch claim status from the db : {}",objectMapper.writeValueAsString(claimStatusList));
        var claimStatusResDTO =claimStatusList.stream().map(claimStatus -> modelMapper.map(claimStatus, ClaimStatusResDTO.class)).toList();
        return utilities.successResponse("Successfully retrieved claim status", claimStatusResDTO);


    }

    public ResponseDTO getUsers() {
        List<Users>users = dataService.fetchUsers();
        var userResDTO = users.stream().map(user -> modelMapper.map(user, UserResDTO.class)).toList();
        return utilities.successResponse("Successfully retrieved users", userResDTO);

    }

    public ResponseDTO getClaim(int id) {
        Claims claims = dataService.findByClaimId(id);
        log.info("Retrieving claim with ID: {}", id);
        if (claims == null) {
            log.warn("Claim with ID {} not found", id);
            return utilities.failedResponse(1,"Claim not found with ID: " + id,null);
        }
        var claimResDTO = ClaimResDTO.builder()
                .id(claims.getId())
                .amountClaimed(claims.getAmountClaimed())
                .name(claims.getClaimType().getName())
                .incidentDate(claims.getIncidentDate())
                .policyNumber(String.valueOf(claims.getPolicyNumber()))
                .url(claims.getAttachments().getFirst().getUrl())
                .type(claims.getAttachments().getFirst().getType())
                .name(claims.getClaimStatus().getName())
                .build();
        return utilities.successResponse("Successfully retrieved claim", claimResDTO);
    }

    public ResponseDTO getClaims() {
        List<Claims>claims = dataService.fetchClaims();
        List<ClaimResDTO>claimResDTOS = claims.stream()
                .map(claim -> modelMapper.map(claim, ClaimResDTO.class))
                .toList();
        return utilities.successResponse("Successfully retrieved claims", claimResDTOS);
    }

    public ResponseDTO updateClaimStatus(int id, UpdateClaimDTO updateClaimDTO) {
        Claims claim = dataService.findByClaimId(id);
        claim.getClaimStatus().setName(updateClaimDTO.getName());
        var updateClaimResDTO =dataService.saveClaim(claim);
        return utilities.successResponse("Successfully updated claim status", updateClaimResDTO);

    }
}


