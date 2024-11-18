package com.skills.insuranceclaimsmanagementsystem.service;
import com.skills.insuranceclaimsmanagementsystem.configurations.SystemConfigs;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.PaymentResDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.PaymentTypeResDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.models.Claims;
import com.skills.insuranceclaimsmanagementsystem.models.PaymentStatus;
import com.skills.insuranceclaimsmanagementsystem.models.Payments;
import com.skills.insuranceclaimsmanagementsystem.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    ModelMapper modelMapper = new ModelMapper();
    private final DataService dataService;
    private final Utilities utilities;
    private final SystemConfigs systemConfigs;

    public ResponseDTO getPaymentStatus() {
        List<PaymentStatus>paymentStatuses = dataService.fetchStatuses();
        List<PaymentTypeResDTO>typeResDTOS = paymentStatuses.stream()
                .map(
                       paymentStatus -> modelMapper.map(paymentStatus, PaymentTypeResDTO.class)
                ).toList();
        return utilities.successResponse(systemConfigs.getSuccessMessage(), typeResDTOS);

    }


    public ResponseDTO getPayments() {
        List<Payments>payments = dataService.fetchPayments();
        List<PaymentResDTO>paymentResDTOS = payments.stream().map(
                payments1 -> modelMapper.map(payments1, PaymentResDTO.class)
        ).toList();
    return utilities.successResponse(systemConfigs.getSuccessMessage(), paymentResDTOS);
}

    public ResponseDTO getClaimPayments(int id) {
        Optional<Claims> claimOptional = dataService.findByClaimId(id);

        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(1,"Claim not found for the provided ID", null);
        }
        List<Payments>payments = dataService.fetchPayments();
        List<PaymentResDTO>paymentResDTOList = payments.stream().map(
                payments1 -> modelMapper.map(payments1, PaymentResDTO.class)
        ).toList();
        return utilities.successResponse(systemConfigs.getSuccessMessage(), paymentResDTOList);

    }
}
