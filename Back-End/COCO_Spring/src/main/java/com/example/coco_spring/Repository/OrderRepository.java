package com.example.coco_spring.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.coco_spring.Entity.*;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    // Show Provider name with their orders
    //@Query("SELECT p.firstName as n FROM Provider p JOIN p.deliveries d JOIN d.orders ")
    //  List<String> findProviderNamesWithOrder();

    @Query("SELECT o FROM Order o")
    List<Order> findAllOrders();

    //Show Orders list of every Provider
    // List<Order> findByDelivery_Provider_FirstName(String name);


}
