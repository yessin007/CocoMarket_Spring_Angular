package com.example.coco_spring.Service.Delivery;

import com.example.coco_spring.Entity.Delivery;

public interface IDeliveryService {
    public void assignDeliveryToOrder(Long orderId, Long deliveryId);
    public void assignProviderDelivery(Long deliveryId, Long providerId);
    public Delivery changeStatusToProg(Long id);
    public Delivery changeStatusToDelivered(Long id);
}
