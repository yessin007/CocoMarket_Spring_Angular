package com.example.coco_spring.Controller.Delivery;

import com.example.coco_spring.Entity.Delivery;
import com.example.coco_spring.Service.Delivery.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/delivery/")
public class DeliveryController {
    DeliveryService deliveryService;
    @GetMapping("/retrive_all_deliveries")
    public List<Delivery> retrieveDeliveryList(){

        return deliveryService.findAll();
    }

    @GetMapping("/retrive_delivery/{deliveryId}")
    public Delivery retrieveDelivery(@PathVariable("deliveryId") Long deliveryId){

        return deliveryService.retrieveItem(deliveryId);
    }

    @PostMapping("/add_delivery")
    public Delivery addDelivery(@RequestBody Delivery delivery){

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

}
