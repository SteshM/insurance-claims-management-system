package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AuthDTO {
    private int userId;
    private List<Integer >roleIds;
}
