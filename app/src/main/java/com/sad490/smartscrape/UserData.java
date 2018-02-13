package com.sad490.smartscrape;

/**
 * Created by sad490 on 2/2/18.
 */

public class UserData {
    private String username;
    private String password;
    private String Email;
    private boolean logined;

    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
        this.Email = "";
        this.logined = false;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setLoginStatus(boolean status) {
        this.logined = status;
    }

    public boolean isLogined() {
        return this.logined;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
