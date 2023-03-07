package com.example.coco_spring.Model;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ChatMessage {

    private MessageType type ;

    private String content ;

    private String sender ;

    private String time ;
}
