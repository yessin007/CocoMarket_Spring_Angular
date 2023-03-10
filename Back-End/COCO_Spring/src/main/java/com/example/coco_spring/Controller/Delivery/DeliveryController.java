package com.example.coco_spring.Controller.Delivery;

import com.example.coco_spring.Entity.ClientLocationRequest;
import com.example.coco_spring.Entity.Delivery;
import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.deliveries;
import com.example.coco_spring.Repository.OrderRepository;
import com.example.coco_spring.Service.Delivery.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery/")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/retrive_all_deliveries")
    public List<deliveries> retrieveDeliveryList(){

        return deliveryService.findAll();
    }

    @GetMapping("/retrive_delivery/{deliveryId}")
    public deliveries retrieveDelivery(@PathVariable("deliveryId") Long deliveryId){

        return deliveryService.retrieveItem(deliveryId);
    }

    @PostMapping("/add_delivery")

    public deliveries addDelivery(@RequestBody deliveries delivery ){

        return deliveryService.add(delivery);
    }

    @PutMapping("/update_delivery")
    public deliveries updateDelivery(@RequestBody deliveries delivery){

        return deliveryService.update(delivery);
    }

    @DeleteMapping("/delete_delivery/{deliveryId}")
    public void deleteDelivery(@PathVariable("deliveryId") Long deliveryId){

        deliveryService.delete(deliveryId);
    }
    @PutMapping("/assignDeliveryToOrder/{orderId}/{deliveryId}")
    public void assignDeliveryToOrder(@PathVariable("orderId") Long orderId,@PathVariable("deliveryId") Long deliveryId){
        deliveryService.assignDeliveryToOrder(orderId,deliveryId);
    }

    @PutMapping("/assignProviderDelivery/{deliveryId}/{providerId}")
    public void assignProviderDelivery(@PathVariable("deliveryId") Long deliveryId,@PathVariable("providerId") Long providerId){
        deliveryService.assignProviderDelivery(deliveryId,providerId);

    }

    @PostMapping("/dispatch")
    public Provider dispatchDeliveryToNearestDeliveryman(@RequestBody ClientLocationRequest clientLocationRequest) {
        deliveries delivery = deliveryService.dispatchDeliveryToNearestDeliveryman(clientLocationRequest);
        return delivery.getProvider();
    }

    }
