package org.example.serve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.serve.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // Custom query methods if needed
}