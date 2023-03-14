package com.example.coco_spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.coco_spring.Entity.*;

import java.util.Date;
import java.util.List;

@Repository
public interface PayementRepository extends JpaRepository<Payement,Long> {



    @Query("SELECT o from Order o Join o.payement p where p.payementDate =?1")
    List<Order> findByPaymentDate(@Param("date") Date date);



}
