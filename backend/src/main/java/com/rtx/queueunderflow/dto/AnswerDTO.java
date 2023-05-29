package com.rtx.queueunderflow.dto;

import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.entity.Vote;

import java.util.List;

public class AnswerDTO {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userPicture;
    private Long questionId;
    private String questionTitle;
    private String content;
    private String date;
    private String picture;
    private List<Vote> votes;
    private Double userScore;

    public AnswerDTO(Long id, Long userId, String userFirstName, String userLastName, String userPicture, Long questionId, String questionTitle, String content, String date, String picture, List<Vote> votes) {
        this.id = id;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPicture = userPicture;
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.content = content;
        this.date = date;
        this.picture = picture;
        this.votes = votes;
    }

    public AnswerDTO(Answer answer) {
        this.id = answer.getAnswerId();
        this.userId = answer.getUser().getUserId();
        this.userFirstName = answer.getUser().getFirstName();
        this.userLastName =  answer.getUser().getLastName();
        this.userPicture = answer.getUser().getPicture();
        this.questionId = answer.getQuestion().getQuestionId();
        this.questionTitle = answer.getQuestion().getTitle();
        this.content = answer.getContent();
        this.date = answer.getDate().toString();
        this.picture = answer.getPicture();
        this.votes = answer.getVotes();
        this.userScore = computeUserScore(answer.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    private Double computeUserScore(User user) {
        Double score = 0.0;
        for (Question question : user.getQuestions()) {
            for(Vote vote : question.getVotes()) {
                score += vote.isPositiveVote() ? 2.5 : -1.5;
            }
        }
        for (Answer answer : user.getAnswers()) {
            for(Vote vote : answer.getVotes()) {
                score += vote.isPositiveVote() ? 5 : -2.5;
            }
        }
        score -= (double)(user.getAnswerVotes().stream().filter(vote -> !vote.isPositiveVote()).count()) * 1.5;
        return score;
    }
}
