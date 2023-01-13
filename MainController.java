import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController implements Initializable{
    // the initial fxml file to be loaded into the programme
    static final String INITIAL_FXML_FILE = ".\\gui\\login.fxml";

    private Library library;
    private Book[] bookList;

    private Stage primaryStage;

    // the grid used at the dashboard to list all books
    @FXML
    GridPane dashboardBookGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        library = new Library();
        bookList  = library.getBookList();

        // get the stage from where it was created (the javafx.application.Application class)
        primaryStage = App.primaryStage;
    }
}