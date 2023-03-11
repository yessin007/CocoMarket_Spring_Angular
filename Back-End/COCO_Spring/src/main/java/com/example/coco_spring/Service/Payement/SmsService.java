package com.example.coco_spring.Service.Payement;

import com.example.coco_spring.Entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
@Service
public class SmsService {


    private final String ACCOUNT_SID ="ACfde9a7ca466cdec462f030c2c7054abb";

    private final String AUTH_TOKEN = "5ea614a24e2c930f1ded475445fe35b0";

    private final String FROM_NUMBER = "+15673646255";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getPhonenumber()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}