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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    @FXML
    TextField loginUserNameField;

    @FXML
    PasswordField loginPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        library = new Library();
        bookList  = library.getBookList();

        // get the stage from where it was created (the javafx.application.Application class)
        primaryStage = App.primaryStage;
    }

    // list all of the books in BookDatabase into the dashboardBookGrid
    @FXML
    public void listBooksForDashboard(){
        int currentRow = 1;
        for (Book book : bookList) {
            dashboardBookGrid.addRow(currentRow, 
                                new Label_18(book.getTitle()), 
                                new Label_18(book.getAuther()), 
                                new Label_18(book.getPublisher()), 
                                new Label_18(book.getPublishDate()), 
                                new Label_18(String.format("%.2f", book.getRating())), 
                                new BuyButton(currentRow)
                                );
            currentRow++;
        }
    } 
    
    // logs out the user from the library system
    // then sets the scene to the first login page
    @FXML
    public void logOut() throws Exception{
        library.logUserOut();
        Parent parent = FXMLLoader.load((new File(INITIAL_FXML_FILE)).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }

    @FXML
    public void logIn() throws Exception{
        library.logUserIn(loginUserNameField.getText(), loginPasswordField);
    }

    @FXML
    public void goToSignUpPage() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\signup.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }

    /**
     * Label class of font size 18
     */
    class Label_18 extends Label{
        Label_18(String text){
            super(text);
            setFont(new Font(18));
        }
    }
    
    /**
     * Button class dedicated to buy stuff
     */
    class BuyButton extends Button{
        private int rowIndex;
        BuyButton(int rowIndex){
            super("Buy");
            this.rowIndex = rowIndex;
            
            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Label_18 ownedLabel = new Label_18("Owned");

                    // remove the "Buy" button and replace it with the label "Owned" after purchasing a book
                    dashboardBookGrid.getChildren().set(6*(BuyButton.this.rowIndex+1) - 1, ownedLabel);
                    GridPane.setConstraints(ownedLabel, 5, rowIndex);

                    // add the purchased book to the corrosponding user (activeUser in the Library class)
                    library.add(bookList[BuyButton.this.rowIndex - 1]);
                }
            });
        }
    }
}