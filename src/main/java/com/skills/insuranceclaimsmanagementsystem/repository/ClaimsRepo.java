package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimsRepo extends JpaRepository<Claims, Integer> {
    Claims findById(int claimId);
}
