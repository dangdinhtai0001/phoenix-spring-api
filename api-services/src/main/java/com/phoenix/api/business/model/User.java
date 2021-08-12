package com.phoenix.api.business.model;


import com.phoenix.api.core.annotation.BusinessObject;
import com.phoenix.api.core.annotation.BusinessObjectField;

import java.util.Date;

@BusinessObject
public class User {
    @BusinessObjectField(table = "fw_user", column = "id", alias = "fu")
    private Long id;

    @BusinessObjectField(table = "profile", column = "name", alias = "p")
    private String name;

    @BusinessObjectField(table = "profile", column = "date_of_birth", alias = "p")
    private Date dateOfBirth;

    @BusinessObjectField(table = "profile", column = "gender", alias = "p")
    private String gender;

    @BusinessObjectField(table = "profile", column = "phone_number", alias = "p")
    private String phoneNumber;

    @BusinessObjectField(table = "profile", column = "avatar", alias = "p")
    private String avatar;

    @BusinessObjectField(table = "fw_user", column = "username", alias = "fu")
    private String username;

    @BusinessObjectField(table = "fw_user", column = "password", alias = "fu")
    private String password;

    public User() {
    }

    public User(Long id, String name, Date dateOfBirth, String gender, String phoneNumber, String avatar,
                String username, String password) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
