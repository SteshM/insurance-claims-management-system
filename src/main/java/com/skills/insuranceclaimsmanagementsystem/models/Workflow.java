package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateCreated;
    private String createdBy;
    private Date dateModified ;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "claimId", nullable = false)
    private Claims claim;

    @ManyToOne
    @JoinColumn(name = "assignedUserId")
    private Users assignedUser;

    @ManyToOne
    @JoinColumn(name = "stageId", nullable = false)
    private WorkflowStage workflowStage;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private WorkflowStatus workflowStatus;
}
