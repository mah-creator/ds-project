import java.awt.Color;
import java.beans.EventHandler;
import java.io.File;
import java.net.URL;
import java.sql.Array;
import java.util.List;
import java.util.ResourceBundle;

import javax.crypto.spec.GCMParameterSpec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class userPageController extends MainController implements Initializable{
    private Book[] listOfBookUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfBookUser=library.getUserBooksList(); 
        listBooksForUser();
    }
    //information for chosen book
    @FXML Label bookTitle;
    @FXML Label bookIsbn;
    @FXML Label bookAuthor;
    @FXML Label bookPublisher;
    @FXML Label bookRating;
    @FXML Label bookPublishedDate;

    /* the grid pane for book the user */
    @FXML GridPane BookUser;
    /*the grid for information to  specific book */
    @FXML GridPane bookDetails;
    // log out forme the account and back to basic page

    /*back to login page */
    @FXML 
    void logOut() throws Exception{
        Parent parent = FXMLLoader.load((new File(INITIAL_FXML_FILE)).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }

    //back to Dashbord
    @FXML void goToDashBord() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\dashboard.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }


    //put the book the user in grid pane
    private void listBooksForUser(){
        int currentRow = 1;
        for (Book book : library.getUserBooksList()) {

            BookUser.addRow(currentRow, 
                new Label_20(book.getTitle()),
                new HBox( 
                    new deleteButton(currentRow)
                )
            );
            currentRow++;
        }
    }
    class deleteButton extends Button{
        private int rowIndex;
        deleteButton(int rowIndex) {
            super("Delete");
            this.rowIndex=rowIndex;
            //code for Action

        }

    }

    class viewButton extends Button{
        private int rowIndex;
        viewButton(int rowIndex){
            super("Buy");
            this.rowIndex = rowIndex;
            
            //code for Action

        }
    }

    /**
     * Label class of font size 18
     */
    class Label_20 extends Label{
        Label_20(String text){
            super(text);
            setFont(new Font(20));
            setLayoutX(38);
            setLayoutY(125);
        }
    }
}
