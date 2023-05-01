package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.LikeDislikeProduct;
import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductRate;
import com.example.coco_spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeDislikeRepository extends JpaRepository<LikeDislikeProduct,Long> {
    LikeDislikeProduct findByProductAndUser(Product product, User user);
    List<LikeDislikeProduct> findByUser_IdAndProduct_ProductIdAndProductRate(Long userId, Long productId, ProductRate productRate);

}
