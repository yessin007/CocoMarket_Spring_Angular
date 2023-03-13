package com.example.coco_spring.Controller.AfterSaleService;

import com.example.coco_spring.Entity.CustomerFeedback;
import com.example.coco_spring.Service.AfterSaleServices.ASSService;
import com.example.coco_spring.Service.AfterSaleServices.FeedbackService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/feedback")
public class CustomerFeedbackController {
 FeedbackService feedbackList;
ASSService assService;
    @PostMapping("/feedback")
    public ResponseEntity<String> addFeedback(@RequestBody CustomerFeedback feedback) {
        feedbackList.add(feedback);
        return ResponseEntity.ok("Feedback added successfully");
    }

   /* @GetMapping("/feedback/satisfaction-score")
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
    @GetMapping("/common-words")
    public ResponseEntity<List<String>> getMostCommonWords(@RequestParam int limit) {
        List<String> result = assService.getMostCommonWords(limit);
        return ResponseEntity.ok(result);
    }*/

}

