package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.WorkflowStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowStageRepo extends JpaRepository<WorkflowStage, Long> {
}
