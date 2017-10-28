package com.shepherdjerred.twister.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

// http://www.parcelabler.com/
public class User implements Parcelable {
    private String username;
    private String about;
    private List<Twist> twists;

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

    protected User(Parcel in) {
        username = in.readString();
        about = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(about);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}