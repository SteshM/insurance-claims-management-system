package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO {
        private String statusCode;
        private String statusMessage;
        private Object result;

}
