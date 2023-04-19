package com.rtx.queueunderflow.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;

@MappedSuperclass
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long postId;

    @Column(name="user_id")
    private long userId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="creation_date")
    private Date date = new Date();

    @Column(name="picture")
    private String picture;

    @Column(name="question")
    private boolean question = true;

    @ElementCollection
    @CollectionTable(
            name="votes",
            joinColumns = @JoinColumn(name="post_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name="userId", column=@Column(name="user_id")),
            @AttributeOverride(name="positiveVote", column=@Column(name="positive_vote"))
    })
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Vote> votes;

    public Post() {
    }

    public Post(long postId, long userId, String title, String content, Date date, String picture, boolean question, List<Vote> votes) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.picture = picture;
        this.question = question;
        this.votes = votes;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public boolean isQuestion() {
        return question;
    }

    public void setQuestion(boolean question) {
        this.question = question;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void replaceNullFields(Post post) {
        if (this.getUserId() == 0) {
            this.setUserId(post.getUserId());
        }
        if (this.getTitle() == null) {
            this.setTitle(post.getTitle());
        }
        if (this.getContent() == null) {
            this.setContent(post.getContent());
        }
        if (this.getDate() == null) {
            this.setDate(post.getDate());
        }
        if (this.getPicture() == null) {
            this.setPicture(post.getPicture());
        }
        if (this.getVotes() == null) {
            this.setVotes(post.getVotes());
        }
    }
}
