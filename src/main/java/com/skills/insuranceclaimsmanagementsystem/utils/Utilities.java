package com.skills.insuranceclaimsmanagementsystem.utils;

import com.skills.insuranceclaimsmanagementsystem.dto.ResponseDTOs.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Utilities {

    public ResponseDTO successResponse(String statusMessage, Object result) {
        var response = new ResponseDTO();
        response.setStatusCode("00");
        response.setStatusMessage(statusMessage);
        response.setResult(result);
        return response;
    }

    public ResponseDTO failedResponse(int statusCode, String statusMessage, Object result) {
        var response = new ResponseDTO();
        response.setStatusCode("01");
        response.setStatusMessage(statusMessage);
        response.setResult(result);

        return response;
    }
}