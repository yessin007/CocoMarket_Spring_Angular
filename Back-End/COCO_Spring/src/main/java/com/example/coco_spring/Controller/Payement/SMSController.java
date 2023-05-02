package com.example.coco_spring.Controller.Payement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Payement.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payement/sms/")
@CrossOrigin("*")
public class SMSController {

    @Autowired
    SmsService smsService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private final String  TOPIC_DESTINATION = "/lesson/sms";


    @PostMapping("/SubmitSms")
    public void smsSubmit(@RequestBody SmsPojo sms) {
        try{
            smsService.send(sms);
        }
        catch(Exception e){

            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
            throw e;
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getPhonenumber());

    }

    @PostMapping("/smscallback")
    public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
        smsService.receive(map);
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Twilio has made a callback request! Here are the contents: "+map.toString());
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}
