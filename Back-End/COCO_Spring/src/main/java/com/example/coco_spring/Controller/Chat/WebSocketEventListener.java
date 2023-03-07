package com.example.coco_spring.Controller.Chat;

import com.example.coco_spring.Model.ChatMessage;
import com.example.coco_spring.Model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.logging.Logger;

@Component
@Slf4j
public class WebSocketEventListener
{
    @Autowired
 private SimpMessageSendingOperations sendingOperations ;
  @EventListener
  public void handlWebSocketConnectListener(final SessionConnectedEvent event){
log.info("bing bong . we have a new cheeky little connection !");
  }
  @EventListener
    public void handlewebSocketDisconnectListener(final SessionConnectedEvent event){
      final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

      final String username =(String) headerAccessor.getSessionAttributes().get("username");

      final ChatMessage chatMessage =ChatMessage.builder()
              .type(MessageType.DISCONNECT)
              .sender(username)
              .build();

      sendingOperations.convertAndSend("/topic/public",chatMessage);
  }
}
