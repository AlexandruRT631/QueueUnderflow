package com.rtx.queueunderflow.dto;

import java.util.List;

public class UserDTOQA extends UserDTO{
    private List<QuestionDTO> questions;
    private List<AnswerDTO> answers;

    public UserDTOQA(String firstName, String lastName, String picture, boolean moderator, boolean banned, List<QuestionDTO> questions, List<AnswerDTO> answers) {
        super(firstName, lastName, picture, moderator, banned);
        this.questions = questions;
        this.answers = answers;
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
