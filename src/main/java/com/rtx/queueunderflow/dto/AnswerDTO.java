package com.rtx.queueunderflow.dto;

import java.util.Date;
import java.util.List;

public class AnswerDTO extends PostDTO {
    public AnswerDTO(String userFirstName, String userLastName, String title, String content, Date date, String picture, List<VoteDTO> votes) {
        super(userFirstName, userLastName, title, content, date, picture, votes);
    }
}
