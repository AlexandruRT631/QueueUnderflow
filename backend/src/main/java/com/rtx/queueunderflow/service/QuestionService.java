package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.dto.QuestionDTO;
import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.repository.AnswerRepository;
import com.rtx.queueunderflow.repository.QuestionRepository;
import com.rtx.queueunderflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    public List<QuestionDTO> retrieveQuestions(int page) {
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, (int) questionRepository.count());

        try {
            return ((List<Question>) questionRepository.findAll()).stream()
                    .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                    .map(QuestionDTO::new).toList().subList(start, end);
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<QuestionDTO> retrieveQuestionsByTag(String tag, int page) {
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end;

        try {
            List<QuestionDTO> questionDTO = ((List<Question>) questionRepository.findByTagsContaining(tag)).stream().map(QuestionDTO::new).toList();
            end = Math.min(start + pageSize, questionDTO.size());
            return questionDTO.subList(start, end);
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<QuestionDTO> retrieveQuestionsByTitle(String title, int page) {
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end;

        try {
            List<QuestionDTO> questionDTO = ((List<Question>) questionRepository.findByTitleContainingIgnoreCase(title)).stream().map(QuestionDTO::new).toList();
            end = Math.min(start + pageSize, questionDTO.size());
            return questionDTO.subList(start, end);
        }
        catch (Exception e) {
            return null;
        }
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

    public Question saveQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        Optional<User> userGet = userRepository.findById(questionDTO.getUserId());
        if (userGet.isPresent()) {
            question.setUser(userGet.get());
        } else {
            return null;
        }
        question.setContent(questionDTO.getContent());
        question.setDate(new Date());
        question.setPicture(questionDTO.getPicture());
        question.setTags(questionDTO.getTags());
        question.setVotes(new ArrayList<>());
        question.setAnswers(new ArrayList<>());
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        Optional<Question> oldQuestionGot = questionRepository.findById(question.getQuestionId());
        if (oldQuestionGot.isPresent()) {
            Question newQuestion = oldQuestionGot.get();
            newQuestion.replaceFields(question);
            return questionRepository.save(newQuestion);
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
