package com.rtx.queueunderflow.dto;

import com.rtx.queueunderflow.entity.User;

import java.util.List;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String picture;
    private boolean moderator;
    private boolean banned;
    private List<QuestionDTO> questions;
    private List<AnswerDTO> answers;

    public UserDTO(Long id, String firstName, String lastName, String picture, boolean moderator, boolean banned, List<QuestionDTO> questions, List<AnswerDTO> answers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.moderator = moderator;
        this.banned = banned;
        this.questions = questions;
        this.answers = answers;
    }

    public UserDTO(User user) {
        this.id = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.picture = user.getPicture();
        this.moderator = user.isModerator();
        this.banned = user.isBanned();
        this.questions = user.getQuestions().stream().map(question -> {
            QuestionDTO questionDTO = new QuestionDTO(question);
            questionDTO.setAnswers(null);
            return questionDTO;
        }).toList();
        this.answers = user.getAnswers().stream().map(AnswerDTO::new).toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
