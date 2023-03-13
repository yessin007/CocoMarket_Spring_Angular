package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AfterSaleServicesRepository extends JpaRepository<AfterSaleServices,Long> {
    List<AfterSaleServices> findByStore(Store store);
}
