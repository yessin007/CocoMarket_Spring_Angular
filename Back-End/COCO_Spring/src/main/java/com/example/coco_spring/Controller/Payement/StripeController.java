package com.example.coco_spring.Controller.Payement;



import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Payement.StripePayment;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import org.springframework.http.HttpStatus;

import java.beans.JavaBean;
import java.lang.String;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/api/payement/stripe")

public class StripeController {

   // StripePayment stripePayment;
/*
    public StripeController() {
        this.stripePayment = new StripePayment();
    }
*/
   @Autowired
   private Gson gson;

    public void myMethod() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.example.com", 8080));
        String json = gson.toJson(proxy);
        Proxy deserializedProxy = gson.fromJson(json, Proxy.class);
    }
      StripePayment stripePayment;

    public StripeController() {
        this.stripePayment = new StripePayment();
    }


    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody Payment paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = stripePayment.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }


    @PostMapping("/paymentStripee")
    public ResponseEntity<String> payyment(@RequestParam("token") String token, @RequestParam("amount") int amount) throws StripeException{
       String chargee = stripePayment.chargeCreditCard(token, amount).toString();

        return  ResponseEntity.ok().body(chargee);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createPaymentYessin(@RequestParam("amount") int amount, @RequestParam("currency") String currency) {
        Stripe.apiKey = "sk_test_51MfpbjFZqNx8XmmhC1sHRKoes1AgZ98bQTRXs27ztbjZ6X4mBaV5X2oAif5LaLmSkQSqZEi7bUyFe4ukscn5Jjy200DlYLAV3X";

        try {
            Charge charge = Charge.create(Map.of(
                    "amount", amount,
                    "currency", currency,
                    "source", "tok_visa"
            ));


            return ResponseEntity.ok(charge.toString());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



/*
    @Autowired
    private Stripe stripe;

    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestParam String token,
                                         @RequestParam int amount, @RequestParam String currency) {
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", currency);
            chargeParams.put("source", token);
            Charge charge = Charge.create(chargeParams);
            return ResponseEntity.ok(charge.toJson());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

 */


}
