package com.example.rishabhkhanna.letsknown.models;

/**
 * Created by rishabhkhanna on 24/08/16.
 */
public class chatsmessages {

    String name;
    String message;
    String timestamp;

    public chatsmessages() {
    }

    public chatsmessages(String name, String timestamp, String message) {
        this.name = name;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
