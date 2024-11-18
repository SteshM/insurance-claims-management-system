package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.AuthDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.RoleDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UserDTO;
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

import java.util.ArrayList;
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

    public ResponseDTO updateUser(int id, UserDTO userDTO) {
        Optional<Users> user = dataService.findByUserId(id);
        user.get().setEmail(userDTO.getEmail());
        user.get().setGender(userDTO.getGender());
        user.get().setFullName(userDTO.getFullName());
        user.get().setPassword(userDTO.getPassword());
        user.get().setPhoneNumber(userDTO.getPhoneNumber());
        user.get().setDateOfBirth(userDTO.getDateOfBirth());
        user.get().setUsername(userDTO.getUsername());
        var userResDTO = modelMapper.map(user.get(), UserResDTO.class);
        return utilities.successResponse("Successfully updated user", userResDTO);


    }

    public ResponseDTO assignAuthority(AuthDTO authDTO) {
        Optional<Users> users = dataService.findByUserId(authDTO.getUserId());
        List<Roles> roles = dataService.findRoles();
        List<Roles>selectedRoles = new ArrayList<>();

        for(int oneCode: authDTO.getRoleIds()){
            for(Roles role: roles){
                if(role.getId() == oneCode){
                    selectedRoles.add(role);
                    break;
                }
            }
        }
        users.get().setRoles(selectedRoles);
        dataService.saveUser(users.get());
        return utilities.successResponse("Successfully assigned authority", users.get());
    }

    public ResponseDTO createRole(RoleDTO roleDTO) {
        Roles role = modelMapper.map(roleDTO, Roles.class);
        role.getAuthorities().addAll(roleDTO.getAuthorities());
        dataService.saveRole(role);
        return utilities.successResponse("Successfully created role", role);
    }
}
