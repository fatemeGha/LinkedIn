package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/// token ro baraye post moghe ijad nazashtam
public class Post {

    private String text;  //max 3000 Character
    private String hashtaks;
    private String likersId;
    private int numLikes;
    private int numComments;
    private String commentsId;
    private int id;
    private String date;
    private String writerId;
    private String token;
    private String image;
    private boolean updated;

    public Post() {
        this.numLikes = 0;
        this.numComments = 0;
        this.id = -1;

    }/* 
    public void addComment(Post comment){
        comments.add(comment);
    }*/
    public boolean getUpdated(){
        return updated;
    }
    public void setUpdated(boolean updated){
        this.updated = updated;
    }
    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }public String getImage() {
        return image;
    }public void setImage(String image) {
        this.image = image;
    }public String getWriter() {
        return writerId;
    }public void setWriter(String writer) {
        this.writerId = writer;
    }
    public String getComments() {
        return commentsId;
    }

    public void setComments(String comments) {
        this.commentsId = comments;
    }

    public String getHashtaks() {
        return hashtaks;
    }

    public void setHashtaks(String hashtaks) {
        this.hashtaks = hashtaks;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getLikers() {
        return likersId;
    }public void setLikers(String likers) {
        this.likersId = likers;
    }
}
