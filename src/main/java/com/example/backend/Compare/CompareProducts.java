package com.example.backend.Compare;

import com.example.backend.Products.Product;
import com.example.backend.proSecurity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompareProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity userEntity;

    @ManyToOne
    private Product product1;

    @ManyToOne
    private Product product2;


}
