package org.example.serve.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonID;

    private String title;
    private String description;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "lesson")
    private List<Quiz> quizzes;

    // Getters and Setters
}
