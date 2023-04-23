package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepo extends JpaRepository<PostComment, Long> {
}