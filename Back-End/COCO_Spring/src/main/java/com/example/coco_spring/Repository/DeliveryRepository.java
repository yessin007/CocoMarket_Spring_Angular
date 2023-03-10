package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Delivery;
import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.deliveries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DeliveryRepository extends JpaRepository<deliveries, Long> {


}
