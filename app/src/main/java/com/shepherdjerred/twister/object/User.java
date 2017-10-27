package com.shepherdjerred.twister.object;

public class User {
    private String username;
    private String about;

    public User(String username, String about) {
        this.username = username;
        this.about = about;
    }

    public String getUsername() {
        return username;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
