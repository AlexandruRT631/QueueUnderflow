package com.rtx.queueunderflow.dto;

import java.util.Date;
import java.util.List;

public class QuestionDTO extends PostDTO{
    private List<AnswerDTO> answers;
    private List<String> tags;

    public QuestionDTO(String userFirstName, String userLastName, String title, String content, Date date, String picture, List<VoteDTO> votes, List<AnswerDTO> answers, List<String> tags) {
        super(userFirstName, userLastName, title, content, date, picture, votes);
        this.answers = answers;
        this.tags = tags;
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
}
