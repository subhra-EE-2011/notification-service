package com.example.edureka.notificationservice.service;


import com.example.edureka.notificationservice.dto.NotificationDTO;
import com.example.edureka.notificationservice.model.request.NVPair;
import com.example.edureka.notificationservice.model.request.NotificationRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumerService {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final NotificationService notificationService;

    public KafkaConsumerService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics={"notification_topic"},groupId="notification-processing-group",concurrency = "2")
    public void receiveNotificationRequest(@Payload NotificationRequest notificationRequest){

        logger.info("Notification request consumed "+notificationRequest);

        Map<String,String> characteristics = new HashMap<>();

        for (NVPair nv: notificationRequest.characteristics()){
            characteristics.put(nv.name(),nv.value());
        }

        NotificationDTO notificationDTO = NotificationDTO.builder().customerId(notificationRequest.customerId())
                                         .emailId(notificationRequest.emailId())
                                            .emailTemplate(notificationRequest.emailTemplate())
                                                            .emailCharacteristics(characteristics)
                                                                        .build();

        notificationService.processNotification(notificationDTO);
    }
}
