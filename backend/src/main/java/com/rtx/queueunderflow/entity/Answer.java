package com.rtx.queueunderflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="answers")
@JsonIgnoreProperties({"user", "question"})
public class Answer{
    @Id
    @Column(name="answer_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="content")
    private String content;

    @Column(name="creation_date")
    private Date date = new Date();

    @Column(name="picture")
    private String picture;

    @ElementCollection
    @CollectionTable(
            name="answer_votes",
            joinColumns = @JoinColumn(name="answer_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name="userId", column=@Column(name="user_id")),
            @AttributeOverride(name="positiveVote", column=@Column(name="positive_vote"))
    })
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Vote> votes;

    public Answer() {
    }

    public Answer(Long answerId, Question question, User user, String content, Date date, String picture, List<Vote> votes) {
        this.answerId = answerId;
        this.question = question;
        this.user = user;
        this.content = content;
        this.date = date;
        this.picture = picture;
        this.votes = votes;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void replaceFields(Answer answer) {
        if (answer.getContent() != null) {
            this.setContent(answer.getContent());
        }
        if (answer.getPicture() != null) {
            this.setPicture(answer.getPicture());
        }
        if (answer.getVotes() != null) {
            this.setVotes(answer.getVotes());
        }
    }
}
