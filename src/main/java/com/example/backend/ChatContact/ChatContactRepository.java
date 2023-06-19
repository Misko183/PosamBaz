package com.example.backend.ChatContact;

import com.example.backend.proSecurity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ChatContactRepository extends CrudRepository<ChatContact, Long> {

    List<ChatContact> findAllBySecondUser(UserEntity secondUser);

    List<ChatContact> findAllByFirstUser(UserEntity firstUser);

    List<ChatContact> findAllBySecondUserAndAccepted(UserEntity secondUser, boolean accepted);

    List<ChatContact> findAllByFirstUserAndAccepted(UserEntity firstUser, boolean accepted);

    List<ChatContact> findAllByAcceptedAndSecondUserOrAcceptedAndFirstUser(boolean accepted, UserEntity secondUser, boolean accepted2, UserEntity firstUser);

    ChatContact findByFirstUserAndSecondUser(UserEntity firstUser, UserEntity secondUser);


}
