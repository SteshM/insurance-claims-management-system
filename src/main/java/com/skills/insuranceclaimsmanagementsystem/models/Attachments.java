package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class Attachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String url;
    private String description;
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claims claim;
}
