package com.rtx.queueunderflow.dto;

import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.entity.Vote;

import java.util.Comparator;
import java.util.List;

public class QuestionDTO {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userPicture;
    private String title;
    private String content;
    private String date;
    private String picture;
    private List<Vote> votes;
    private List<AnswerDTO> answers;
    private List<String> tags;

    public QuestionDTO(Long id, Long userId, String userFirstName, String userLastName, String userPicture, String title, String content, String date, String picture, List<Vote> votes, List<AnswerDTO> answers, List<String> tags) {
        this.id = id;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPicture = userPicture;
        this.title = title;
        this.content = content;
        this.date = date;
        this.picture = picture;
        this.votes = votes;
        this.answers = answers.stream().sorted(Comparator.comparingInt(answer -> getVoteScore(((AnswerDTO)answer).getVotes())).reversed()).toList();
        this.tags = tags;
    }

    public QuestionDTO(Question question) {
        this.id = question.getQuestionId();
        this.userId = question.getUser().getUserId();
        this.userFirstName = question.getUser().getFirstName();
        this.userLastName = question.getUser().getLastName();
        this.userPicture = question.getUser().getPicture();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.date = question.getDate().toString();
        this.picture = question.getPicture();
        this.votes = question.getVotes();
        this.answers = question.getAnswers().stream().map(AnswerDTO::new).sorted(Comparator.comparingInt(answer -> getVoteScore(((AnswerDTO)answer).getVotes())).reversed()).toList();
        this.tags = question.getTags();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private int getVoteScore(List<Vote> votes) {
        int score = 0;
        for (Vote vote : votes) {
            score += vote.isPositiveVote() ? 1 : -1;
        }
        return score;
    }
}
