package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.dto.QuestionDTO;
import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.repository.AnswerRepository;
import com.rtx.queueunderflow.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    public List<QuestionDTO> retrieveQuestions() {
        return ((List<Question>) questionRepository.findAll()).stream().map(QuestionDTO::new).toList();
    }

    public List<QuestionDTO> retrieveQuestionsByTag(String tag) {
        return ((List<Question>) questionRepository.findByTagsContaining(tag)).stream().map(QuestionDTO::new).toList();
    }

    public QuestionDTO retrieveQuestionById(Long questionId) {
        Optional<Question> questionGot = questionRepository.findById(questionId);
        if (questionGot.isPresent()) {
            Question question = questionGot.get();
            return new QuestionDTO(question);
        } else {
            return null;
        }
    }

    public Question saveQuestion(Question question) {
        question.setVotes(new ArrayList<>());
        question.setAnswers(new ArrayList<>());
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        Optional<Question> oldQuestionGot = questionRepository.findById(question.getQuestionId());
        if (oldQuestionGot.isPresent()) {
            Question oldQuestion = oldQuestionGot.get();
            question.replaceFields(oldQuestion);
            return questionRepository.save(question);
        } else {
            return null;
        }
    }

    public String deleteById(Long questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            try {
                for (Answer answer : question.get().getAnswers()) {
                    answerRepository.deleteById(answer.getAnswerId());
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
