package com.shepherdjerred.twister.object;

import java.util.Date;

public class Twist {
    private int id;
    private String username;
    private String message;
    private Date timestamp;

    public Twist(int id, String username, String message, Date timestamp) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
