package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UserDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.UserResDTO;
import com.skills.insuranceclaimsmanagementsystem.models.Roles;
import com.skills.insuranceclaimsmanagementsystem.models.Users;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final DataService dataService;
    private final Utilities utilities;
    ModelMapper modelMapper = new ModelMapper();
    public ResponseDTO createAccount(UserDTO userDTO) {
        Users user = new Users();
        modelMapper.map(userDTO, user);
        Optional<Roles> role = dataService.findById(userDTO.getRoleId());
        user.setRole(role.get());
        var savedUser = dataService.saveUser(user);
        var userResDTO = modelMapper.map(savedUser, UserResDTO.class);
        return utilities.successResponse("account created successfully",userResDTO);
    }
}
