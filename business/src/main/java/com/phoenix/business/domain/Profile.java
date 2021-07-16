package com.phoenix.business.domain;

public class Profile {
    private String name;
    private String code;
    private String gender;
    private String account;
    private String password;
    private ProfileType type;

    public enum ProfileType {
        STUDENT, TEACHER;
    }

    public Profile() {
    }

    public Profile(String name, String code, String gender, String account, String password, ProfileType type) {
        this.name = name;
        this.code = code;
        this.gender = gender;
        this.account = account;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfileType getType() {
        return type;
    }

    public void setType(ProfileType type) {
        this.type = type;
    }
}
