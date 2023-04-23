package com.rtx.queueunderflow.dto;

import com.rtx.queueunderflow.entity.Answer;

import java.util.Date;
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
    private List<VoteDTO> votes;

    public AnswerDTO(Long id, Long userId, String userFirstName, String userLastName, String userPicture, Long questionId, String questionTitle, String content, String date, String picture, List<VoteDTO> votes) {
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

    public List<VoteDTO> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteDTO> votes) {
        this.votes = votes;
    }
}
