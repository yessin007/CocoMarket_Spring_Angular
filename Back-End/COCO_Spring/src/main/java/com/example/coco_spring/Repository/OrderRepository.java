package com.example.coco_spring.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.coco_spring.Entity.*;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT p.providerName as n FROM Provider p JOIN p.deliveries d JOIN d.orders ")
    List<String> findProviderNamesWithOrder();

    List<Order> findByDelivery_Provider_ProviderName(String name);
}
