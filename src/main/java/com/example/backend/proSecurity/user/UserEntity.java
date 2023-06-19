package com.example.backend.proSecurity.user;


import com.example.backend.Chat.Chat;
import com.example.backend.ChatContact.ChatContact;
import com.example.backend.Compare.CompareProducts;
import com.example.backend.Products.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String authority;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column
    private String confirmPassword = "BASIC USER";

    private boolean isEnabled;

    @JsonIgnore
    @OneToMany(mappedBy = "userEntity")
    private Set<Product> products;

    @JsonIgnore
    @OneToOne(mappedBy = "userEntity")
    private CompareProducts compareProducts;


    @JsonIgnore
    @OneToMany(mappedBy = "userSender")
    private Set<Chat> chats;

    @JsonIgnore
    @OneToMany(mappedBy = "userReceiver")
    private Set<Chat> chats2;

    @JsonIgnore
    @OneToMany(mappedBy = "firstUser")
    private Set<ChatContact> firstContact;

    @JsonIgnore
    @OneToMany(mappedBy = "secondUser")
    private Set<ChatContact> secondContact;

}
