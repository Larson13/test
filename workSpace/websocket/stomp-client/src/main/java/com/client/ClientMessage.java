package com.client;

public class ClientMessage {
    private String from;
    private String message;

    public ClientMessage() {
    }

    public ClientMessage(String from, String text) {
        this.from = from;
        this.message = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "ClientMessage{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String text) {
        this.message = text;
    }
}
