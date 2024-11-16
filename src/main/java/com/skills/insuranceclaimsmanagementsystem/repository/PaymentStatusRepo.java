package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepo extends JpaRepository<PaymentStatus, Integer> {
    PaymentStatus findByStatusName(String pending);
}
