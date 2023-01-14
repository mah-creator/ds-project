import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends MainController implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    @FXML
    Label massage;//tell the user if enterd error data

    @FXML
    TextField userNameField;

    @FXML
    PasswordField passwordField;

    @FXML
    public void goToSignUpPage() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\signup.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
    @FXML
    public void goToUserPage() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\userpage.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
    
    @FXML
    public void logIn() throws Exception{
        boolean loggedIn = library.logUserIn(userNameField.getText(), passwordField.getText());
        if(loggedIn){
            Parent parent = FXMLLoader.load((new File(".\\gui\\dashboard.fxml")).toURI().toURL());
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
        }
        else{
            massage.setText("Incorrect username or password");
            userNameField.setText("");
            passwordField.setText("");
            primaryStage.getScene().getRoot().requestFocus();
        }
    }
    
}