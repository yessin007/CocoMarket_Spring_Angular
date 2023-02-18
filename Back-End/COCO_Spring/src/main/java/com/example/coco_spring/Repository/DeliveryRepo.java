package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<Delivery, Long> {

}
