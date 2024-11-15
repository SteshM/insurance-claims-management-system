package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "claims")
public class Claims {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String policyNumber;
    private Date incidentDate;
    private Double amountClaimed;
    private boolean active;
    private Date dateCreated;
    private Date dateUpdated;
    private String createdBy;
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "claim_status_id")
    private ClaimStatus claimStatus;

    @ManyToOne
    @JoinColumn(name = "claim_type_id")
    private ClaimType claimType;

    @OneToMany
    private List<Attachments> attachments;
}
