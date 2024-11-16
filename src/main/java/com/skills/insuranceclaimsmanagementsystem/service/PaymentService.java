package com.skills.insuranceclaimsmanagementsystem.service;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.PaymentRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UpdatePaymentStatusDTO;
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
        Payments payments = new Payments();
        payments.setPaymentDate(paymentRequestDTO.getPaymentDate());
        payments.setTransactionReference(paymentRequestDTO.getTransactionReference());
        Optional<Claims> claimOptional = dataService.findByClaimId(id);

        if (claimOptional.isEmpty()) {
            return utilities.failedResponse(1,"Claim not found for the provided ID", null);
        }

        Claims claim = claimOptional.get();
        payments.setAmount(claim.getAmountClaimed());
        payments.setClaim(claim);
        PaymentStatus paymentStatus = dataService.findByStatusName("pending");
        payments.setStatus(paymentStatus);
        Payments savedPayment = dataService.savePayment(payments);
        var paymentResDTO = modelMapper.map(savedPayment, PaymentResDTO.class);
        return utilities.successResponse("Payment recorded successfully", paymentResDTO);
    }

    public ResponseDTO updatePaymentStatus(int id, UpdatePaymentStatusDTO updatePaymentStatusDTO) {
        Optional<Payments> payments = dataService.findByPaymentId(id);
        if (payments.isEmpty()) {
            return utilities.failedResponse(1,"Payment not found for the provided ID", null);
        }
        PaymentStatus paymentStatus = dataService.findByStatusName(updatePaymentStatusDTO.getStatusName());
        if (paymentStatus.equals(payments.get().getStatus())) {
            return utilities.failedResponse(1,"Payment already recorded for the provided ID", null);
        }
        payments.get().setStatus(paymentStatus);
         var updatedPayment = dataService.savePayment(payments.get());
         var paymentResDTO = modelMapper.map(updatedPayment, PaymentResDTO.class);
         return utilities.successResponse("Payment recorded successfully", paymentResDTO);


    }

    public ResponseDTO getPayments() {
        List<Payments>payments = dataService.fetchPayments();
        List<PaymentResDTO>paymentResDTOS = payments.stream().map(
                payments1 -> modelMapper.map(payments1, PaymentResDTO.class)
        ).toList();
    return utilities.successResponse("Success", paymentResDTOS);
}
}
