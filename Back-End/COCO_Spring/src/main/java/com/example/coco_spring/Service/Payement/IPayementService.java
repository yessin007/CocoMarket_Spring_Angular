package com.example.coco_spring.Service.Payement;



import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;

import java.util.Date;
import java.util.List;

public interface IPayementService {

    List<Order> findByPaymentDate(Date date);

    public Order assignOrderToPayment(Long orderId, Long paymentId);

    //String createCharge(String email, String token, int amount);
   //void StripeService();
}
