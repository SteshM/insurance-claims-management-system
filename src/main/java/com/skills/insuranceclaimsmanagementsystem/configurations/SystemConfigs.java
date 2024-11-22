package com.skills.insuranceclaimsmanagementsystem.configurations;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties("insurance-claim.config")
public class SystemConfigs {
    private int successStatusCode;
    private int failedStatusCode;
    private String successMessage;

    private String pendingStatus;
    private String submittedStatus;
    private String approvedStatus ;
    private String settledStatus;
    private String inProgressStatus;
    private String completedStatus;
    private String disbursedStatus;

}
