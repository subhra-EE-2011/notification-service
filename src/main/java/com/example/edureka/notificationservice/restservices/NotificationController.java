package com.example.edureka.notificationservice.restservices;


import com.example.edureka.notificationservice.model.response.ListOfNotification;
import com.example.edureka.notificationservice.service.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/api/v1/getNotifications",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListOfNotification> getNotifications(@RequestParam(value = "customerId",required = true)Integer customerId){

        ListOfNotification listOfNotification = notificationService.getNotifications(customerId);

        if (null != listOfNotification &&
                      null != listOfNotification.notifications() &&
                              !listOfNotification.notifications().isEmpty()){
            return ResponseEntity.ok(listOfNotification);

        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
