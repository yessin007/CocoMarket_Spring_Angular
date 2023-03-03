package com.example.coco_spring.Service.Payement;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class PayementService implements ICRUDService<Payement,Long> , IPayementService {

    PayementRepository payementRepository;
    OrderRepository orderRepository;

    @Override
    public List<Payement> findAll() {
        return payementRepository.findAll();
    }

    @Override
    public Payement retrieveItem(Long idItem) {
        return payementRepository.findById(idItem).get();
    }

    @Override
    public Payement add(Payement payement) {
        return payementRepository.save(payement);
    }

    @Override
    public void delete(Long payementId) {

        payementRepository.deleteById(payementId);

    }

    @Override
    public Payement update(Payement payement) {
        return payementRepository.save(payement);
    }

    @Override
    public Order findByPaymentDate(Date date) {

        return payementRepository.findByPaymentDate(date);
    }

    @Override
    public void assignOrderToPayment(Long orderId, Long paymentId) {
        Order order = orderRepository.findById(orderId).get();
        Payement payement = payementRepository.findById(paymentId).get();
        order.setPayement(payement);
        orderRepository.save(order);


    }


}
