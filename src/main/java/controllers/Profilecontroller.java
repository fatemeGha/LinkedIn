
package controllers;

import model.dao.UserDAO;
import model.dao.UserDAOImpl;
import model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.DatabaseMetaData;

public class Profilecontroller {

    @FXML
    private TextField nameField;

    @FXML
    private TextField locationField;

    @FXML
    private ImageView profileImageView;

    private UserDAOImpl userDAO;
    private Stage stage;

    public Profilecontroller() {
        MySQLConnector.makeConnection();
        this.userDAO = new UserDAOImpl(MySQLConnector.getConnection());
    }

    @FXML
    private void initialize() {
        loadUserProfile();
        profileImageView.setOnMouseClicked(event -> handleChangeProfileImage());
    }

    @FXML
    private void handleAddProfileSection() {
        openDialog("/path/to/add to profile.fxml", "Add Profile Section");
    }

    @FXML
    private void handleContactInfo() {
        openDialog("/path/to/edit contact info.fxml", "Edit Contact Info");
    }

    @FXML
    private void handleEditIntro() {
        openDialog("/path/to/Edit info.fxml", "Edit Intro");
    }

    private void openDialog(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            showAlert("Error", "Failed to open dialog.");
            e.printStackTrace();
        }
    }

    private void loadUserProfile() {
        int userId = getCurrentUserId();
        User user = userDAO.getUserById(userId);

        if (user != null) {
            nameField.setText(user.getFirstName() + user.getLastName());
            locationField.setText(user.getLocation());
            profileImageView.setImage(new Image(user.getProfilePhoto()));
        } else {
            showAlert("Error", "User not found.");
        }
    }

    private void handleChangeProfileImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                Path destination = new File("path/to/save/images/" + selectedFile.getName()).toPath();
                Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                profileImageView.setImage(new Image(destination.toUri().toString()));
            } catch (Exception e) {
                showAlert("Error", "Failed to upload image.");
                e.printStackTrace();
            }
        }
    }

    private int getCurrentUserId() {
        return 4; // Placeholder
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
