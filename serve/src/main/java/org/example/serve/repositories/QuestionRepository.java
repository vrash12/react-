package org.example.serve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.serve.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Custom query methods if needed
}