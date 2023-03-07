package com.example.coco_spring.Service.Order;

import com.example.coco_spring.Entity.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IOrderService {
   Map<String,List<Order>> displayOrdersByProvider();
}
