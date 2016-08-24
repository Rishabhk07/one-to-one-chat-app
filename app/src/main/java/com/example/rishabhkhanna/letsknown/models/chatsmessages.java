package com.example.rishabhkhanna.letsknown.models;

import java.util.Map;

/**
 * Created by rishabhkhanna on 24/08/16.
 */
public class chatsmessages {

    String name;
    String email;
    String uid;
    String message;
    String timestamp;

    public chatsmessages(String name, String email, String uid, String message, Map<String, String> timestamp) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.message = message;
        this.timestamp = timestamp;
    }

    public chatsmessages() {
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

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
