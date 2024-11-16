package com.skills.insuranceclaimsmanagementsystem.repository;

import com.skills.insuranceclaimsmanagementsystem.models.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepo  extends JpaRepository<Attachments,Integer> {
}
