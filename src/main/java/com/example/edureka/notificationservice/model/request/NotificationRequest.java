package com.example.edureka.notificationservice.model.request;

import java.util.List;

public record NotificationRequest(Integer customerId,
                                  String emailId,
                                  String emailTemplate,
                                  List<NVPair> characteristics) {
}
