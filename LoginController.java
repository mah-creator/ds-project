import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends MainController implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    TextField userNameField;

    @FXML
    PasswordField passwordField;

    @FXML
    public void logIn() throws Exception{

        try {
            library.logUserIn(userNameField.getText(), passwordField.getText());
            Parent parent = FXMLLoader.load((new File(".\\gui\\dashboard.fxml")).toURI().toURL());
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();

            userNameField.setText("");
            passwordField.setText("");
            primaryStage.getScene().getRoot().requestFocus();
        }
    }

    @FXML
    public void goToSignUpPage() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\signup.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
}