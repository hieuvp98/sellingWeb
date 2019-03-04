package com.bksoftware.sellingweb.service_impl;


import com.bksoftware.sellingweb.entities.Feedback;
import com.bksoftware.sellingweb.repository.FeedbackRepository;
import com.bksoftware.sellingweb.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FeedbackService_Impl implements FeedbackService {

    private final static Logger LOGGER = Logger.getLogger(FeedbackService_Impl.class.getName());

    private final
    FeedbackRepository feedbackRepository;

    public FeedbackService_Impl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> findAllFeedback() {
        try {
            return feedbackRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-feedback-error : {0}", ex.getMessage());
        }
        return null;
    }


}
