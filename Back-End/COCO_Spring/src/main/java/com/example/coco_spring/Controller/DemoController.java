package com.example.coco_spring.Controller;

import com.example.coco_spring.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendMail() throws MessagingException {
        String to = "arfaoui.maram@esprit.tn";
        String subject = "Hello from Spring Boot";
        String message = "This is a test email sent from a Spring Boot application using Thymeleaf templates.";
        emailService.sendEmail(to, subject, message);
        return ResponseEntity.ok("Mail sending");
    }



}

