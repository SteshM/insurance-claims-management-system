package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.LoginDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UserDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.UserResDTO;
import com.skills.insuranceclaimsmanagementsystem.models.Roles;
import com.skills.insuranceclaimsmanagementsystem.models.Users;
import com.skills.insuranceclaimsmanagementsystem.security.CustomUserDetails;
import com.skills.insuranceclaimsmanagementsystem.utils.JwtUtil;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements UserDetailsService {
    private final DataService dataService;
    private final Utilities utilities;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = dataService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public ResponseDTO createAccount(UserDTO userDTO) {
        Users user = new Users();
        modelMapper.map(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Optional<Roles> role = dataService.findById(userDTO.getRoleId());
        List<Roles>roles = Arrays.asList(role.get());
        user.setRoles(roles);
        var savedUser = dataService.saveUser(user);
        var userResDTO = modelMapper.map(savedUser, UserResDTO.class);
        return utilities.successResponse("account created successfully",savedUser);
    }


    public ResponseDTO login(@Valid LoginDTO loginDTO) {
        Users user = dataService.findByUsername(loginDTO.getUsername());
        if (user == null) {
            return utilities.failedResponse(01, "Account doesn't exist, proceed to register", null);
        }
        log.info("Username is {}", loginDTO.getUsername());

        // Verify password
        log.info("user {}", user.getUsername());
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            // Create authorities
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) user.getAuthorities();

            // Create User object for token generation
            CustomUserDetails customUserDetails = new CustomUserDetails(user.getUsername(), user.getPassword(), authorities);

            // Generate token
            String token = jwtUtil.generateToken(customUserDetails);

            // Prepare response
            Map<String, String> res = new HashMap<>();
            res.put("token", token);
            res.put("username", loginDTO.getUsername());

            return utilities.successResponse("Successfully logged in", res);
        } else {
            return utilities.failedResponse(1, "Password Mismatch", null);
        }
    }

}
