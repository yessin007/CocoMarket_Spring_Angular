package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

    @Query("SELECT SUM(s.quantity * s.price) FROM Sale s WHERE s.product.productId = :productId")
    Double findTotalRevenueByProduct(@Param("productId") Long productId);

        @Query("SELECT p.title, SUM(s.quantity) as quantitySold "
                + "FROM Sale s "
                + "JOIN s.product p "
                + "WHERE s.saleDate BETWEEN :startDate AND :endDate "
                + "GROUP BY p.productId"
                + " order by quantitySold DESC")
        List<Object[]> findTopSellingProducts(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Sale> findAllByOrderByQuantityDesc();
}
