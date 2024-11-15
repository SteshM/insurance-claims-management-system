package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date dateCreated;
    private Date dateModified;
    private String createdBy;
    private String modifiedBy;
    private boolean active;
}
