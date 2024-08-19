package org.example.serve.model;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerID;

    @Lob
    private String answerText;

    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "questionID", nullable = false)
    private Question question;

    // Getters and Setters
}
