package com.example.backend.Feedback;

import com.example.backend.proSecurity.user.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface FeedbackService {

    void saveFeedback(Feedback feedback, @AuthenticationPrincipal CurrentUser currentUser);

    void deleteFeedback(Feedback feedback);

}
