package com.example.backend.Feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Feedback {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOfSuggestion;

    private String description;

    private Long userId;

    private String userName;

    private String email;

    private Date dateOfCreation = new Date(System.currentTimeMillis());
}
