package com.example.backend.Chat;

import com.example.backend.proSecurity.user.CurrentUser;
import com.example.backend.proSecurity.user.UserEntity;
import com.example.backend.proSecurity.user.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    void saveChat(Chat chat, CurrentUser currentUser) {

           if(chatRepository.findAllByUserSender_UsernameIgnoreCaseAndUserReceiver_UsernameIgnoreCase(chat.getUserReceiver().getUsername(), currentUser.getUsername()).size() > 0)
        {
             chat.setIdentificator(chatRepository.findTopByUserSender_UsernameIgnoreCaseAndUserReceiver_UsernameIgnoreCase(chat.getUserReceiver().getUsername(),currentUser.getUsername()).getIdentificator());
//             chat.setIdentificator(userRepository.findByUsernameIgnoreCase(currentUser.getUsername()).getUsername() + userRepository.findByUsernameIgnoreCase(chat.getUserReceiver().getUsername()).getUsername());

            chat.setUserSender(userRepository.findByUsernameIgnoreCase(currentUser.getUsername()));
            chat.setUserReceiver(userRepository.findByUsernameIgnoreCase(chat.getUserReceiver().getUsername()));
            chatRepository.save(chat);
        }

            else {
               chat.setIdentificator(userRepository.findByUsernameIgnoreCase(chat.getUserReceiver().getUsername()).getUsername() + userRepository.findByUsernameIgnoreCase(currentUser.getUsername()).getUsername());

               chat.setUserSender(userRepository.findByUsernameIgnoreCase(currentUser.getUsername()));
            chat.setUserReceiver(userRepository.findByUsernameIgnoreCase(chat.getUserReceiver().getUsername()));
            chatRepository.save(chat);
        }
    }


       List<Chat> chatList(Chat chat, CurrentUser currentUser) {
           UserEntity currentUserMe = userRepository.findByUsernameIgnoreCase(currentUser.getUsername());
           UserEntity currentUserHe = userRepository.findByUsernameIgnoreCase(chat.getUserReceiver().getUsername());


           if(currentUserMe.getUsername() == chat.getUserReceiver().getUsername()){
            chat.setUserReceiver(currentUserHe);
            chat.setUserSender(currentUserMe);
               List<Chat> chatList = chatRepository.findAllByIdentificator(chat.getUserSender().getUsername() + chat.getUserReceiver().getUsername());

               if (chatList.size() > 0) {
                   return chatList;
               }
               else {
                   return chatRepository.findAllByIdentificator(chat.getUserReceiver().getUsername() + chat.getUserSender().getUsername());
               }


           }

       else{
               List<Chat> chatList = chatRepository.findAllByIdentificator(chat.getUserSender().getUsername() + chat.getUserReceiver().getUsername());

               if (chatList.size() > 0) {
                   return chatList;
               }
               else {
                   return chatRepository.findAllByIdentificator(chat.getUserReceiver().getUsername() + chat.getUserSender().getUsername());
               }
       }
       //send a rec
//        if(chatRepository.findAllByUserSender_UsernameIgnoreCaseAndUserReceiver_UsernameIgnoreCase(chat.getUserReceiver().getUsername(), currentUser.getUsername()).size() > 0)
//        {
//         return chatRepository.findAllByIdentificator(userRepository.findByUsernameIgnoreCase(chat.getUserReceiver().getUsername()).getUsername() + userRepository.findByUsernameIgnoreCase(currentUser.getUsername()).getUsername());
//
//        }
//        else {
//            return chatRepository.findAllByIdentificator(userRepository.findByUsernameIgnoreCase(currentUser.getUsername()).getUsername() + userRepository.findByUsernameIgnoreCase(chat.getUserReceiver().getUsername()).getUsername());
//        }

    }

}
