package com.example.coco_spring.websocketproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatmessageRepo extends JpaRepository<ChatMessage, Long>{

}
