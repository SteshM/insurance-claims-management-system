package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "users")
public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;
        private String fullName;
        private String phoneNumber;
        @Column(unique = true, nullable = false)
        private String email;
        private Date dateOfBirth;
        private String gender;
        private Boolean active = true;
        private Date dateCreated;
        private String createdBy;
        private Date dateModified ;
        private String modifiedBy;
        @ManyToOne
        @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
        private Roles role;



}
