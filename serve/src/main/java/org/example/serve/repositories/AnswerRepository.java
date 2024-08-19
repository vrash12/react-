package org.example.serve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.serve.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // Custom query methods if needed
}