package com.benomads.quizzi.dao;

import com.benomads.quizzi.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {
    List<Question> findQuestionByCategory(String category);
}
