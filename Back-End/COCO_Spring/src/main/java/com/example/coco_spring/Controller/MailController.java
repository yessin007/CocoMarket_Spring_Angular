package com.example.coco_spring.Controller;

import com.example.coco_spring.Service.EmailService;
import com.example.coco_spring.Service.SchedulerMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private EmailService emailService;
    private SchedulerMailService schedulerMailService;

    /*@PostMapping
    public ResponseEntity<String> sendMail() throws MessagingException {
        String to = "arfaoui.maram@esprit.tn";
        String subject = "Hello from Spring Boot";
        String message = "This is a test email sent from a Spring Boot application using Thymeleaf templates.";
        emailService.sendEmail(to, subject, message);
        return ResponseEntity.ok("Mail sending");
    }*/

    @PostMapping("/{mail}")
    public ResponseEntity<String> sendMailToOne(@PathVariable("mail") String m) throws MessagingException {
        String to = m;
        String subject = "Hello from Spring Boot";
        String message = "This is a test email sent from a Spring Boot application using Thymeleaf templates.";
        emailService.sendEmail(to, subject, message);
        return ResponseEntity.ok("Mail sending to "+m);
    }

    @PostMapping("/sub/{mail}/{sub}")
    public ResponseEntity<String> sendMailToOneWithSub(@PathVariable("mail") String m,@PathVariable("sub") String s) throws MessagingException {
        String to = m;
        String subject = s;
        String message = "This is a test email sent from a Spring Boot application using Thymeleaf templates.";
        emailService.sendEmail(to, subject, message);
        return ResponseEntity.ok("Mail sending to "+m);
    }

    /*@PostMapping("/wel/{mail}/{id}")
    public String sendWel(@PathVariable("mail") String m,@PathVariable("id") Long id) throws MessagingException {
        //User u = userService.getUserById(id).get();
        String mes = "test";
        emailService.sendWelcomeEmail(m,"3aslema",mes);
        return "c bon";
    }*/

    @PostMapping("/off")
    public String sendOff() throws MessagingException {
        schedulerMailService.runScheduledDailyOffre();
        return "test Daily Offers Mail done";
    }
}
