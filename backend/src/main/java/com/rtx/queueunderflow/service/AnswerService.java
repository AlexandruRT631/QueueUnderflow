package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.dto.AnswerDTO;
import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<AnswerDTO> retrieveAnswers() {
        return ((List<Answer>)answerRepository.findAll()).stream().map(AnswerDTO::new).toList();
    }

    public AnswerDTO retrieveAnswerByID(Long answerId) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        return answer.map(AnswerDTO::new).orElse(null);
    }

    public Answer saveAnswer(Answer answer) {
        answer.setVotes(new ArrayList<>());
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Optional<Answer> newAnswer = answerRepository.findById(answer.getAnswerId());
        if (newAnswer.isPresent()) {
            Answer oldAnswer = newAnswer.get();
            answer.replaceFields(oldAnswer);
            return answerRepository.save(answer);
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
