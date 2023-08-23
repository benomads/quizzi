package com.benomads.quizzi.dao;

import com.benomads.quizzi.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
    Optional<Quiz> findAllById(Integer id);
}
