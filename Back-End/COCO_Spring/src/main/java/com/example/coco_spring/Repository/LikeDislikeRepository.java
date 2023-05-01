package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.LikeDislikeProduct;
import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDislikeRepository extends JpaRepository<LikeDislikeProduct,Long> {
	LikeDislikeProduct findByProductAndUser(Product product, User user);
}
