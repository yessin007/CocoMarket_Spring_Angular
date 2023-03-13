package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuizQuestion, Long> {

}