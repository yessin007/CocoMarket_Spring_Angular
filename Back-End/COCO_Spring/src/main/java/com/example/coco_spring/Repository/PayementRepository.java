package com.example.coco_spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.coco_spring.Entity.*;

@Repository
public interface PayementRepository extends JpaRepository<Payement,Long> {
}
