package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXListCell;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class NewsFeedPageController {

    public ArrayList<Post> posts;
    public static int token;
    public static boolean isShown;

    @FXML
    private FontAwesomeIconView HomeIcon;

    @FXML
    private FontAwesomeIconView JobIcon;

    @FXML
    private FontAwesomeIconView MessageIcon;

    @FXML
    private FontAwesomeIconView NotifIcon;

    @FXML
    private VBox VBoxPosts;

    @FXML
    private ImageView profilePotho;

    @FXML
    private Button startAPostBTN;

    @FXML
    void goToHomePage(MouseEvent event) {

    }

    @FXML
    void goToJobPage(MouseEvent event) {
        Post post = new Post();
        post.setText(
                "salam man fateme hastam . in barname baraye darse ap man tahie shode . man 18 sal sen daram . man dar karaj zendigi mi konam");
        posts = new ArrayList<>();
        posts.add(post);
        Request request = new Request(token, RequestType.GET_USER);
            User user = null;
                try {
                    user = (new ObjectMapper().readValue(request.getResponseData(), new TypeReference<User>() {}));
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
        user.setFirstName("Fateme");
        user.setLastName("Ghanbari");
        request = new Request(user, RequestType.UPDATE_USER);
        System.out.println(user.getFirstName() + " is updated");
        Integer id = (Integer) user.getId();
        post.setWriter(id.toString());
        post.setDate(LocalDateTime.now().toString());
        refresh();
    }

    @FXML
    void goToMessagePage(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomePageController.class.getResource("MessagePage.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Post(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomePageController.class.getResource("AddPostPage" + ".fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            isShown = false;
            stage.show();
            refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void refresh() {
        token = WelcomePageController.token;
        for (Post post : posts) {
            addPostToVBox(post);
        }
    }

    private void addPostToVBox(Post post) {
        Request request = new Request(token, RequestType.GET_USER);
            User writer = null;
                try {
                    writer = (new ObjectMapper().readValue(request.getResponseData(), new TypeReference<User>() {}));
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color : #ecf4fc");
        //vBox.setStyle("-fx-background-color : #ecf4fc");

        HBox hBox = new HBox();
        Image profile;
        try {
            profile = new Image(new FileInputStream(writer.getProfilePhoto()));
            ImageView profileView = new ImageView(profile);
            hBox.getChildren().add(profileView);
            HBox.setMargin(profileView, new Insets(10, 0, 0, 10));
            Text nametTxt = new Text(writer.getFirstName() + " " + writer.getLastName());
            TextFlow name = new TextFlow(nametTxt);
            hBox.getChildren().add(name);
            HBox.setMargin(name, new Insets(20, 0, 0, 10));
            //String Date = (post.getDate().getYear()).toString();
            //Label date = new Label(getString(post.getDate()));
            //HBox.setMargin(date, new Insets(20, 0, 0, 150));
            //hBox.getChildren().add(date);
            nametTxt.setFont(new Font(18.0));
            vBox.getChildren().add(hBox);
            Text text = new Text(post.getText());
            TextFlow postText = new TextFlow(text);
            vBox.getChildren().add(postText);
            VBox.setMargin(postText, new Insets(20, 0, 20, 30));
            text.setFont(new Font(15));
            HBox likeHBox = new HBox();
            JFXListCell<?> likeCell = creatCell("HEART" , "like");
            JFXListCell<?> commentCell = creatCell("COMMENT" , "comment");
            JFXListCell<?> repostCell = creatCell("REPLY" , "repost");
           
            // vBox.
            likeHBox.getChildren().add(likeCell);
            likeHBox.getChildren().add(commentCell);
            likeHBox.getChildren().add(repostCell);

            vBox.getChildren().add(likeHBox);
            VBoxPosts.getChildren().addAll(vBox);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public String getString(LocalDateTime date) {
        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();
        Integer houre = date.getHour();
        Integer minutes = date.getMinute();

        return year.toString() + "-" + month.toString() + "-" + day.toString() + "    " + houre.toString() + ":" + minutes.toString();
    }

    JFXListCell<?> creatCell(String glyphName , String iconName){
        JFXListCell<?> cell = new JFXListCell<>();
        FontAwesomeIconView likeIcon = new FontAwesomeIconView();
        likeIcon.setGlyphName(glyphName);
        cell.setGraphic(likeIcon);
        cell.setText(iconName);
        cell.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        cell.setPrefWidth(340.0);
        cell.setPrefHeight(70.0);
        cell.setStyle("-fx-background-color : #ecf4fc");
        cell.setAlignment(Pos.CENTER);
        return cell;
    }

    
    @FXML
    void goToNotifPage(MouseEvent event) {
    }

    @FXML
    void exitHomeIcon(MouseEvent event) {
        HomeIcon.setFill(Paint.valueOf("#838282"));
    }

    @FXML
    void exitJobIcon(MouseEvent event) {
        JobIcon.setFill(Paint.valueOf("#838282"));

    }

    @FXML
    void exitMessageIcon(MouseEvent event) {
        MessageIcon.setFill(Paint.valueOf("#838282"));

    }

    @FXML
    void exitNotifIcon(MouseEvent event) {
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

}
