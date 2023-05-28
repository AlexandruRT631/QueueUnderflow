package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.dto.AnswerDTO;
import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.repository.AnswerRepository;
import com.rtx.queueunderflow.repository.QuestionRepository;
import com.rtx.queueunderflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    public List<AnswerDTO> retrieveAnswers() {
        return ((List<Answer>)answerRepository.findAll()).stream().map(AnswerDTO::new).toList();
    }

    public AnswerDTO retrieveAnswerByID(Long answerId) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        return answer.map(AnswerDTO::new).orElse(null);
    }

    public Answer saveAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        Optional<User> userGet = userRepository.findById(answerDTO.getUserId());
        if (userGet.isPresent()) {
            answer.setUser(userGet.get());
        } else {
            return null;
        }
        Optional<Question> questionGet = questionRepository.findById(answerDTO.getQuestionId());
        if (questionGet.isPresent()) {
            answer.setQuestion(questionGet.get());
        } else {
            return null;
        }
        answer.setContent(answerDTO.getContent());
        answer.setDate(new Date());
        answer.setPicture(answerDTO.getPicture());
        answer.setVotes(new ArrayList<>());
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Optional<Answer> oldAnswerGot = answerRepository.findById(answer.getAnswerId());
        if (oldAnswerGot.isPresent()) {
            Answer newAnswer = oldAnswerGot.get();
            newAnswer.replaceFields(answer);
            return answerRepository.save(newAnswer);
        } else {
            return null;
        }
    }

    public String deleteById(Long answerId) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (answer.isPresent()) {
            try {
                answerRepository.deleteById(answerId);
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
