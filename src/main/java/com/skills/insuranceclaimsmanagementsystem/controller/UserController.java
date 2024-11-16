package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/roles")
    public ResponseDTO getRoles() {
        return userService.getRoles();
    }

    @GetMapping("/users")
    public ResponseDTO getUsers() {
        return userService.getUsers();
    }
    @GetMapping("/user/{id}")
    public ResponseDTO getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

}
