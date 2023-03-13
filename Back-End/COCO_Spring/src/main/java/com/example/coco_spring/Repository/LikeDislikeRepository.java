package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.LikeDislikeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDislikeRepository extends JpaRepository<LikeDislikeProduct,Long> {
}
