package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.ClaimType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimTypeRepo extends JpaRepository<ClaimType, Integer> {

    ClaimType findByName(String name);
}
