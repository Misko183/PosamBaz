package com.example.backend.ChatContact;

import com.example.backend.proSecurity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatContact {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private boolean accepted;

      @ManyToOne
      private UserEntity firstUser;

      @ManyToOne
      private UserEntity secondUser;

}
