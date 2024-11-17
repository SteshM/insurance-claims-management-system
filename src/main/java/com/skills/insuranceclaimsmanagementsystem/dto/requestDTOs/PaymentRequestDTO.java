package com.skills.insuranceclaimsmanagementsystem.dto.requestDTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class PaymentRequestDTO {
    private BigDecimal amount;
    private Date paymentDate;
    private String transactionReference;
    private int settledBy;
}
