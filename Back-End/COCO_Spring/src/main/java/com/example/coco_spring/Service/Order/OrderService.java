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
    DiscountCodeRepository discountCodeRepository;
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
            map.put(obj,orderRepository.findByDelivery_Provider_FirstName(obj));

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


    public Order OrderAfterDiscount(Long orderId, Long CodePromo){

        Order order = orderRepository.findById(orderId).get();

        DiscountCode discountCode = discountCodeRepository.findById(CodePromo).get();
        System.out.println(discountCode);
        if(discountCode != null){
            if(discountCode.getUsed()==false){
                float discount = discountCode.getDiscount();
                float discountedBill = order.getAmountBill()*(1- discount/100) ;
                order.setAmountBill(discountedBill);
                orderRepository.save(order);
                discountCode.setUsed(true);
                discountCodeRepository.save(discountCode);
            }else {
                System.out.println("CodePromo "+discountCode.getCode()+" deja utilisé ! ");
            }
        }else {
            System.out.println("CodePromo n'existe pas");
        }
        return order;
    }



}
