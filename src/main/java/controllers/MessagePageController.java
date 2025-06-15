
package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListCell;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;

public class MessagePageController {

    private ArrayList<User> users;

    @FXML
    private FontAwesomeIconView HomeIcon;

    @FXML
    private FontAwesomeIconView JobIcon;

    @FXML
    private JFXListCell<?> ListCell1;

    @FXML
    private FontAwesomeIconView MessageIcon;

    @FXML
    private FontAwesomeIconView NotifIcon;

    @FXML
    private VBox VBoxSearch;

    @FXML
    private TextField textFieldSearch;

    @FXML
    void clickOnSearchField(MouseEvent event) {
        users = new ArrayList<>();
        textFieldSearch.setText("");
        User user1 = new User();
        user1.setFirstName("Fateme");
        user1.setLastName("Ghanbari");
        user1.setProfilePhoto("defaultAvatar.jpg");
        users.add(user1);
        for(User user : users){
            addToList(user);
        }
    }

    void addToList(User user){
        JFXListCell<?> cell = new JFXListCell<>();
        
        Image image;
        try {
            image = new Image(new FileInputStream(user.getProfilePhoto()));
            cell.setGraphic(new ImageView(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        cell.setText(user.getFirstName() + " " + user.getLastName());
        cell.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        cell.setPrefWidth(340.0);
        cell.setPrefHeight(70.0);
        cell.setStyle("-fx-background-radius: 5");
        VBoxSearch.getChildren().add(cell);


    }



     @FXML
    void exitHomeIcon(MouseEvent  event) {
        HomeIcon.setFill(Paint.valueOf("#838282"));
    }

    @FXML
    void exitJobIcon(MouseEvent  event) {
        JobIcon.setFill(Paint.valueOf("#838282"));

    }

    @FXML
    void exitMessageIcon(MouseEvent  event) {
        MessageIcon.setFill(Paint.valueOf("#838282"));

    }

    @FXML
    void exitNotifIcon(MouseEvent  event) {
        NotifIcon.setFill(Paint.valueOf("#838282"));

    }



    @FXML
    void tuchHomeIcon(MouseEvent event) {
        HomeIcon.setFill(Paint.valueOf("#000000"));
    }

    @FXML
    void tuchJobIcon(MouseEvent event) {
        JobIcon.setFill(Paint.valueOf("#000000"));
    }

    @FXML
    void tuchMessageIcon(MouseEvent event) {
        MessageIcon.setFill(Paint.valueOf("#000000"));
    }

    @FXML
    void tuchNotifIcon(MouseEvent event) {
        NotifIcon.setFill(Paint.valueOf("#000000"));
    }


    @FXML
    void enterSearchField(MouseEvent event) {

    }

    @FXML
    void exitSearchFie(MouseEvent event) {

    }
}

