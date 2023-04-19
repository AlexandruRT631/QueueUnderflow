package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> retrieveAnswers() {
        return (List<Answer>) answerRepository.findByQuestionIsFalse();
    }

    public Answer retrieveAnswerByID(Long answerId) {
        Optional<Answer> answer = answerRepository.findByPostIdAndQuestionIsFalse(answerId);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            return null;
        }
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }


    public String deleteById(Long answerId) {
        Optional<Answer> answer = answerRepository.findByPostIdAndQuestionIsFalse(answerId);
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
