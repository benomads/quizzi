package com.benomads.quizzi.service;

import com.benomads.quizzi.dao.QuestionDao;
import com.benomads.quizzi.dao.QuizDao;
import com.benomads.quizzi.entity.Question;
import com.benomads.quizzi.exception.QuizAlreadyExistsException;
import com.benomads.quizzi.exception.QuizNotFoundException;
import com.benomads.quizzi.model.QuestionWrapper;
import com.benomads.quizzi.entity.Quiz;
import com.benomads.quizzi.model.Response;

import org.springframework.beans.factory.annotation.Autowired;

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
        Optional<Quiz> quiz = quizDao.findAllById(id);
        if (quiz.isEmpty())
            throw new QuizNotFoundException(
                String.format("Quiz with id=%d not found!", id));

        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB)
            questionsForUser.add(QuestionWrapper.toModel(q));

        return questionsForUser;
    }

    // This method create(assembling) quiz randomly with existing questions
    public Quiz createQuizTest(String category,
                           int numberOfQuestions,
                           String title) {
        List<Question> questions = questionDao
            .findRandomQuestionsByCategory(
                category,
                numberOfQuestions
            );

        if (questions.isEmpty())
            throw new RuntimeException("Not Found any Question!!!");

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return quizDao.save(quiz);
    }

    public Quiz createQuiz(Quiz quiz) {
        if (quiz == null)
            throw new QuizNotFoundException("Quiz not found!");

        if(quizDao.existsQuizByTitle(quiz.getTitle()))
            throw new QuizAlreadyExistsException(
                String.format(
                    "Question already exist. Question with id=%d " +
                        "have same title of question", quiz.getId()));

        return quizDao.save(quiz);
    }

    public Quiz changeExistingQuiz(Integer quizId, Quiz quiz) {
        if(!quizDao.existsById(quizId))
            throw new QuizNotFoundException(
                String.format("Quiz with id=%d not found!", quizId));

        return quizDao.save(quiz);
    }

    public Integer calculateScore(Integer id,
                                  List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findAllById(id);

        if (quiz.isEmpty())
            throw new QuizNotFoundException(String.format(
                "Quiz with id=%d not found!", id));

        List<Question> questions = quiz.get().getQuestions();
        int right = 0;
        int next = 0;

        for (Response resp : responses) {
            String response = resp.getScore();
            String correctAnswer = questions.get(next).getCorrectAnswer();

            if (response.equals(correctAnswer))
                right++;

            next++;
        }
        return right;
    }

    public void deleteQuiz(Integer id) {
        if (!quizDao.existsById(id)) {
            throw new QuizNotFoundException(String.format(
                "Question with id=%d doesn't exist)", id));
        }

        quizDao.deleteById(id);
    }
}
