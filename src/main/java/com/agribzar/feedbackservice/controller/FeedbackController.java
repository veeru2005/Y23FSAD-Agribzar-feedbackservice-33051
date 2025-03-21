package com.agribzar.feedbackservice.controller;

import com.agribzar.feedbackservice.model.Feedback;
import com.agribzar.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:5173")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // ✅ POST request to save feedback
    @PostMapping
    public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback) {
        // Call the properly implemented method in FeedbackService
        Feedback savedFeedback = feedbackService.addFeedback(feedback);

        // Return 201 (Created) to indicate a new resource has been saved
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedback);
    }

    // ✅ GET request to retrieve all feedback with debugging
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbackList = feedbackService.getAllFeedback();

        // 🔍 Debugging
        System.out.println("Fetched Feedback: " + feedbackList);

        // If there are no feedback entries, return 204 No Content
        if (feedbackList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbackList);
    }
}
