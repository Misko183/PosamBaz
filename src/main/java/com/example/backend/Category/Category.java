package com.example.backend.Category;

import com.example.backend.Image.Image;
import com.example.backend.ImageOfCategory.ImageOfCategory;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "kindOfCategory")
    private Set<Product> products;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageOfCategory imageOfCategory;


}
