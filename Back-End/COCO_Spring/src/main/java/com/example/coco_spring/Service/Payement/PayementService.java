package com.example.coco_spring.Service.Payement;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PayementService implements ICRUDService<Payement,Long> {

    PayementRepository payementRepository;
    OrderRepository orderRepository;
    UserRepository userRepository;

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


    public List<Order> findByPaymentDate(Date date) {

        return payementRepository.findByPaymentDate(date);
    }


    public Order assignOrderToPayment(Long orderId, Long paymentId) {
        Order order = orderRepository.findById(orderId).get();
        Payement payement = payementRepository.findById(paymentId).get();
        order.setPayement(payement);
        return orderRepository.save(order);


    }
}
