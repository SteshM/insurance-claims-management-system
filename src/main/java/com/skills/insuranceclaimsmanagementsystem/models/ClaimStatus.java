package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "claim_status")
public class ClaimStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date dateCreated;
    private Date dateModified;
    private String createdBy;
    private String modifiedBy;
}
