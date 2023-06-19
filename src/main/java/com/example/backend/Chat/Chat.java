package com.example.backend.Chat;

import com.example.backend.proSecurity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificator;

    private String message;

    private Date date = new Date(System.currentTimeMillis());

    @ManyToOne
    private UserEntity userSender;

    @ManyToOne
    private UserEntity userReceiver;

}
