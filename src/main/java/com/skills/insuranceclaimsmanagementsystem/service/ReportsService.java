package com.skills.insuranceclaimsmanagementsystem.service;


import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final DataService dataService;
//
//    public ResponseDTO getClaimByStatus() {
//        List<Claims>claims = dataService.fetchClaimsByStatus();
//    }
}
