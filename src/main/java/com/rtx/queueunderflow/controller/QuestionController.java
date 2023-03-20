package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.AnswerDTO;
import com.rtx.queueunderflow.dto.QuestionDTO;
import com.rtx.queueunderflow.dto.VoteDTO;
import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.service.AnswerService;
import com.rtx.queueunderflow.service.QuestionService;
import com.rtx.queueunderflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;

    @PostMapping("/insertQuestion")
    @ResponseBody
    public Question insertQuestion(@RequestBody Question question) {
        question.setQuestion(true);
        question.setVotes(new ArrayList<>());
        question.setAnswers(new ArrayList<>());
        return questionService.saveQuestion(question);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Question> retrieveQuestions() {
        return questionService.retrieveQuestions();
    }

    @GetMapping("/getById/{question_id}")
    @ResponseBody
    public QuestionDTO retrieveQuestionById(@PathVariable Long question_id) {
        Question question = questionService.retrieveQuestionById(question_id);
        if (question == null) {
            return null;
        }
        List<AnswerDTO> answers = question.getAnswers().stream().map(answerId -> {
            Answer answer = answerService.retrieveAnswerByID(answerId);
            List<VoteDTO> votes = answer.getVotes().stream().map(vote -> new VoteDTO(userService.retrieveUserByID(vote.getUserId()).getFirstName(), userService.retrieveUserByID(vote.getUserId()).getLastName(), vote.isPositiveVote())).toList();
            return new AnswerDTO(userService.retrieveUserByID(answer.getUserId()).getFirstName(), userService.retrieveUserByID(answer.getUserId()).getLastName(), answer.getTitle(), answer.getContent(), answer.getDate(), answer.getPicture(), votes);
        }).toList();
        List<String> tags = question.getTags();
        List<VoteDTO> votes = question.getVotes().stream().map(vote -> new VoteDTO(userService.retrieveUserByID(vote.getUserId()).getFirstName(), userService.retrieveUserByID(vote.getUserId()).getLastName(), vote.isPositiveVote())).toList();
        return new QuestionDTO(userService.retrieveUserByID(question.getUserId()).getFirstName(), userService.retrieveUserByID(question.getUserId()).getLastName(), question.getTitle(), question.getContent(), question.getDate(), question.getPicture(), votes, answers, tags);
    }

    @PutMapping("/updateQuestion")
    @ResponseBody
    public Question updateQuestion(@RequestBody Question question) {
        Question oldQuestion = questionService.retrieveQuestionById(question.getPostId());
        question.replaceNullFields(oldQuestion);
        return questionService.saveQuestion(question);
    }

    @DeleteMapping("/deleteById/{question_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long question_id){
        return questionService.deleteById(question_id);
    }
}
