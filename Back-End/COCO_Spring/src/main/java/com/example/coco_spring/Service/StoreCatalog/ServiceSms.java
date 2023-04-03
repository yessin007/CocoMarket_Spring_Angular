package com.example.coco_spring.Service.StoreCatalog;

import com.example.coco_spring.Entity.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


@Service
@Component
public class ServiceSms {

    private final String ACCOUNT_SID ="AC30c244531637ebcb62c3d85db3c95be9";

    private final String AUTH_TOKEN = "7ba3effec3d303cf3477833f308c3c57";

    private final String FROM_NUMBER = "+15674831313";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getPhonenumber()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}
