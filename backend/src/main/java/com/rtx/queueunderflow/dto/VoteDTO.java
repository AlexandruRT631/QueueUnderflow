package com.rtx.queueunderflow.dto;

public class VoteDTO {
    private String userFirstName;
    private String userLastName;
    private boolean positiveVote;

    public VoteDTO(String userFirstName, String userLastName, boolean positiveVote) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.positiveVote = positiveVote;
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

    public boolean isPositiveVote() {
        return positiveVote;
    }

    public void setPositiveVote(boolean positiveVote) {
        this.positiveVote = positiveVote;
    }
}
