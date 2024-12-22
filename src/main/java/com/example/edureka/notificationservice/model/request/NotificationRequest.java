package com.example.edureka.notificationservice.model.request;

import java.util.List;

public record NotificationRequest(Integer customerId,
                                  String emailId,
                                  String kciKey,
                                  List<NVPair> characteristics) {
}
