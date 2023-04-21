package com.rtx.queueunderflow.dto;

import com.rtx.queueunderflow.entity.Answer;

import java.util.Date;
import java.util.List;

public class AnswerDTO {
    private String userFirstName;
    private String userLastName;
    private String questionTitle;
    private String content;
    private Date date;
    private String picture;
    private List<VoteDTO> votes;

    public AnswerDTO(String userFirstName, String userLastName, String questionTitle, String content, Date date, String picture, List<VoteDTO> votes) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.questionTitle = questionTitle;
        this.content = content;
        this.date = date;
        this.picture = picture;
        this.votes = votes;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
