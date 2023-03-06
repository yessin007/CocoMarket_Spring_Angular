package com.example.coco_spring.Entity;

import javax.persistence.Entity;


public class ChatBotRequest {
    private String prompt;
    private int maxTokens;

    public ChatBotRequest(String prompt, int maxTokens) {
        this.prompt = prompt;
        this.maxTokens = maxTokens;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }
}
