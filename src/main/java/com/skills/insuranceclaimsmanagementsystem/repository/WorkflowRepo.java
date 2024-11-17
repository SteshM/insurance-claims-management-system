package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import com.skills.insuranceclaimsmanagementsystem.models.Workflow;
import com.skills.insuranceclaimsmanagementsystem.models.WorkflowStage;
import com.skills.insuranceclaimsmanagementsystem.models.WorkflowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkflowRepo extends JpaRepository<Workflow, Integer> {
    Optional<Workflow> findByClaimAndWorkflowStage(Claims claim, WorkflowStage investigationStage);

    Optional<Workflow> findByClaimAndWorkflowStageAndWorkflowStatus(Claims claim, WorkflowStage workflowStage, WorkflowStatus workflowStatus);

}
