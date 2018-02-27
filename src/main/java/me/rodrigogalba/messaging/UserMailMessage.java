package me.rodrigogalba.messaging;

public class UserMailMessage {
    private String body;

    public UserMailMessage(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
