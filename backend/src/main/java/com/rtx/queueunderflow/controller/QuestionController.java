package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.AnswerDTO;
import com.rtx.queueunderflow.dto.QuestionDTO;
import com.rtx.queueunderflow.dto.VoteDTO;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.service.AnswerService;
import com.rtx.queueunderflow.service.QuestionService;
import com.rtx.queueunderflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/questions")
@CrossOrigin
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
        question.setVotes(new ArrayList<>());
        question.setAnswers(new ArrayList<>());
        return questionService.saveQuestion(question);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<QuestionDTO> retrieveQuestions() {
        return questionService.retrieveQuestions().stream().map(question ->
            new QuestionDTO(
                    question.getQuestionId(),
                    question.getUser().getUserId(),
                    question.getUser().getFirstName(),
                    question.getUser().getLastName(),
                    question.getUser().getPicture(),
                    question.getTitle(),
                    question.getContent(),
                    question.getDate().toString(),
                    question.getPicture(),
                    question.getVotes().stream().map(vote -> new VoteDTO(userService.retrieveUserByID(vote.getUserId()).getFirstName(), userService.retrieveUserByID(vote.getUserId()).getLastName(), vote.isPositiveVote())).toList(),
                    question.getAnswers().stream().map(answer ->
                        new AnswerDTO(
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
                    )).toList(),
                    question.getTags()
        )).toList();
    }

    @GetMapping("/getById/{question_id}")
    @ResponseBody
    public QuestionDTO retrieveQuestionById(@PathVariable Long question_id) {
        Question question = questionService.retrieveQuestionById(question_id);
        if (question == null) {
            return null;
        }
        List<AnswerDTO> answers = question.getAnswers().stream().map(answer -> {
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
        }).toList();
        List<String> tags = question.getTags();
        List<VoteDTO> votes = question.getVotes().stream().map(vote -> new VoteDTO(userService.retrieveUserByID(vote.getUserId()).getFirstName(), userService.retrieveUserByID(vote.getUserId()).getLastName(), vote.isPositiveVote())).toList();
        return new QuestionDTO(question.getQuestionId(), question.getUser().getUserId(), question.getUser().getFirstName(), question.getUser().getLastName(), question.getUser().getPicture(), question.getTitle(), question.getContent(), question.getDate().toString(), question.getPicture(), votes, answers, tags);
    }

    @PutMapping("/updateQuestion")
    @ResponseBody
    public Question updateQuestion(@RequestBody Question question) {
        Question oldQuestion = questionService.retrieveQuestionById(question.getQuestionId());
        question.replaceFields(oldQuestion);
        return questionService.saveQuestion(question);
    }

    @DeleteMapping("/deleteById/{question_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long question_id){
        return questionService.deleteById(question_id);
    }
}
