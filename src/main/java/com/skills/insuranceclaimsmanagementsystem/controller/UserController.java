package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UserDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority(CAN_VIEW_ROLES)")
    public ResponseDTO getRoles() {
        return userService.getRoles();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority(CAN_VIEW_USERS)")
    public ResponseDTO getUsers() {
        return userService.getUsers();
    }
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority(CAN_VIEW_USER)")
    public ResponseDTO getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
    @PutMapping("/user/{id}")
    @PreAuthorize("hasAuthority(CAN_UPDATE_USERS)")
    public ResponseDTO updateUser(@PathVariable int id, @RequestBody UserDTO userDTO){
        return userService.updateUser(id,userDTO);
    }

}
