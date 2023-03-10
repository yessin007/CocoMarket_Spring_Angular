package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.PostDislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostDislikeRepo extends JpaRepository<PostDislike, Long> {
}
