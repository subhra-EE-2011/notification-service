package com.example.edureka.notificationservice.dto;

import lombok.*;
import java.util.Map;


@Builder
@Setter
@Getter
@ToString
public class NotificationDTO {

    private Integer customerId;
    private String emailId;
    private String emailTemplate;
    private Map<String,String> emailCharacteristics;
}
