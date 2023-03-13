package com.example.coco_spring.Service.Delivery;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.DeliveryRepository;
import com.example.coco_spring.Repository.OrderRepository;
import com.example.coco_spring.Repository.ProviderRepository;
import com.example.coco_spring.Service.ICRUDService;
import com.example.coco_spring.websocketproject.ChatmessageRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DeliveryService implements ICRUDService<Delivery,Long>, IDeliveryService {
    private final ChatmessageRepo chatmessageRepo;

    DeliveryRepository deliveryRepository;
    OrderRepository orderRepository;
    ProviderRepository providerRepository;
    @Override
    public List<Delivery> findAll() {

        return deliveryRepository.findAll();
    }

    @Override
    public Delivery retrieveItem(Long deliveryId) {

        return deliveryRepository.findById(deliveryId).get();
    }

    @Override
    public Delivery add(Delivery delivery) {

        return deliveryRepository.save(delivery);
    }

    @Override
    public void delete(Long deliveryId) {

        deliveryRepository.deleteById(deliveryId);
    }

    @Override
    public Delivery update(Delivery delivery) {

        return deliveryRepository.save(delivery);
    }


    /*public void assignDeliveryToOrder(Long orderId, Long deliveryId){
        Order order = orderRepository.findById(orderId).get();
        deliveries delivery = deliveryRepository.findById(deliveryId).get();
        order.setDelivery(delivery);
        orderRepository.save(order);

    }*/


    @Override
    public void assignDeliveryToOrder(Long orderId, Long deliveryId) {
        Order order = orderRepository.findById(orderId).get();
        Delivery delivery = deliveryRepository.findById(deliveryId).get();
        order.setDelivery(delivery);
        orderRepository.save(order);

    }

    public void assignProviderDelivery(Long deliveryId, Long providerId) {
        Provider provider = providerRepository.findById(providerId).get();
        Delivery delivery = deliveryRepository.findById(deliveryId).get();
        delivery.setProvider(provider);
        deliveryRepository.save(delivery);
    }

    @Transactional
    public Delivery dispatchDeliveryToNearestDeliveryman(ProviderLocation clientLocation) {
        List<Provider> deliverymen = getDeliverymenWithinRadius(clientLocation.getLatitude(),
                clientLocation.getLongitude(), 15); // 10 km radius

        Provider nearestDeliveryman = getNearestDeliveryman(deliverymen, clientLocation.getLatitude(),
                clientLocation.getLongitude());
        Delivery delivery = new Delivery();
        delivery.setProvider(nearestDeliveryman);

        delivery.setClientAddress(clientLocation.getAddress());
        delivery.setClientLatitude(clientLocation.getLatitude());
        delivery.setClientLongitude(clientLocation.getLongitude());
        delivery.setStatut(Status.PENDING);
        return deliveryRepository.save(delivery);

    }

    private List<Provider> getDeliverymenWithinRadius(double latitude, double longitude, int radiusInKm) {
        List<Provider> deliverymen = providerRepository.findAllDeliverymen();
        List<Provider> deliverymenWithinRadius = new ArrayList<>();
        for (Provider deliveryman : deliverymen) {
            double distance = distanceInKm(latitude, longitude, deliveryman.getProviderLocation().getLatitude(), deliveryman.getProviderLocation().getLongitude());
            if (distance <= radiusInKm) {
                deliverymenWithinRadius.add(deliveryman);
            }
        }
        return deliverymenWithinRadius;
    }

    private Provider getNearestDeliveryman(List<Provider> deliverymen, double clientLatitude,
                                              double clientLongitude) {
        Provider nearestDeliveryman = null;
        double shortestDistance = Double.MAX_VALUE;
        for (Provider deliveryman : deliverymen) {
            double distance = distanceInKm(clientLatitude, clientLongitude, deliveryman.getProviderLocation().getLatitude(),
                    deliveryman.getProviderLocation().getLongitude());
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestDeliveryman = deliveryman;
            }
        }
        return nearestDeliveryman;
    }

    private double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // convert to kilometers
        return dist;
    }
    public Delivery cancelDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id).get();
        delivery.setCancelled(true);
        return deliveryRepository.save(delivery);
    }
    public  Delivery changeStatusToProg(Long id){
        Delivery delivery = deliveryRepository.findById(id).get();
        delivery.setStatut(Status.IN_PROGRESS);
        return deliveryRepository.save(delivery);
    }
    public  Delivery changeStatusToDelivered(Long id){
        Delivery delivery = deliveryRepository.findById(id).get();
        delivery.setStatut(Status.DELIVERED);
        return deliveryRepository.save(delivery);
    }

}

