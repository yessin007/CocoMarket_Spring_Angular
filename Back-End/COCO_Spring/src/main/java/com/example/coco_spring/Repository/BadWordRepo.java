package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.BadWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadWordRepo extends JpaRepository<BadWords, Long> {
}