package com.rtx.queueunderflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="posts")
public class Answer extends Post {
    public Answer() {
    }

    public Answer (long answerId, long userId, String title, String content, Date date, String picture, List<Vote> votes) {
        super(answerId, userId, title, content, date, picture, false, votes);
    }

    public void replaceNullFields(Answer answer) {
        super.replaceNullFields(new Post(answer.getPostId(), answer.getUserId(), answer.getTitle(), answer.getContent(), answer.getDate(), answer.getPicture(), answer.isQuestion(), answer.getVotes()));
    }
}
