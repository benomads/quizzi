package com.benomads.quizzi.service;

import com.benomads.quizzi.dao.QuestionDao;
import com.benomads.quizzi.dao.QuizDao;

import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.exception.QuizNotFoundException;
import com.benomads.quizzi.model.QuestionWrapper;
import com.benomads.quizzi.entity.Quiz;
import com.benomads.quizzi.model.Response;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizDao quizDao;
    private final QuestionDao questionDao;

    @Autowired
    public QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }


    public List<Quiz> getAllQuizzes() {
        return quizDao.findAll();
    }

    public List<QuestionWrapper> getQuizById(Integer id) {
        List<Question> questionsFromDB = getQuizQuestionsById(id);

        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB)
            questionsForUser.add(QuestionWrapper.toModel(q));

        return questionsForUser;
    }

  
    public Quiz createQuiz(String category,
                           int numberOfQuestions,
                           String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numberOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return quiz;
    }





    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        List<Question> questions= getQuizQuestionsById(id);
        int right = 0;
        int i = 0;

        for (Response resp : responses) {
            String response = resp.getScore();
            String correctAnswer = questions.get(i).getCorrectAnswer();

            if (response.equals(correctAnswer))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }


    private List<Question> getQuizQuestionsById(Integer id) {
        Optional<Quiz> quiz = quizDao.findAllById(id);

        return quiz.get().getQuestions();

    }


    public void deleteQuiz(Integer id) {
        if (!quizDao.existsById(id)) {
            throw new QuizNotFoundException(String.format(
                "Question with id=%d doesn't exist)", id));
        }

        quizDao.deleteById(id);
    }

}
