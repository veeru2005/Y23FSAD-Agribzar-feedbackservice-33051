package com.agribzar.feedbackservice.service;

import com.agribzar.feedbackservice.model.Feedback;
import com.agribzar.feedbackservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // ✅ Fetch all feedbacks (returns empty list if none exist)
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // ✅ Fetch a specific feedback by ID
    public Optional<Feedback> getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

    // ✅ Add new feedback
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // ✅ Update existing feedback (checks if feedback exists before updating)
    public Feedback updateFeedback(Long feedbackId, Feedback feedbackDetails) {
        return feedbackRepository.findById(feedbackId)
                .map(existingFeedback -> {
                    existingFeedback.setCustomerName(feedbackDetails.getCustomerName());
                    existingFeedback.setEmail(feedbackDetails.getEmail());
                    existingFeedback.setFeedback(feedbackDetails.getFeedback());
                    return feedbackRepository.save(existingFeedback);
                })
                .orElseThrow(() -> new RuntimeException("Feedback with ID " + feedbackId + " not found"));
    }

    // ✅ Delete feedback by ID (checks if ID exists before deleting)
    public void deleteFeedback(Long feedbackId) {
        if (feedbackRepository.existsById(feedbackId)) {
            feedbackRepository.deleteById(feedbackId);
        } else {
            throw new RuntimeException("Feedback with ID " + feedbackId + " not found");
        }
    }
}
