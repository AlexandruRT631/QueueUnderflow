package com.rtx.queueunderflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name="answer_votes")
public class AnswerVotes {
    @Id
    @Column(name="answer_id")
    private Long answerId;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="positive_vote")
    private boolean positiveVote;

    public AnswerVotes() {
    }

    public AnswerVotes(Long answerId, User user, boolean positiveVote) {
        this.answerId = answerId;
        this.user = user;
        this.positiveVote = positiveVote;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPositiveVote() {
        return positiveVote;
    }

    public void setPositiveVote(boolean positiveVote) {
        this.positiveVote = positiveVote;
    }
}
