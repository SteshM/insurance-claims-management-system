package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@Entity
public class WorkflowStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stageName;
    private Date dateCreated;
    private String createdBy;
    private Date dateModified ;
    private String modifiedBy;
}
