package com.example.rishabhkhanna.letsknown.models;

/**
 * Created by rishabhkhanna on 24/08/16.
 */
public class UserChats {

    String email;
    String uid;
    String uName;
    String profilepic;

    public UserChats() {
    }

    public UserChats(String email, String uid, String uName, String profilepic) {
        this.email = email;
        this.uid = uid;
        this.uName = uName;
        this.profilepic = profilepic;
    }

    public String getuName() {
        return uName;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

}
