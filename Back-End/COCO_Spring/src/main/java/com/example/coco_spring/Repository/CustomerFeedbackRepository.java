package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.CustomerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback,Long> {
}
