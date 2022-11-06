package com.example.onlinebookstore;

public class UsersItem {

    String userID;
    String userName;
    String userEmail;
    String userPrice;

    public UsersItem() {
    }

    public UsersItem(String userID, String userName, String userEmail, String userPrice) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPrice = userPrice;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(String userPrice) {
        this.userPrice = userPrice;
    }
}
