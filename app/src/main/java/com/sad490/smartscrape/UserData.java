package com.sad490.smartscrape;

import android.os.Parcel;
import android.os.Parcelable;

import com.sad490.smartscrape.NetWork.User;

/**
 * Created by sad490 on 2/2/18.
 */

public class UserData implements Parcelable{
    private String username;
    private String password;
    private String Email;
    private boolean logined;

    public UserData() {
        this.username = "";
        this.password = "";
        this.Email = "";
        this.logined = false;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(Email);
    }

    public UserData(String username, String password, String Email) {
        this.username = username;
        this.password = password;
        this.Email = "";
        this.logined = false;
    }

    public UserData(Parcel in) {
        username = in.readString();
        password = in.readString();
        Email = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return Email;
    }

    public void setLoginStatus(boolean status) {
        this.logined = status;
    }

    public boolean isLogined() {
        return this.logined;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public void setEmail( String email ) {
        this.Email = email;
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
