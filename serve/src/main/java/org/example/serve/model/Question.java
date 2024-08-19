package org.example.serve.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionID;

    @Lob
    private String questionText;

    private String questionType; // e.g., "Multiple Choice", "True/False"
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "quizID", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    // Getters and Setters
}
