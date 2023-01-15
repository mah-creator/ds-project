import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class signUpController extends MainController implements   Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {   }
    @FXML TextField lastName;
    @FXML TextField firstName;
    @FXML TextField Age;
    @FXML TextField email;
    @FXML TextField id;
    @FXML TextField password;
    @FXML Label errorMessage;

    @FXML
    public void goToUserPage() throws Exception{
        try {
            // chekFillText();
            library.addUser(
                Integer.parseInt(id.getText()),Integer.parseInt(Age.getText()),
                password.getText(),email.getText(),
                firstName.getText(), lastName.getText()
            );
            // library.logUserIn(password.getText(),email.getText());
            Parent parent = FXMLLoader.load((new File(".\\gui\\dashboard.fxml")).toURI().toURL());
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }
    }
    private void chekFillText(){
        if( (lastName.getText().isEmpty())||firstName.getText().isEmpty()||
            Age.getText().isEmpty()||email.getText().isEmpty()||
            password.getText().isEmpty()||id.getText().isEmpty()
        
        ){
            throw new IllegalStateException("Please, enter the required data");
        }
    }
    @FXML
    public void goToLogInPage() throws Exception{
        Parent parent = FXMLLoader.load((new File(MainController.INITIAL_FXML_FILE)).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }

}