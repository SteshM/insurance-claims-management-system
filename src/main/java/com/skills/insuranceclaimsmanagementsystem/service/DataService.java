package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.models.ClaimStatus;
import com.skills.insuranceclaimsmanagementsystem.models.ClaimType;
import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import com.skills.insuranceclaimsmanagementsystem.repository.ClaimStatusRepo;
import com.skills.insuranceclaimsmanagementsystem.repository.ClaimTypeRepo;
import com.skills.insuranceclaimsmanagementsystem.repository.ClaimsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {
    private final ClaimTypeRepo claimTypeRepo;
    private final ClaimsRepo claimsRepo;
    private final ClaimStatusRepo claimStatusRepo;

    public ClaimType findByName(String name) {
        return claimTypeRepo.findByName(name);
    }

    public void saveClaimType(ClaimType claimType) {
      claimTypeRepo.save(claimType);
    }

    public Claims saveClaim(Claims claim) {
       return claimsRepo.save(claim);
    }

    public ClaimStatus findClaimStatusByName(String pending) {
       return claimStatusRepo.findByName(pending);
    }

    public List<ClaimType> findAll() {
        return claimTypeRepo.findAll();
    }
}
