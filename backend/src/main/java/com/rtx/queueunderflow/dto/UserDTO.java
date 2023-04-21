package com.rtx.queueunderflow.dto;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String picture;
    private boolean moderator;
    private boolean banned;

    public UserDTO(String firstName, String lastName, String picture, boolean moderator, boolean banned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.moderator = moderator;
        this.banned = banned;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
