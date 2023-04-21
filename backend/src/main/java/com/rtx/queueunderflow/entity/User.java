package com.rtx.queueunderflow.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="e_mail")
    private String eMail;

    @Column(name="pass")
    private String password;

    @Column(name="phone")
    private String phone;

    @Column(name="picture")
    private String picture = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";

    @Column(name="moderator")
    private boolean moderator;

    @Column(name="banned")
    private boolean banned;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public User() {
    }

    public User(Long userId, String firstName, String lastName, String eMail, String password, String phone, String picture, boolean moderator, boolean banned) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.password = password;
        this.phone = phone;
        this.picture = picture;
        this.moderator = moderator;
        this.banned = banned;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void replaceFields(User user) {
        if (user.getFirstName() != null) {
            this.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            this.setLastName(user.getLastName());
        }
        if (user.geteMail() != null) {
            this.seteMail(user.geteMail());
        }
        if (user.getPassword() != null) {
            this.setPassword(user.getPassword());
        }
        if (user.getPhone() != null) {
            this.setPhone(user.getPhone());
        }
        if (user.getPicture() != null) {
            this.setPicture(user.getPicture());
        }
        if (user.isModerator() != this.isModerator()) {
            this.setModerator(user.isModerator());
        }
        if (user.isBanned() != this.isBanned()) {
            this.setBanned(user.isBanned());
        }
    }
}
