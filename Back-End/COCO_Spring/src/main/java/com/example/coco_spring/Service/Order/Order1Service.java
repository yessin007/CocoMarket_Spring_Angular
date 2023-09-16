package com.example.coco_spring.Service.Order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class Order1Service implements ICRUDService<Order1,Long> {

    Order1Repository order1Repository;

    CartRepsitory cartRepsitory;
    DiscountCodeRepository discountCodeRepository;

    @Override
    public List<Order1> findAll() {
        return order1Repository.findAll();
    }

    @Override
    public Order1 retrieveItem(Long idItem) {
        return order1Repository.findById(idItem).get();
    }

    @Override
    public Order1 add(Order1 order) {
        return order1Repository.save(order);
    }

    @Override
    public void delete(Long orderId) {
        order1Repository.deleteById(orderId);

    }


    @Override
    public Order1 update(Order1 order) {
        return order1Repository.save(order);
    }

}