package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reportName;
    @Lob
    private String reportData; // Assuming JSONB will be stored as a string
    private Date dateCreated;
    private Date dateModified;
    private String createdBy;
    private String modifiedBy;
}