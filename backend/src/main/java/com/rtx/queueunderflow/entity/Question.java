package com.rtx.queueunderflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="questions")
@JsonIgnoreProperties({"user", "answers"})
public class Question {
    @Id
    @Column(name="question_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long questionId;

    @Column(name="title")
    private String title;

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
            name="list_of_tags",
            joinColumns = @JoinColumn(name="question_id")
    )
    @Column(name="tag")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> tags;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    @ElementCollection
    @CollectionTable(
            name="question_votes",
            joinColumns = @JoinColumn(name="question_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name="userId", column=@Column(name="user_id")),
            @AttributeOverride(name="positiveVote", column=@Column(name="positive_vote"))
    })
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Vote> votes;

    public Question() {
    }

    public Question(Long questionId, String title, User user, String content, Date date, String picture, List<String> tags, List<Answer> answers, List<Vote> votes) {
        this.questionId = questionId;
        this.title = title;
        this.user = user;
        this.content = content;
        this.date = date;
        this.picture = picture;
        this.tags = tags;
        this.answers = answers;
        this.votes = votes;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void replaceFields(Question question) {
        if (question.getTitle() != null) {
            this.title = question.getTitle();
        }
        if (question.getContent() != null) {
            this.content = question.getContent();
        }
        if (question.getDate() != null) {
            this.date = question.getDate();
        }
        if (question.getPicture() != null) {
            this.picture = question.getPicture();
        }
        if (question.getTags() != null) {
            this.tags = question.getTags();
        }
        if (question.getVotes() != null) {
            this.votes = question.getVotes();
        }
    }
}
