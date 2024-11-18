package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.LoginDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UserDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allow")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/create-account")
    public ResponseDTO createAccount(@Valid @RequestBody UserDTO userDTO){
        return authenticationService.createAccount(userDTO);
    }
    @PostMapping("/login")
    public ResponseDTO login(@Valid @RequestBody LoginDTO loginDTO){
        return authenticationService.login(loginDTO);
    }
}
