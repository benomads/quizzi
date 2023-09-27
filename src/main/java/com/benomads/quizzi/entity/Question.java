package com.benomads.quizzi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Entity(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficultyLevel;
    private String correctAnswer;
    private String category;


}
