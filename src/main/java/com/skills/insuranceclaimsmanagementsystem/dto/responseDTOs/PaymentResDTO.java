package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PaymentResDTO {
    private int id;
    private Date paymentDate;
    private String transactionReference;
    private PaymentTypeResDTO paymentStatus;
}
