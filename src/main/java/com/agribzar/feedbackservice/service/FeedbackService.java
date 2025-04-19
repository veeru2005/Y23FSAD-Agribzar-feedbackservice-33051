package com.agribzar.feedbackservice.service;

import com.agribzar.feedbackservice.model.Feedback;
import com.agribzar.feedbackservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Feedback saveFeedback(Feedback feedback) {
        Feedback savedFeedback = feedbackRepository.save(feedback);
        sendThankYouEmail(feedback);
        return savedFeedback;
    }

    private void sendThankYouEmail(Feedback feedback) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(feedback.getEmail());
        message.setSubject("Thank You for Your Feedback");
        message.setText(feedback.getCustomerName() + ",\n\nThank you for submitting your feedback. We will use your valuable inputs for our application's betterment.\n\nRegards,\nAgribzar");
        mailSender.send(message);
    }
}
