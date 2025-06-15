package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

public class WelcomePageController {

    @FXML
    private Label emailErorLBL;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button joinBTN;

    @FXML
    private Label passErorLBL;

    @FXML
    private PasswordField passTextField;

    @FXML
    private Button signInBTN;

    @FXML
    private ImageView welcomImage;

    public static int token;

    public WelcomePageController() {
        welcomImage = new ImageView();
        try {
            welcomImage.setImage(new Image(new FileInputStream("WelcomPage.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void join(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomePageController.class.getResource("JoinPage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void signIn(ActionEvent event) {
        if (emailTextField.getText() == null) {
            emailErorLBL.setText("Enter your email");
            emailErorLBL.setVisible(true);
        } else {
            
            Request request = new Request(emailTextField.getText(), RequestType.GET_USER_BY_EMAIL);
            User user = null;
                try {
                    user = (new ObjectMapper().readValue(request.getResponseData(), new TypeReference<User>() {}));
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            
        if (user == null) {
                emailErorLBL.setText("Invalid email! Join now");
                emailErorLBL.setVisible(true);
            } else if (passTextField.getText().compareTo(user.getPassword()) != 0) {
                passErorLBL.setText("Wrong password");
                passErorLBL.setVisible(true);
            } else {
                token = user.getId();

                creatUserBuDefault(user);
                try {
                    NewsFeedPageController.token = token;
                    FXMLLoader fxmlLoader = new FXMLLoader(WelcomePageController.class.getResource("NewsFeed.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    emailErorLBL.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void creatUserBuDefault(User user) {

        user.setProfilePhoto("defaultAvatar.jpg");

    }

}