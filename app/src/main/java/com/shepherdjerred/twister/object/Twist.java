package com.shepherdjerred.twister.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Twist implements Parcelable {
    private int id;
    private String username;
    private String message;
    private Date timestamp;

    public static final Parcelable.Creator<Twist> CREATOR = new Parcelable.Creator<Twist>() {
        @Override
        public Twist createFromParcel(Parcel in) {
            return new Twist(in);
        }

        @Override
        public Twist[] newArray(int size) {
            return new Twist[size];
        }
    };

    public Twist(int id, String username, String message, Date timestamp) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    private Twist(Parcel in) {
        id = in.readInt();
        username = in.readString();
        message = in.readString();
        long tmpTimestamp = in.readLong();
        timestamp = tmpTimestamp != -1 ? new Date(tmpTimestamp) : null;
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

    @Override
    public String toString() {
        return "Twist{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(message);
        dest.writeLong(timestamp != null ? timestamp.getTime() : -1L);
    }
}
