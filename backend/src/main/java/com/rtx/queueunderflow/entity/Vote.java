package com.rtx.queueunderflow.entity;

import jakarta.persistence.*;

@Embeddable
public class Vote {
    private long userId;
    private boolean positiveVote;

    public Vote() {
    }

    public Vote(long userId, boolean positiveVote) {
        this.userId = userId;
        this.positiveVote = positiveVote;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isPositiveVote() {
        return positiveVote;
    }

    public void setPositiveVote(boolean positiveVote) {
        this.positiveVote = positiveVote;
    }
}
