package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Setter
@Getter
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigInteger amount;
    private Date paymentDate;
    private String transactionReference;
    private Date dateCreated;
    private String createdBy;
    private Date dateModified;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "claimId", nullable = false)
    private Claims claims;
}
