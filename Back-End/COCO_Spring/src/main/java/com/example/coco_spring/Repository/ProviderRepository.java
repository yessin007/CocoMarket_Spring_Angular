package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
