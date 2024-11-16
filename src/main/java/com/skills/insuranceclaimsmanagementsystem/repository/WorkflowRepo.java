package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRepo extends JpaRepository<Workflow, Integer> {
}
