package com.example.backend.Favourite;

import com.example.backend.Products.Product;
//import com.example.backend.User.User;
import com.example.backend.proSecurity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    /*
    @ManyToOne
    User user;
    */

    @ManyToOne
    Product product;

    @ManyToOne
    UserEntity userEntity;


}
