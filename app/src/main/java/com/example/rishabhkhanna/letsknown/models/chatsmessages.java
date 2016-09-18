package com.example.rishabhkhanna.letsknown.models;

import java.util.Map;

/**
 * Created by rishabhkhanna on 24/08/16.
 */
public class chatsmessages {

//    String name;
//    String email;
//    String uid;
//    String message;
//    Map<String,String> timestamp;

    String sender;
    String receiver;
    String message;

    public chatsmessages() {
    }

    public chatsmessages(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //    public chatsmessages(String name, String email, String uid, String message, Map<String, String> timestamp) {
//        this.name = name;
//        this.email = email;
//        this.uid = uid;
//        this.message = message;
//        this.timestamp = timestamp;
//    }
//
//    public chatsmessages() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getUid() {
//        return uid;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public Map<String, String>  getTimestamp() {
//        return timestamp;
//    }
}
