package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.ProviderLocation;
import com.example.coco_spring.Entity.StoreLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderLocationRepository extends JpaRepository<ProviderLocation,Long> {
}
