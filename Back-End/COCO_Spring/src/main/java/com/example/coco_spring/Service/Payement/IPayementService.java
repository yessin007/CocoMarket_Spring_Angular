package com.example.coco_spring.Service.Payement;

import com.example.coco_spring.Entity.Order;
import com.example.coco_spring.Entity.Payement;

import java.util.Date;
import java.util.List;

public interface IPayementService {

    List<Order> findByPaymentDate(Date date);

    public Order assignOrderToPayment(Long orderId, Long paymentId);

    //String createCharge(String email, String token, int amount);
   //void StripeService();
}
