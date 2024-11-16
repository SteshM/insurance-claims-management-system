package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.WorkflowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface workflowStatusRepo extends JpaRepository<WorkflowStatus,Integer> {
    WorkflowStatus findByStatusName(String inProgress);
}
