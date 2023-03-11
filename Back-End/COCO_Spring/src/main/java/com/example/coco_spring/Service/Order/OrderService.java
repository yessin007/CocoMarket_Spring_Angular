package com.example.coco_spring.Service.Order;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
@AllArgsConstructor
public class OrderService implements ICRUDService<Order,Long> , IOrderService {

    OrderRepository orderRepository;

    CartRepsitory cartRepsitory;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order retrieveItem(Long idItem) {
        return orderRepository.findById(idItem).get();
    }

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);

    }



    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }



    public Map<String,List<Order>> displayOrdersByProvider() {
        List<String> findProviderNamesWithOrder = orderRepository.findProviderNamesWithOrder();
        Map<String, List<Order>> map = new HashMap<>();
        for (String obj : findProviderNamesWithOrder) {
            map.put(obj,orderRepository.findByDelivery_Provider_ProviderName(obj));

        }
        return map;
    }

    @Override
    public Order AssignCartToOrder(Long orderId, Long cartId) {
        Cart cart = cartRepsitory.findById(cartId).orElse(null);
        Order order = orderRepository.findById(orderId).orElse(null);

        order.setCart(cart);

        return orderRepository.save(order);
    }
/*
    @Scheduled(fixedRate = 30000) // generate discount every 10 seconds
    public int generateDiscount() {
        Random random = new Random();

        int discount = random.nextInt(20); // generate a random discount between 0 and 50 percent
        System.out.println("Generated discount: " + discount + "%");
        return discount;
    }

 */


    @Scheduled(fixedRate = 10000) // generate discount code every 10 seconds
    public void generateDiscount() {
        Random random = new Random();
        int discount = random.nextInt(50); // generate a random discount between 0 and 50 percent
        String code = generateDiscountCode(discount); // generate a discount code with the discount value
        System.out.println("Generated discount code: " + code);
        System.out.println("Generated discount: " + discount + "%");
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
