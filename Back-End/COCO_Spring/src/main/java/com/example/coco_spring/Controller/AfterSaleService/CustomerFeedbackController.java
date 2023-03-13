package com.example.coco_spring.Controller.AfterSaleService;

import com.example.coco_spring.Entity.CustomerFeedback;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/feedback")
public class CustomerFeedbackController {
    private List<CustomerFeedback> feedbackList = new ArrayList<>();

    @PostMapping("/feedback")
    public ResponseEntity<String> addFeedback(@RequestBody CustomerFeedback feedback) {
        feedbackList.add(feedback);
        return ResponseEntity.ok("Feedback added successfully");
    }

    @GetMapping("/feedback/satisfaction-score")
    public ResponseEntity<Double> getSatisfactionScore() {
        if (feedbackList.isEmpty()) {
            return ResponseEntity.ok(0.0);
        }

        double totalScore = 0.0;
        for (CustomerFeedback feedback : feedbackList) {
            totalScore += feedback.getScore();
        }

        double satisfactionScore = totalScore / feedbackList.size();
        return ResponseEntity.ok(satisfactionScore);
    }
}

