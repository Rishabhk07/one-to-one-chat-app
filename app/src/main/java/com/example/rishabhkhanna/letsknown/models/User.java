package com.example.rishabhkhanna.letsknown.models;

/**
 * Created by rishabhkhanna on 22/08/16.
 */
public class User {

    String name;
    String email;
    String uid;
    String password;
    String status;

    public User() {
    }



    public User(String name, String email, String uid , String password) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.password = password;
        this.status = "Hey there !! i am using whatsapp";
    }

    public String getPassword() {
        return password;
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
