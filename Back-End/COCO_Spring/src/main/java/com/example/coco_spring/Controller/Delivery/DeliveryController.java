package com.example.coco_spring.Controller.Delivery;

import com.example.coco_spring.Entity.ClientLocationRequest;
import com.example.coco_spring.Entity.Delivery;
import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Service.Delivery.DeliveryService;
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
    public List<Delivery> retrieveDeliveryList(){

        return deliveryService.findAll();
    }

    @GetMapping("/retrive_delivery/{deliveryId}")
    public Delivery retrieveDelivery(@PathVariable("deliveryId") Long deliveryId){

        return deliveryService.retrieveItem(deliveryId);
    }

    @PostMapping("/add_delivery")

    public Delivery addDelivery(@RequestBody Delivery delivery ){

        return deliveryService.add(delivery);
    }

    @PutMapping("/update_delivery")
    public Delivery updateDelivery(@RequestBody Delivery delivery){

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
        Delivery delivery = deliveryService.dispatchDeliveryToNearestDeliveryman(clientLocationRequest);
        return delivery.getProvider();
    }

    }
