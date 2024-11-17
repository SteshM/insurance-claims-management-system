package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserDTO {
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "fullName is required")
    private String fullName;
    @NotNull(message = "phoneNumber is required")
    private String phoneNumber;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "dateOfBirth is required")
    private Date dateOfBirth;
    @NotNull(message = "gender is required")
    private String gender;
    private String password;
}
