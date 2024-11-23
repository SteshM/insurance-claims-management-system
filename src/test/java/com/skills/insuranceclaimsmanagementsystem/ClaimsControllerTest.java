package com.skills.insuranceclaimsmanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.insuranceclaimsmanagementsystem.configurations.SystemConfigs;
import com.skills.insuranceclaimsmanagementsystem.controller.ClaimsController;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.ClaimRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.repository.ClaimStatusRepo;
import com.skills.insuranceclaimsmanagementsystem.service.ClaimsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClaimsController.class)
@EnableConfigurationProperties(SystemConfigs.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClaimsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClaimsService claimsService;

    @Test
    @DisplayName("Test Get Claim Types")
    void testGetClaimTypes() throws Exception {
        mockMvc.perform(get("api/customer/claim-types"))
            .andExpect(status().isOk());
        verify(claimsService,times(1))
                .getClaimTypes();
    }

    @Test
    @DisplayName("Test Get Claim Statuses")
    void testGetClaimStatuses() throws Exception {
        mockMvc.perform(get("/api/customer/claim-statuses"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$[0]").value("Pending"))
                .andExpect((ResultMatcher) jsonPath("$[1]").value("Approved"))
                .andExpect((ResultMatcher) jsonPath("$[2]").value("Rejected"));
        // Verify the service method was called once
        verify(claimsService, times(1)).getClaimStatus();
    }

    @Test
    @DisplayName("Test Submit Claim")
    void testSubmitClaim() throws Exception {
        // Act and Assert
        mockMvc.perform(get("/api/customer/claim"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.message").value("Claim submitted successfully"))
                .andExpect((ResultMatcher) jsonPath("$.status").value("SUCCESS"));
        // Verify the service was called
        verify(claimsService, times(1)).submitClaim(any(ClaimRequestDTO.class));
    }


}
