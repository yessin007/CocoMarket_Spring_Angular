package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscripttionRepository extends JpaRepository<Subscription,Long> {
    Subscription findByUser_IdAndProduct_ProductId(Long userId, Long productId);
}
