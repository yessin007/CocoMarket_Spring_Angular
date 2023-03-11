package com.example.coco_spring.Service.Order;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;



@Service
@Slf4j
@AllArgsConstructor

public class DiscountCodeService {

    private  DiscountCodeRepository discountCodeRepository;



    @Scheduled(fixedRate = 10000000) // generate discount code every 10 seconds
    public String generateDiscount() {
        Random random = new Random();
        int discountId = random.nextInt(974557534); // generate a random discountId just to fill the Setter for discountCode Entity
        int discount = random.nextInt(40); // generate a random discount between 0 and 40 percent
        String code = generateDiscountCode(discount); // generate a discount code with the discount value
        DiscountCode discountCode = new DiscountCode((long)discountId,code,discount,false); // create a new discount code object
        discountCodeRepository.save(discountCode); // save the discount code to the database
        System.out.println("Generated discount code: " + code);
        return code;
    }

    private String generateDiscountCode(int discount) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) { // generate a 6-digit discount code
            int digit = random.nextInt(10); // generate a random digit between 0 and 9
            code += digit;
        }
        code += "-" + discount + "%"; // add the discount value to the end of the code
        return code;
    }



}
