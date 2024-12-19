package com.example.edureka.notificationservice.model.response;

import java.time.Instant;

public record Notification(Integer customerId,
                           String emailId,
                           String emailSubject,
                           String emailContent,
                           Instant sendingTime) {
}
