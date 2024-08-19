package org.example.serve.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizID;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "lessonID", nullable = false)
    private Lesson lesson;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    // Getters and Setters
}