package com.example.edureka.notificationservice.service;


import com.example.edureka.notificationservice.dto.NotificationDTO;
import com.example.edureka.notificationservice.jpa.entity.Notification;
import com.example.edureka.notificationservice.model.response.ListOfNotification;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;
import java.io.StringWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final Configuration freemarkerConfig;
    private final NotificationRepository notificationRepository;
    private final Environment env;

    public NotificationService(Configuration freemarkerConfig, NotificationRepository notificationRepository, Environment env) {
        this.freemarkerConfig = freemarkerConfig;
        this.notificationRepository = notificationRepository;
        this.env = env;
    }

    public void processNotification(NotificationDTO notificationDTO){


        try {

            Template template = freemarkerConfig.getTemplate(env.getProperty("application.template."+notificationDTO.getKciKey()+".file"));
            StringWriter emailBodyWriter = new StringWriter();
            template.process(notificationDTO.getEmailCharacteristics(), emailBodyWriter);


            Notification notificationEntity = new Notification();
            notificationEntity.setCustomerid(notificationDTO.getCustomerId());
            notificationEntity.setEmailid(notificationDTO.getEmailId());
            notificationEntity.setKcikey(notificationDTO.getKciKey());
            notificationEntity.setEmailsubject(env.getProperty("application.template."+notificationDTO.getKciKey()+".subject"));
            notificationEntity.setEmailbody(emailBodyWriter.toString());
            notificationEntity.setSendingtime(Instant.now());

            notificationRepository.save(notificationEntity);

        } catch (Exception e) {
            logger.error("Exception in processing notification",e);
        }
    }


    public ListOfNotification getNotifications(Integer customerId){

        List<Notification> notificationEntities = notificationRepository.getNotificationsByCustomerid(customerId);
        List<com.example.edureka.notificationservice.model.response.Notification> notifications = new ArrayList<>();

        for (Notification notification: notificationEntities){
            notifications.add(new com.example.edureka.notificationservice.model.response.Notification(notification.getCustomerid(),
                                                                                                      notification.getEmailid(),
                                                                                                      notification.getEmailsubject(),
                                                                                                      notification.getEmailbody(),
                                                                                                      notification.getSendingtime()));
        }


        return new ListOfNotification(notifications);
    }
}
