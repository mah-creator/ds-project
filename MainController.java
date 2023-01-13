import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController implements Initializable{
    static final String INITIAL_FXML_FILE = ".\\gui\\login.fxml";
    private Library library;
    private Book[] bookList;
    private Stage primaryStage;

    @FXML
    GridPane bookGridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        library = new Library();
        bookList  = library.getBookList();

        primaryStage = App.primaryStage;
    }

    @FXML
    public void listBooksForDashboard(){
        int currentRow = 1;
        for (Book book : bookList) {
            bookGridPane.addRow(currentRow, 
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
    
    @FXML
    public void logOut() throws Exception{
        library.logUserOut();
        Parent parent = FXMLLoader.load((new File(INITIAL_FXML_FILE)).toURI().toURL());
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
                    bookGridPane.getChildren().set(6*(BuyButton.this.rowIndex+1) - 1, ownedLabel);
                    GridPane.setConstraints(ownedLabel, 5, rowIndex);
                    library.add(bookList[BuyButton.this.rowIndex - 1]);
                }
            });
        }
    }
}