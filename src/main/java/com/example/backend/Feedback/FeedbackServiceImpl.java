package com.example.backend.Feedback;

//import com.example.backend.User.UserServiceImpl;
import com.example.backend.proSecurity.user.CurrentUser;
import com.example.backend.proSecurity.user.UserEntity;
import com.example.backend.proSecurity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

//    @Autowired
//    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveFeedback(Feedback feedback, @AuthenticationPrincipal CurrentUser currentUser) {
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername());

          feedback.setUserId(userEntity.getId());
          feedback.setUserName(userEntity.getUsername());
          feedback.setEmail(userEntity.getEmail());

          feedbackRepository.save(feedback);

    }

    @Override
    public void deleteFeedback(Feedback feedback)
    {
        feedbackRepository.delete(feedback);
    }


}

