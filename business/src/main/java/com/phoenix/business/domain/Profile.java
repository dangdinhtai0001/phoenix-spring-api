package com.phoenix.business.domain;

public class Profile {
    private String name;
    private String code;
    private String gender;

    public enum ProfileType {
        STUDENT, TEACHER;
    }

    public Profile() {
    }

    public Profile(String name, String code, String gender) {
        this.name = name;
        this.code = code;
        this.gender = gender;
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
}
