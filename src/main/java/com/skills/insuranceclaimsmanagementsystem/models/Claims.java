package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    private BigDecimal amountClaimed;
    private boolean active;
    private Date dateCreated;
    private Date dateModified;
    private String createdBy;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "claimStatusId")
    private ClaimStatus claimStatus;

    @ManyToOne
    @JoinColumn(name = "claimTypeId")
    private ClaimType claimType;

    @OneToMany
    private List<Attachments> attachments;
}
