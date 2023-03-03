package com.example.coco_spring.Controller.Payement;

import com.example.coco_spring.Service.Payement.PayementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/api/payementStripe/")
public class PayementStripeController {

    @Value("pk_test_51MfpbjFZqNx8XmmhnPUi0vwPQMW2FufR26GbvrMstAEtgxYgLXPbx0Gwnq1Sp54Oun1X5vRyxAYXlUQJL3r3VKxJ00P3dOdiFL")
    private String API_PUBLIC_KEY;

    private PayementService payementService;

    @GetMapping("/home")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/subscription")
    public String subscriptionPage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "paym";
    }

    @GetMapping("/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }


    @PostMapping("/create-charge")
    public @ResponseBody String createCharge(String email, String token) {

        if (token == null) {
            return "false, Stripe payment token is missing. please try again later.";
        }

        String chargeId = payementService.createCharge(email, token, 850);// 9.99 usd

        if (chargeId == null) {
            return "false, An error accurred while trying to charge.";
        }

        // You may want to store charge id along with order information

        return "true, Success your charge id is " + chargeId;
    }

}
