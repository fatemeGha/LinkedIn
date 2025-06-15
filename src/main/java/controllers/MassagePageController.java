package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MassagePageController {

    @FXML
    private Button Start;

    @FXML
    private VBox VBoxMessages;

    @FXML
    private ImageView profilePotho;

    @FXML
    private TextFlow textFlowMassage;

    ArrayList<Text> texts;

    @FXML
    void Start(ActionEvent event) {
        texts = new ArrayList<>();
        Text text1 = new Text("salam");
        Text text2 = new Text("man fateme ghanbari hastam . man dar karaj zendegi mi konam . esm pedar man ali ast . man do khahar daram . esm khahar kuchek tar man arzie ast");
        texts.add(text1);
        texts.add(text2);
        text1.setFont(Font.font("Verdana", 15));
        text2.setFont(Font.font("Verdana", 15));
        textFlowMassage.setPadding(new Insets(20 , 20 , 20 , 20)); 
        textFlowMassage.getChildren().addAll(texts);
        creatMassage("man ham marzie hastam . esm khahar man fateme ast . au besyar mehraban ast");
        creatMassage("che jaleb! vaghean nemidunestam");
    }

    void creatMassage(String textString){
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Text text = new Text(textString);
        text.setFont(Font.font("Verdana", 15));

        try {
            ImageView imageView = new ImageView();
            Image image = new Image(new FileInputStream("defaultAvatar.jpg"));
            imageView.setImage(image);
            TextFlow textFlow = new TextFlow();
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(textFlow);
            textFlow.setPadding(new Insets(20 , 20 , 20 , 20));
            HBox.setMargin(imageView , new Insets(0 , 10 , 0 , 10));
            HBox.setMargin(textFlow , new Insets(0 , 20 , 0 , 10));
            profilePotho.setImage(image);
            textFlow.getChildren().add(text);
            vBox.getChildren().add(hBox);
            textFlow.setStyle("-fx-background-color: #ebebeb");
            textFlow.setStyle("-fx-background-radius: 5");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        VBoxMessages.getChildren().add(vBox);
        

    }


}
