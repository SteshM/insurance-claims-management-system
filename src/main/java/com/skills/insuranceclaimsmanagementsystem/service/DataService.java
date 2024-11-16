package com.skills.insuranceclaimsmanagementsystem.service;

import com.skills.insuranceclaimsmanagementsystem.models.*;
import com.skills.insuranceclaimsmanagementsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataService {
    private final ClaimTypeRepo claimTypeRepo;
    private final ClaimsRepo claimsRepo;
    private final ClaimStatusRepo claimStatusRepo;
    private final RolesRepo rolesRepo;
    private final UserRepo userRepo;
    private final AttachmentRepo attachmentRepo;
    private final PaymentStatusRepo paymentStatusRepo;
    private final PaymentsRepo paymentsRepo;

    public ClaimType findByName(String name) {
        return claimTypeRepo.findByName(name);
    }

    public void saveClaimType(ClaimType claimType) {
      claimTypeRepo.save(claimType);
    }

    public Claims saveClaim(Claims claim) {
       return claimsRepo.save(claim);
    }

    public ClaimStatus findClaimStatusByName(String pending) {
       return claimStatusRepo.findByName(pending);
    }

    public List<ClaimType> findAll() {
        return claimTypeRepo.findAll();
    }

    public List<Roles> findRoles() {
        return rolesRepo.findAll();

    }

    public List<ClaimStatus> fetchAll() {
        return claimStatusRepo.findAll();
    }

    public List<Users> fetchUsers() {
        return userRepo.findAll();
    }

    public Optional<Claims> findByClaimId(int claimId) {
        return claimsRepo.findById(claimId);
    }
    public Claims findByClaimId2(int claimId) {
        return claimsRepo.findById(claimId).orElseThrow();
    }

    public Attachments saveAttachment(Attachments attachment) {
        return attachmentRepo.save(attachment);
    }

    public List<Claims> fetchClaims() {
        return claimsRepo.findAll();
    }

    public Optional<Users> findByUserId(int userId) {
        return userRepo.findById(userId);
    }

    public List<PaymentStatus> fetchStatuses() {
        return paymentStatusRepo.findAll();
    }

    public PaymentStatus findByStatusName(String pending) {
        return paymentStatusRepo.findByStatusName(pending);
    }

    public Payments savePayment(Payments payments) {
        return paymentsRepo.save(payments);
    }

    public Optional<Payments> findByPaymentId(int id) {
        return paymentsRepo.findById(id);
    }
}
