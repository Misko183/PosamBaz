package com.example.backend.ChatContact;

import com.example.backend.proSecurity.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatContactController {

    @Autowired
    private ChatContactServiceImpl chatContactService;

    @PostMapping("/addChatContact")
    void addChatContact(@RequestBody ChatContact chatContact, @AuthenticationPrincipal CurrentUser currentUser) {
        chatContactService.addChatContact(chatContact, currentUser);
    }

    @GetMapping("/getChatContacts")
    List<ChatContact> getChatContacts(@AuthenticationPrincipal CurrentUser currentUser) {
        return chatContactService.getChatContacts(currentUser);
    }

    @GetMapping("/getChatContactsAccepted")
    List<ChatContact> getChatContactsAccepted(@AuthenticationPrincipal CurrentUser currentUser) {
        return chatContactService.getChatContactsAccepted(currentUser);
    }

   //accept chat contact
    @PostMapping("/acceptChatContact")
    void acceptChatContact(@RequestBody ChatContact chatContact, @AuthenticationPrincipal CurrentUser currentUser) {
        chatContactService.acceptChatContact(chatContact, currentUser);
    }

    //delete chat contact
    @PostMapping("/deleteChatContact")
    void deleteChatContact(@RequestBody ChatContact chatContact, @AuthenticationPrincipal CurrentUser currentUser) {
        chatContactService.deleteChatContact(chatContact, currentUser);
    }

}
