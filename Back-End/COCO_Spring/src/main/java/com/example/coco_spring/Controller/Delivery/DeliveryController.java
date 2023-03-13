package com.example.coco_spring.Controller.Delivery;

import com.example.coco_spring.Entity.ProviderLocation;
import com.example.coco_spring.Entity.Delivery;
import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.StoreLocations;
import com.example.coco_spring.Repository.DeliveryRepository;
import com.example.coco_spring.Service.Delivery.DeliveryService;
import com.example.coco_spring.Service.Delivery.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery/")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryRepository deliveryRepository;

    private final LocationService locationService;

    public DeliveryController(DeliveryService deliveryService,
                              DeliveryRepository deliveryRepository,LocationService locationService)  {
        this.deliveryService = deliveryService;
        this.deliveryRepository = deliveryRepository;
        this.locationService = locationService;
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
    public Provider dispatchDeliveryToNearestDeliveryman(@RequestBody ProviderLocation clientLocation) {
        Delivery delivery = deliveryService.dispatchDeliveryToNearestDeliveryman(clientLocation);
        return delivery.getProvider();
    }
    @PutMapping("/cancelDelivery/{id}")
    public Delivery cancelDelivery(@PathVariable Long id) {
       return deliveryService.cancelDelivery(id);
    }
    @GetMapping("/getNearestStorewithproduct/{productname}/{clientLatitude}/{clientLongitude}")
    public Map<String , StoreLocations> getNearestStorewithproduct(@PathVariable("productname") String productname,@PathVariable("clientLatitude") double clientLatitude,
                                                                   @PathVariable("clientLongitude") double clientLongitude) {
        return locationService.getNearestStorewithproduct(productname,clientLatitude,clientLongitude);
    }

    @PutMapping("/changeStatusToProg/{id}")
    public  Delivery changeStatusToProg(@PathVariable("id")Long id){
      return   deliveryService.changeStatusToProg(id);
    }
    @PutMapping("/changeStatusToDelivered/{id}")
    public  Delivery changeStatusToDelivered(@PathVariable("id")Long id){
        return   deliveryService.changeStatusToDelivered(id);
    }

}
