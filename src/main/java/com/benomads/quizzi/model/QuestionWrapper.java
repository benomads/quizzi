package com.benomads.quizzi.model;

import com.benomads.quizzi.entity.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionWrapper {

    private Long questionWrapperId;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public static QuestionWrapper toModel(Question question) {
        QuestionWrapper model = new QuestionWrapper();
        model.setQuestionWrapperId(question.getId());
        model.setQuestionTitle(question.getQuestionTitle());
        model.setOption1(question.getOption1());
        model.setOption2(question.getOption2());
        model.setOption3(question.getOption3());
        model.setOption4(question.getOption4());

        return model;
    }
}
