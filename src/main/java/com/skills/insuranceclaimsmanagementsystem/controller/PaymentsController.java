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

    @PostMapping("/payment/{id}")
    public ResponseDTO recordPayment(@PathVariable int id, @RequestBody PaymentRequestDTO paymentRequestDTO){
        return paymentService.recordPayment(id,paymentRequestDTO);
    }

    @PutMapping("/payment/{id}/status")
    public ResponseDTO updatePaymentStatus(@PathVariable int id, @RequestBody UpdatePaymentStatusDTO updatePaymentStatusDTO){
        return paymentService.updatePaymentStatus(id,updatePaymentStatusDTO);

    }



}
