package com.example.coco_spring.Controller.Order;


import com.example.coco_spring.Service.Order.Order1Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.Order.DiscountCodeService;
import com.example.coco_spring.Service.Order.OrderService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order1/")

@CrossOrigin("*")

public class Order1Controller {

    DiscountCodeService discountCodeService;
    OrderService orderService;
    Order1Service order1Service;
    private final OrderRepository orderRepository;

    @GetMapping("/retrive_all_orders")
    public List<Order1> retrieveOrderList(){
        return order1Service.findAll();
    }

    @GetMapping("/retrive_order/{orderId}")
    public Order1 retrieveOrder(@PathVariable("orderId") Long orderId){
        return order1Service.retrieveItem(orderId);
    }

    @PostMapping("/add_order")
    public Order1 addOrder(@RequestBody Order1 order1){
        return order1Service.add(order1);
    }

    @PutMapping("/update_order")
    public Order1 updateOrder(@RequestBody Order1 order){
        return order1Service.update(order);
    }

    @DeleteMapping("/delete_order/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        order1Service.delete(orderId);
    }


//    @GetMapping("/orderslistbyprovider")
//    public Map<String,List<Order>> displayOrdersByProvider() {
//        return orderService.displayOrdersByProvider();
//    }


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