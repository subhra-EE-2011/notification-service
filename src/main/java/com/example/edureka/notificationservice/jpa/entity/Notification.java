package com.example.edureka.notificationservice.jpa.entity;


import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(schema = "reservation")
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emailid", nullable = false, length = 50)
    private String emailid;

    @Column(name = "emailsubject", nullable = false, length = 100)
    private String emailsubject;

    @Column(name = "emailbody", nullable = false, length = 2000)
    private String emailbody;

    @Column(name = "sendingtime", nullable = false)
    private Instant sendingtime;

    @Column(name = "customerid", nullable = false)
    private Integer customerid;

    @Column(name = "kcikey", nullable = false, length = 50)
    private String kcikey;

    public String getKcikey() {
        return kcikey;
    }

    public void setKcikey(String kcikey) {
        this.kcikey = kcikey;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Instant getSendingtime() {
        return sendingtime;
    }

    public void setSendingtime(Instant sendingtime) {
        this.sendingtime = sendingtime;
    }

    public String getEmailbody() {
        return emailbody;
    }

    public void setEmailbody(String emailbody) {
        this.emailbody = emailbody;
    }

    public String getEmailsubject() {
        return emailsubject;
    }

    public void setEmailsubject(String emailsubject) {
        this.emailsubject = emailsubject;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
