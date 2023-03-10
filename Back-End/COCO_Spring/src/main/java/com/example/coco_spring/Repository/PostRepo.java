package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.PostStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostStore, Long> {
}
