package model;

import java.time.LocalDateTime;

public class Massage {

    private String text;
    private User sender;
    private User reciever;
    private LocalDateTime date;
    private Massage replyToMassage;
    private String image;
    private int id;
    private String token;

    public Massage(){


    }
    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }public void setDate(LocalDateTime date) {
        this.date = date;
    }public void setImage(String image) {
        this.image = image;
    }public String getImage() {
        return image;
    }public User getReciever() {
        return reciever;
    }public void setReciever(User reciever) {
        this.reciever = reciever;
    }public Massage getReplyToMassage() {
        return replyToMassage;
    }public void setReplyToMassage(Massage replyToMassage) {
        this.replyToMassage = replyToMassage;
    }public User getSender() {
        return sender;
    }public void setSender(User sender) {
        this.sender = sender;
    }public void setText(String text) {
        this.text = text;
    }public String getText() {
        return text;
    }
    public String getToken() {
        return token;
    }public void setToken(String token) {
        this.token = token;
    }
}
