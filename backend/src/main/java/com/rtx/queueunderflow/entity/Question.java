package com.rtx.queueunderflow.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="posts")
public class Question extends Post {
    @ElementCollection
    @CollectionTable(
            name="list_of_tags",
            joinColumns = @JoinColumn(name="question_id")
    )
    @Column(name="tag")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(
            name="list_of_answers",
            joinColumns = @JoinColumn(name="question_id")
    )
    @Column(name="answer_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Long> answers;

    public Question() {
    }

    public Question(long questionId, long userId, String title, String content, Date date, String picture, List<String> tags, List<Long> answers, List<Vote> votes) {
        super(questionId, userId, title, content, date, picture, true, votes);
        this.tags = tags;
        this.answers = answers;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Long> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Long> answers) {
        this.answers = answers;
    }

    public void replaceNullFields(Question question) {
        super.replaceNullFields(new Post(question.getPostId(), question.getUserId(), question.getTitle(), question.getContent(), question.getDate(), question.getPicture(), question.isQuestion(), question.getVotes()));
        if (this.tags == null) {
            this.tags = question.getTags();
        }
        if (this.answers == null) {
            this.answers = question.getAnswers();
        }
    }
}
