package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.ClientLocation;
import com.example.coco_spring.Entity.ProviderLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLocationRepository extends JpaRepository<ClientLocation, Long> {
}
