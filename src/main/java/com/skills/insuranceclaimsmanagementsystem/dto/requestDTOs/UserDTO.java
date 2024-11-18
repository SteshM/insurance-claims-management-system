package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserDTO {
    @NotNull(message = "username is required")
    @Column(unique = true)
    private String username;
    @NotNull(message = "fullName is required")
    private String fullName;
    @NotNull(message = "phoneNumber is required")
    private String phoneNumber;
    @Email
    @Column(unique = true)
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "dateOfBirth is required")
    private Date dateOfBirth;
    @NotNull(message = "gender is required")
    private String gender;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "roleId is required")
    private int roleId;


}
