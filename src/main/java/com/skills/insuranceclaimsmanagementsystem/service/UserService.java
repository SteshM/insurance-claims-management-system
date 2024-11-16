package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.RolesResDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.UserResDTO;
import com.skills.insuranceclaimsmanagementsystem.models.Roles;
import com.skills.insuranceclaimsmanagementsystem.models.Users;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    ModelMapper modelMapper = new ModelMapper();
    private final DataService dataService;
    private final Utilities utilities;

    public ResponseDTO getRoles() {
        List<Roles>roles = dataService.findRoles();
        log.info("data {}", roles.size());
        var retrievedRoles = roles.stream().map(role -> modelMapper.map(role, RolesResDTO.class)).toList();
        return utilities.successResponse("Successfully retrieved roles", retrievedRoles);

    }

    public ResponseDTO getUsers() {
            List<Users> users = dataService.fetchUsers();
            var userResDTO = users.stream().map(user -> modelMapper.map(user, UserResDTO.class)).toList();
            return utilities.successResponse("Successfully retrieved users", userResDTO);


    }

    public ResponseDTO getUser(int id) {
        Optional<Users> user = dataService.findByUserId(id);
        UserResDTO userResDTO = modelMapper.map(user.get(), UserResDTO.class);
        return utilities.successResponse("Successfully retrieved user", userResDTO);
    }
}
