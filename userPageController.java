import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class userPageController extends MainController implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) { }
    //information for chosen book
    @FXML Label bookTitle;
    @FXML Label bookIsbn;
    @FXML Label bookAuthor;
    @FXML Label bookPublisher;
    @FXML Label bookRating;
    @FXML Label bookPublishedDate;

    //log out forme the account and back to basic page
    @FXML
    void logOut1() throws Exception{
        // library.logUserOut();
        Parent parent = FXMLLoader.load((new File(INITIAL_FXML_FILE)).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
    @FXML void goToDashBord() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\dashboard.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
}
