package com.example.coco_spring.Service.Delivery;

import com.example.coco_spring.Entity.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Component
@Service
public class DeliverySmsService {


    private final String ACCOUNT_SID ="AC4a0fd610f0bd807de8b72e4bd5bfb353";

    private final String AUTH_TOKEN = "62915380e419199a66db8cedc03f4665";

    private final String FROM_NUMBER = "+15674831222";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getPhonenumber()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}