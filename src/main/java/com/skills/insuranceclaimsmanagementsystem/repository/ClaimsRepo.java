package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaimsRepo extends JpaRepository<Claims, Integer> {
    Optional<Claims> findById(int claimId);
}
