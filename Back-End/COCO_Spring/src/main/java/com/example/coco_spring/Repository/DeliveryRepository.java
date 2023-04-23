package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {


}