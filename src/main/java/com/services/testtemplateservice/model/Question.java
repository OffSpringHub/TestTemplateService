package com.services.testtemplateservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private int estimated_time;
    private float score;
    @OneToOne
    private Category category;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<Options> options;
}
