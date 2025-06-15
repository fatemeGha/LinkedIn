package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public static Request request;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("WelcomePage" + ".fxml"));
        fxmlLoader.setController(new WelcomePageController());
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        


    }

    public static void main(String[] args) throws IOException {
        launch();
    }

}