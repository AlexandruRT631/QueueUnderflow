package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.*;
import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/insertUser")
    @ResponseBody
    public User insertUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<UserDTO> retrieveUsers() {
        return userService.retrieveUsers().stream().map(user -> new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPicture(),
                user.isModerator(),
                user.isBanned(),
                user.getQuestions().stream().map(question -> new QuestionDTO(
                        question.getQuestionId(),
                        null,
                        null,
                        null,
                        null,
                        question.getTitle(),
                        question.getContent(),
                        question.getDate().toString(),
                        question.getPicture(),
                        question.getVotes().stream().map(vote -> new VoteDTO(
                                userService.retrieveUserByID(vote.getUserId()).getFirstName(),
                                userService.retrieveUserByID(vote.getUserId()).getLastName(),
                                vote.isPositiveVote()
                        )).toList(),
                        null,
                        question.getTags()
                )).toList(),
                user.getAnswers().stream().map(answer -> new AnswerDTO(
                        answer.getAnswerId(),
                        null,
                        null,
                        null,
                        null,
                        answer.getQuestion().getQuestionId(),
                        answer.getQuestion().getTitle(),
                        answer.getContent(),
                        answer.getDate().toString(),
                        answer.getPicture(),
                        answer.getVotes().stream().map(vote -> new VoteDTO(
                                userService.retrieveUserByID(vote.getUserId()).getFirstName(),
                                userService.retrieveUserByID(vote.getUserId()).getLastName(),
                                vote.isPositiveVote()
                        )).toList()
                )).toList()
        )).toList();
    }

    @GetMapping("/getById/{user_id}")
    @ResponseBody
    public UserDTO retrieveById(@PathVariable Long user_id) {
        User user = userService.retrieveUserByID(user_id);
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPicture(),
                user.isModerator(),
                user.isBanned(),
                user.getQuestions().stream().map(question -> new QuestionDTO(
                        question.getQuestionId(),
                        null,
                        null,
                        null,
                        null,
                        question.getTitle(),
                        question.getContent(),
                        question.getDate().toString(),
                        question.getPicture(),
                        question.getVotes().stream().map(vote -> new VoteDTO(
                                userService.retrieveUserByID(vote.getUserId()).getFirstName(),
                                userService.retrieveUserByID(vote.getUserId()).getLastName(),
                                vote.isPositiveVote()
                        )).toList(),
                        null,
                        question.getTags()
                )).toList(),
                user.getAnswers().stream().map(answer -> new AnswerDTO(
                        answer.getAnswerId(),
                        null,
                        null,
                        null,
                        null,
                        answer.getQuestion().getQuestionId(),
                        answer.getQuestion().getTitle(),
                        answer.getContent(),
                        answer.getDate().toString(),
                        answer.getPicture(),
                        answer.getVotes().stream().map(vote -> new VoteDTO(
                                userService.retrieveUserByID(vote.getUserId()).getFirstName(),
                                userService.retrieveUserByID(vote.getUserId()).getLastName(),
                                vote.isPositiveVote()
                        )).toList()
                )).toList());
    }

    @GetMapping("/getById")
    @ResponseBody
    public UserDTO retrieveById1(@RequestParam("user_id") Long user_id) {
        User user = userService.retrieveUserByID(user_id);
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPicture(),
                user.isModerator(),
                user.isBanned(),
                user.getQuestions().stream().map(question -> new QuestionDTO(
                        question.getQuestionId(),
                        null,
                        null,
                        null,
                        null,
                        question.getTitle(),
                        question.getContent(),
                        question.getDate().toString(),
                        question.getPicture(),
                        question.getVotes().stream().map(vote -> new VoteDTO(
                                userService.retrieveUserByID(vote.getUserId()).getFirstName(),
                                userService.retrieveUserByID(vote.getUserId()).getLastName(),
                                vote.isPositiveVote()
                        )).toList(),
                        null,
                        question.getTags()
                )).toList(),
                    user.getAnswers().stream().map(answer -> new AnswerDTO(
                        answer.getAnswerId(),
                        null,
                        null,
                        null,
                        null,
                        answer.getQuestion().getQuestionId(),
                        answer.getQuestion().getTitle(),
                        answer.getContent(),
                        answer.getDate().toString(),
                        answer.getPicture(),
                        answer.getVotes().stream().map(vote -> new VoteDTO(
                                userService.retrieveUserByID(vote.getUserId()).getFirstName(),
                                userService.retrieveUserByID(vote.getUserId()).getLastName(),
                                vote.isPositiveVote()
                        )).toList()
                )).toList()
        );
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        User newUser = userService.retrieveUserByID(user.getUserId());
        newUser.replaceFields(user);
        return userService.saveUser(newUser);
    }

    @DeleteMapping("/deleteById/{user_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long user_id) {
        return userService.deleteById(user_id);
    }
}
