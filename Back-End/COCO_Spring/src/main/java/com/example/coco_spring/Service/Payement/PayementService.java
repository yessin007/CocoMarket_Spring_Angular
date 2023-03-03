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


    @Value("k_test_51MfpbjFZqNx8XmmhC1sHRKoes1AgZ98bQTRXs27ztbjZ6X4mBaV5X2oAif5LaLmSkQSqZEi7bUyFe4ukscn5Jjy200DlYLAV3X")
    private String API_SECET_KEY;
    public void StripeService() {

    }

    public String createCharge(String email, String token, int amount) {

        String chargeId = null;

        try {
            Stripe.apiKey = API_SECET_KEY;

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("description","Charge for "+email);
            chargeParams.put("currency","usd");
            chargeParams.put("amount",amount);
            chargeParams.put("source",token);

            Charge charge = Charge.create(chargeParams);

            chargeId = charge.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chargeId;
    }
}
