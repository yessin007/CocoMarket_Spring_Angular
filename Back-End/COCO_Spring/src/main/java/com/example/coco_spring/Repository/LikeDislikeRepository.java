package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeDislikeRepository extends JpaRepository<LikeDislikeProduct,Long> {
    LikeDislikeProduct findByProductAndUser(Product product, User user);
    LikeDislikeProduct findByReviewAndUser(Review review, User user);
    List<LikeDislikeProduct> findByUser_IdAndProduct_ProductIdAndProductRate(Long userId, Long productId, ProductRate productRate);

}
