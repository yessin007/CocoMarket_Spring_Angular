package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.productId = :productId")
	Double getAverageRatingByProductId(@Param("productId") Long productId);
}
