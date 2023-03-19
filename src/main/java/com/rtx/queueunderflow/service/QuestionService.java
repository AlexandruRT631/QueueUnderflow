package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.repository.AnswerRepository;
import com.rtx.queueunderflow.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    public List<Question> retrieveQuestions() {
        return (List<Question>) questionRepository.findByQuestionIsTrue();
    }

    public Question retrieveQuestionById(Long questionId) {
        Optional<Question> question = questionRepository.findByPostIdAndQuestionIsTrue(questionId);
        if (question.isPresent()) {
            return question.get();
        } else {
            return null;
        }
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public String deleteById(Long questionId) {
        Optional<Question> question = questionRepository.findByPostIdAndQuestionIsTrue(questionId);
        if (question.isPresent()) {
            try {
                for (Long answerId : question.get().getAnswers()) {
                    answerRepository.deleteById(answerId);
                }
                questionRepository.deleteById(questionId);
                return "Success";
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        } else {
            return "Failed";
        }
    }
}
