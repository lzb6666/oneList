package com.whu.onelist.vo;

public class User {
    private Long userID;
    private String phoneNum;
    private String email;

    public User(Long userID, String phoneNum, String email) {
        this.userID = userID;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
