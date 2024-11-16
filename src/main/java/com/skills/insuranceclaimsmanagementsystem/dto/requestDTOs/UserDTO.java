package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserDTO {
    private String username;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Date dateOfBirth;
    private String gender;
    private String password;
}
