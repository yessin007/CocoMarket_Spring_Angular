package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.ClientLocationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clientLocationRequestRepository  extends JpaRepository<ClientLocationRequest, Long> {
}
