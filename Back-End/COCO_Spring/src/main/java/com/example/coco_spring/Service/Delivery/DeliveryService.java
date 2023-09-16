package com.example.coco_spring.Service.Delivery;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.ICRUDService;
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
    private final UserRepository userRepository;

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
    public Delivery dispatchDeliveryToNearestDeliveryman(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).get();

        List<Provider> providers = getDeliverymenWithinRadius(delivery.getClientLatitude(),
                delivery.getClientLongitude(), 20); // 20 km radius

        Provider nearestDeliveryman = getNearestDeliveryman(providers, delivery.getClientLatitude(),
                delivery.getClientLongitude());

        delivery.setProvider(nearestDeliveryman);
        delivery.setStatut(Status.IN_PROGRESS);
        return deliveryRepository.save(delivery);

    }
    private List<Provider> getDeliverymenWithinRadius(double latitude, double longitude, int radiusInKm) {
        List<Provider> providers = providerRepository.findAll();
        List<Provider> deliverymenWithinRadius = new ArrayList<>();
        for (Provider provider : providers) {
            double distance = distanceInKm(latitude, longitude, provider.getProviderLocation().getLatitude(), provider.getProviderLocation().getLongitude());
            if (distance <= radiusInKm) {
                deliverymenWithinRadius.add(provider);
            }
        }
        return deliverymenWithinRadius;
    }

    private Provider getNearestDeliveryman(List<Provider> providers, double clientLatitude,
                                           double clientLongitude) {
        Provider nearestDeliveryman = null;
        double shortestDistance = Double.MAX_VALUE;
        for (Provider provider : providers) {
            double distance = distanceInKm(clientLatitude, clientLongitude, provider.getProviderLocation().getLatitude(),
                    provider.getProviderLocation().getLongitude());
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestDeliveryman = provider;
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
        dist = dist * 1.609344;
        return dist;
    }
    @Transactional
    public Delivery addjjj(Delivery delivery, double lat, double lng) {
        ClientLocation clientLocation = new ClientLocation();
        clientLocation.setLongitude(lng);
        clientLocation.setLatitude(lat);
        delivery.setClientLocation(clientLocation);
        return deliveryRepository.save(delivery);

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