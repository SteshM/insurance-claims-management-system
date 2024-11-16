package com.skills.insuranceclaimsmanagementsystem.dto.responseDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttachmentResDTO {
    private int id;
    private String type;
    private String url;
    private String description;
}
