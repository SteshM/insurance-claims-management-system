package com.skills.insuranceclaimsmanagementsystem.controller;

import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.PaymentRequestDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs.UpdatePaymentStatusDTO;
import com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs.ResponseDTO;
import com.skills.insuranceclaimsmanagementsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentsController {
    private final PaymentService paymentService;

    @GetMapping("/payment-status")
    public ResponseDTO getPaymentStatus() {
        return paymentService.getPaymentStatus();
    }

    @GetMapping("/payments")
    public ResponseDTO getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("/payments/claim/{id}")
    public ResponseDTO claimPayments(@PathVariable int id) {
        return paymentService.getClaimPayments(id);
    }



}
