package com.skills.insuranceclaimsmanagementsystem.service;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.PaymentRequestDTO;
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

    public ResponseDTO getPaymentStatus() {
        List<PaymentStatus>paymentStatuses = dataService.fetchStatuses();
        List<PaymentTypeResDTO>typeResDTOS = paymentStatuses.stream()
                .map(
                       paymentStatus -> modelMapper.map(paymentStatus, PaymentTypeResDTO.class)
                ).toList();
        return utilities.successResponse("Success", typeResDTOS);

    }

    public ResponseDTO recordPayment(int id, PaymentRequestDTO paymentRequestDTO) {
        // Create a new Payments entity and set properties from the request DTO
        Payments payments = new Payments();
        payments.setPaymentDate(paymentRequestDTO.getPaymentDate());
        payments.setTransactionReference(paymentRequestDTO.getTransactionReference());
        Optional<Claims> claimOptional = dataService.findByClaimId(id);

        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(1,"Claim not found for the provided ID", null);
        }

        // Get the claim from Optional
        Claims claim = claimOptional.get();

        // Set the amount of the payment to the claimed amount
        payments.setAmount(claim.getAmountClaimed());

        // Set the claim associated with this payment
        payments.setClaim(claim); // Associate the payment with the claim

        // Retrieve the payment status by name and set it to "pending"
        PaymentStatus paymentStatus = dataService.findByStatusName("pending");
        payments.setStatus(paymentStatus);

        // Save the payment and map it to the response DTO
        Payments savedPayment = dataService.savePayment(payments);
        var paymentResDTO = modelMapper.map(savedPayment, PaymentResDTO.class);

        // Return success response
        return utilities.successResponse("Payment recorded successfully", paymentResDTO);
    }

}
