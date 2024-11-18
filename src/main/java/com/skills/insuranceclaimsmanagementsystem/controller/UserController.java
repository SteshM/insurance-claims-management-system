package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.AuthDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UserDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/insurer/roles")
    public ResponseDTO getRoles() {
        return userService.getRoles();
    }

    @GetMapping("/insurer/users")
    public ResponseDTO getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/insurer/user/{id}")
    public ResponseDTO getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PutMapping("/insurer/user/{id}")
    public ResponseDTO updateUser(@PathVariable int id, @RequestBody UserDTO userDTO){
        return userService.updateUser(id,userDTO);
    }


}
