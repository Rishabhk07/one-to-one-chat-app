package com.example.rishabhkhanna.letsknown.models;

/**
 * Created by rishabhkhanna on 22/08/16.
 */
public class User {

    String name;
    String email;
    String uid;
    String password;

    public User() {
    }



    public User(String name, String email, String uid , String password) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.password = password;
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }


}
