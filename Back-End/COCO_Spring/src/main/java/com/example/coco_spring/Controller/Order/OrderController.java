package com.example.coco_spring.Controller.Order;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.Order.DiscountCodeService;
import com.example.coco_spring.Service.Order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order/")

@CrossOrigin("*")
public class OrderController {

    DiscountCodeService discountCodeService;
    OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/retrive_all_orders")
    public List<Order> retrieveOrderList(){
        return orderService.findAll();
    }

    @GetMapping("/retrive_order/{orderId}")
    public Order retrieveOrder(@PathVariable("orderId") Long orderId){
        return orderService.retrieveItem(orderId);
    }

    @PostMapping("/add_order")
    public Order addOrder(@RequestBody Order order){
        return orderService.add(order);
    }

    @PutMapping("/update_order")
    public Order updateOrder(@RequestBody Order order){
        return orderService.update(order);
    }

    @DeleteMapping("/delete_order/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        orderService.delete(orderId);
    }


    @GetMapping("/orderslistbyprovider")
    public Map<String,List<Order>> displayOrdersByProvider() {
        return orderService.displayOrdersByProvider();
    }


    @PostMapping("/AssignCartToOrder/{orderId}/{cartId}")
    public Order AssignCartToOrder(@PathVariable("orderId") Long orderId,@PathVariable("cartId") Long cartId){
        return orderService.AssignCartToOrder(orderId, cartId);
    }

    @PostMapping("/BillAfterDiscount/{orderId}/{discountId}")
    public Order BillAfterDiscount(@PathVariable("orderId") Long orderId,@PathVariable("discountId") Long discountId){
       return orderService.OrderAfterDiscount(orderId, discountId);
    }

    @GetMapping("/GenerateCodeAndDiscount")
    public String generateDiscount() {
        return "Votre Code Promo est :" + discountCodeService.generateDiscount()+"  Amusez vous !";
    }


}
