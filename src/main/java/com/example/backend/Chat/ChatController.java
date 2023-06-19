package com.example.backend.Chat;

import com.example.backend.proSecurity.user.CurrentUser;
import com.example.backend.proSecurity.user.UserEntity;
import com.example.backend.proSecurity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatServiceImpl chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/chat")
    public void chat(@RequestBody Chat chat, @AuthenticationPrincipal CurrentUser currentUser) {
        chatService.saveChat(chat, currentUser);
    }

    @PostMapping("/chatGet")
        List<Chat> chatList(@RequestBody Chat chat, @AuthenticationPrincipal CurrentUser currentUser) {
            return chatService.chatList(chat, currentUser);
        }
    @GetMapping("/whoIam")
    public UserEntity whoIam(@AuthenticationPrincipal CurrentUser currentUser) {
        return userRepository.findByUsername(currentUser.getUsername());
    }
    }

