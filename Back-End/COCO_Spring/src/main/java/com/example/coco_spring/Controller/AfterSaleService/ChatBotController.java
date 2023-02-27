package com.example.coco_spring.Controller.AfterSaleService;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ch")
public class ChatBotController {
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String OPENAI_API_KEY = "ssk-V8rqmwEDw15L4ADuKTY2T3BlbkFJw3RrL3dmccSb06ULbyhc";

    @PostMapping("/chatbot")
    public String generateResponse() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        headers.set("Content-Type", "application/json");
        String message="hello devinci";
        String requestBody = "{\"prompt\": \"" + message + "\", \"max_tokens\": 60\"}";
        HttpEntity<String> httpRequest = new HttpEntity<>(requestBody, headers);
        String response = restTemplate.postForObject(OPENAI_API_URL, httpRequest, String.class);

        String[] choices = response.split("\"text\":");
        message = choices[1].substring(1, choices[1].indexOf("\",\""));

        return message;
    }
}
