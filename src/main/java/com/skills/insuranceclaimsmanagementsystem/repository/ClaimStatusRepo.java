package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimStatusRepo extends JpaRepository<ClaimStatus, Integer> {
    ClaimStatus findByName(String name);
}
