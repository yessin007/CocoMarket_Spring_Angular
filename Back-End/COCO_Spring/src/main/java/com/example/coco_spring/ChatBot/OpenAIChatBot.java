package com.example.coco_spring.ChatBot;

        import org.springframework.web.client.RestTemplate;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpEntity;

public class OpenAIChatBot {
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String OPENAI_API_KEY = "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";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        headers.set("Content-Type", "application/json");

        String message = "Hello, Davinci!";
        while (true) {
            String requestBody = "{\"prompt\": \"" + message +  "\", \"max_tokens\": 60}";
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            String response = restTemplate.postForObject(OPENAI_API_URL, request, String.class);
            String[] choices = response.split("\"text\":");
            message = choices[1].substring(1, choices[1].indexOf("\",\""));
            System.out.println("Davinci: " + message);
        }
    }
}