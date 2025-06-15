package controllers;
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
import javafx.stage.Stage;
import model.User;

public class JoinPageController {

    @FXML
    private Label emailErorLBL;

    @FXML
    private TextField emailText;

    @FXML
    private Label firsNameErorLBL;

    @FXML
    private TextField firstNameText;

    @FXML
    private Button joinBTN;

    @FXML
    private Label lastNameErorLBL;

    @FXML
    private TextField lastNameText;

    @FXML
    private Label passErorLBL;

    @FXML
    private PasswordField passText;

    @FXML
    private Label repeatPassErorLBL;

    @FXML
    private PasswordField repeatPassText;

    @FXML
    private Button signUpBTN;


    @FXML
    void join(ActionEvent event) {
        emailErorLBL.setVisible(false);
        passErorLBL.setVisible(false);
        repeatPassErorLBL.setVisible(false);
        firsNameErorLBL.setVisible(false);
        lastNameErorLBL.setVisible(false);
        if(!model.User.checkFirstNameLength(firstNameText.getText())){
            firsNameErorLBL.setText("enter a shorter name");
            firsNameErorLBL.setVisible(true);
        }
        else if(!model.User.checkLastNameLength(lastNameText.getText())){
            lastNameErorLBL.setText("enter a shorter name");
            lastNameErorLBL.setVisible(true);
        }
        else if(!model.User.checkEmail(emailText.getText())){
            emailText.setText("Email format is wrong");
            emailErorLBL.setVisible(true);
        }
        else if(passText.getText().compareTo(repeatPassText.getText()) != 0){
            repeatPassErorLBL.setText("Passwords are not the same");
            repeatPassErorLBL.setVisible(true);
        }
        else if(firstNameText.getText() == null){
            firsNameErorLBL.setText("please enter your first name");
            firsNameErorLBL.setVisible(true);
        }
        else if(lastNameText.getText() == null){
            lastNameErorLBL.setText("please enter your last name");
            lastNameErorLBL.setVisible(true);
        }
        else if(emailText.getText() == null){
            emailText.setText("please enter your email");
            emailErorLBL.setVisible(true);
        }
        /*else if(emailText.getText()){

        }*/
        
        /*else if(!(model.User.checkPass(passText.getText()))){
            passErorLBL.setText("password must have letters and numbers");
            passErorLBL.setVisible(true);
        }*/
        else if(!model.User.checkPassLength(passText.getText())){
            passErorLBL.setText("enter a longer password .");
            passErorLBL.setVisible(true);
        }
        else{

        User user = new User();
        user.setEmail(emailText.getText());
        user.setFirstName(firstNameText.getText());
        user.setLastName(lastNameText.getText());
        user.setPassword(passText.getText());
        App.request.creatUser(user);

    }
        
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomePageController.class.getResource("WelcomePage.fxml"));
            fxmlLoader.setController(new WelcomePageController());
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}




