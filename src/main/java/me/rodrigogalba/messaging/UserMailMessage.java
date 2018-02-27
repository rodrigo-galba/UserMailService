package me.rodrigogalba.messaging;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class UserMailMessage {

    @NotBlank
    private String recipient;
    private List<String> bccRecipients;
    private String sender;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public List<String> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(List<String> bccRecipients) {
        this.bccRecipients = bccRecipients;
    }

    @Override
    public String toString() {
        return "UserMailMessage{" +
                "recipient='" + recipient + '\'' +
                ", bccRecipients=" + bccRecipients +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
