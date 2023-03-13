package com.example.coco_spring.Service.Delivery;

import com.example.coco_spring.Entity.Delivery;
import com.example.coco_spring.Entity.Order;
import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Repository.DeliveryRepository;
import com.example.coco_spring.Repository.OrderRepository;
import com.example.coco_spring.Repository.ProviderRepository;
import com.example.coco_spring.Service.ICRUDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class DeliveryService implements ICRUDService<Delivery,Long>, IDeliveryService {

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


    public void assignDeliveryToOrder(Long orderId, Long deliveryId){
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
}
