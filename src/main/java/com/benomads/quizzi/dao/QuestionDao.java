package com.benomads.quizzi.dao;

import com.benomads.quizzi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {
    List<Question> findQuestionByCategory(String category);

    Boolean existsQuestionByQuestionTitle(String questionTitle);

    Boolean existsQuestionByCategory(String category);



    @Query(value = "SELECT * FROM question q WHERE q.category=:category " +
                   "ORDER BY RANDOM() LIMIT :numberOfQuestions",
                    nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numberOfQuestions);
}
