package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
}
