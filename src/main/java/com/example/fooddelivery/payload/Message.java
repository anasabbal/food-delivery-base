package com.example.fooddelivery.payload;



public class Message {
    private String message;

    public Message() {
    }

    public Message(String message) {
        super();
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
