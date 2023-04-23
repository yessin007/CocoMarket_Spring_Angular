package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Store;
import com.example.coco_spring.Entity.StoreLocations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLocationsRepository extends JpaRepository<StoreLocations,Long> {
}
