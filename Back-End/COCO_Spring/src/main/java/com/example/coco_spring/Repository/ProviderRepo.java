package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepo extends JpaRepository<Provider, Long> {
}
