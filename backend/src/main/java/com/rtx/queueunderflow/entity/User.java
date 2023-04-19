package com.rtx.queueunderflow.entity;

import jakarta.persistence.*;

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

    public void replaceNullFields(User user) {
        if (this.firstName == null) {
            this.firstName = user.getFirstName();
        }
        if (this.lastName == null) {
            this.lastName = user.getLastName();
        }
        if (this.eMail == null) {
            this.eMail = user.geteMail();
        }
        if (this.password == null) {
            this.password = user.getPassword();
        }
        if (this.phone == null) {
            this.phone = user.getPhone();
        }
        if (this.picture == null) {
            this.picture = user.getPicture();
        }
        if (!this.moderator) {
            this.moderator = user.isModerator();
        }
        if (!this.banned) {
            this.banned = user.isBanned();
        }

    }
}
