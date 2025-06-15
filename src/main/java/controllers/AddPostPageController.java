package controllers;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;


public class AddPostPageController {

    public static int token;

    @FXML
    private Button addPostBTN;

    @FXML
    private Label erorLBL;

    @FXML
    private Label nameLBL;

    @FXML
    private TextArea postText;

    @FXML
    private ImageView profilePhoto;

    @FXML
    void addPost(ActionEvent event) {
        token = NewsFeedPageController.token;
        Post post = creatPost();
        Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        stage.close();

    }
    
    Post creatPost(){
        Post post = new Post();
        post.setText(postText.getText());
        post.setComments(null);
        post.setNumLikes(0);
        User user = App.request.getUser(token);
        post.setWriter(user);
        LocalDateTime date = LocalDateTime.now();
        post.setDate(date);
        user.getPosts().add(post);
        return post;
    }

}

