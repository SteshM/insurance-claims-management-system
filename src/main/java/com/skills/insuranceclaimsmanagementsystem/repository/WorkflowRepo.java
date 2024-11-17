package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import com.skills.insuranceclaimsmanagementsystem.models.Workflow;
import com.skills.insuranceclaimsmanagementsystem.models.WorkflowStage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkflowRepo extends JpaRepository<Workflow, Integer> {
    Optional<Workflow> findByClaimAndStage(Claims claim, WorkflowStage investigationStage);
}
