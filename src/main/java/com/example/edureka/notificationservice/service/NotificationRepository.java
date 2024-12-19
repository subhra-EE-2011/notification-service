package com.example.edureka.notificationservice.service;

import com.example.edureka.notificationservice.jpa.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> getNotificationsByCustomerid(Integer customerid);
}
