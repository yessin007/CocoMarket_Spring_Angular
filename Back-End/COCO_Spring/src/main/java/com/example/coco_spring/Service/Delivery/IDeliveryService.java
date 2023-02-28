package com.example.coco_spring.Service.Delivery;

public interface IDeliveryService {
    public void assignDeliveryToOrder(Long orderId, Long deliveryId);
    public void assignProviderDelivery(Long deliveryId, Long providerId);


}
