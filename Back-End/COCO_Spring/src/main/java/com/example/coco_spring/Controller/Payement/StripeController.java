package com.example.coco_spring.Controller.Payement;



import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Payement.StripePayment;

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
import java.util.HashMap;
import java.util.Map;


@Component
@RestController
@RequestMapping("/api/payement/stripe")
@AllArgsConstructor
public class StripeController {

    StripePayment stripePayment;

    public StripeController() {
        this.stripePayment = new StripePayment("");
    }
/*
    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody Payment paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = stripePayment.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
*/

    @PostMapping("/paymentStripee")
    public Charge payyment(@RequestBody String token, @RequestBody BigDecimal amount) throws StripeException{
        return stripePayment.chargeCreditCard(token, amount) ;
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
