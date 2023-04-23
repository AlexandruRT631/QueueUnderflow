package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.AnswerDTO;
import com.rtx.queueunderflow.dto.QuestionDTO;
import com.rtx.queueunderflow.dto.VoteDTO;
import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.service.AnswerService;
import com.rtx.queueunderflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;

    @PostMapping("/insertAnswer")
    @ResponseBody
    public Answer insertAnswer(@RequestBody Answer answer) {
        answer.setVotes(new ArrayList<>());
        return answerService.saveAnswer(answer);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<AnswerDTO> retrieveQuestions() {
        return answerService.retrieveAnswers().stream().map(answer -> new AnswerDTO(
                answer.getAnswerId(),
                answer.getUser().getUserId(),
                answer.getUser().getFirstName(),
                answer.getUser().getLastName(),
                answer.getUser().getPicture(),
                answer.getQuestion().getQuestionId(),
                answer.getQuestion().getTitle(),
                answer.getContent(),
                answer.getDate().toString(),
                answer.getPicture(),
                answer.getVotes().stream().map(vote -> new VoteDTO(userService.retrieveUserByID(vote.getUserId()).getFirstName(), userService.retrieveUserByID(vote.getUserId()).getLastName(), vote.isPositiveVote())).toList()
        )).toList();
    }

    @GetMapping("/getById/{answer_id}")
    @ResponseBody
    public AnswerDTO retrieveById(@PathVariable Long answer_id) {
        Answer answer = answerService.retrieveAnswerByID(answer_id);
        return new AnswerDTO(
                answer.getAnswerId(),
                answer.getUser().getUserId(),
                answer.getUser().getFirstName(),
                answer.getUser().getLastName(),
                answer.getUser().getPicture(),
                answer.getQuestion().getQuestionId(),
                answer.getQuestion().getTitle(),
                answer.getContent(),
                answer.getDate().toString(),
                answer.getPicture(),
                answer.getVotes().stream().map(vote -> new VoteDTO(userService.retrieveUserByID(vote.getUserId()).getFirstName(), userService.retrieveUserByID(vote.getUserId()).getLastName(), vote.isPositiveVote())).toList()
        );
    }

    @PutMapping("/updateAnswer")
    @ResponseBody
    public Answer updateAnswer(@RequestBody Answer answer) {
        Answer newAnswer = answerService.retrieveAnswerByID(answer.getAnswerId());
        newAnswer.replaceFields(answer);
        return answerService.saveAnswer(newAnswer);
    }

    @DeleteMapping("/deleteById/{answer_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long answer_id) {
        return answerService.deleteById(answer_id);
    }
}
