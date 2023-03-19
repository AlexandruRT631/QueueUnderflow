package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.AnswerDTO;
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
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;

    @PostMapping("/insertAnswer")
    @ResponseBody
    public Answer insertAnswer(@RequestBody Answer answer) {
        answer.setQuestion(false);
        answer.setVotes(new ArrayList<>());
        return answerService.saveAnswer(answer);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Answer> retrieveQuestions() {
        return answerService.retrieveAnswers();
    }

    @GetMapping("/getById/{answer_id}")
    @ResponseBody
    public AnswerDTO retrieveById(@PathVariable Long answer_id) {
        Answer answer = answerService.retrieveAnswerByID(answer_id);
        List<VoteDTO> votes = answer.getVotes().stream().map(vote -> new VoteDTO(userService.retrieveUserByID(vote.getUserId()).getFirstName(), userService.retrieveUserByID(vote.getUserId()).getLastName(), vote.isPositiveVote())).toList();
        return new AnswerDTO(userService.retrieveUserByID(answer.getUserId()).getFirstName(), userService.retrieveUserByID(answer.getUserId()).getLastName(), answer.getTitle(), answer.getContent(), answer.getDate(), answer.getPicture(), votes);
    }

    @PutMapping("/updateAnswer")
    @ResponseBody
    public Answer updateAnswer(@RequestBody Answer answer) {
        Answer oldAnswer = answerService.retrieveAnswerByID(answer.getPostId());
        answer.replaceNullFields(oldAnswer);
        return answerService.saveAnswer(answer);
    }

    @DeleteMapping("/deleteById/{answer_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long answer_id) {
        return answerService.deleteById(answer_id);
    }
}
