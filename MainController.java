import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class MainController implements Initializable{
    private int currentRow = 1;
    private Library library;
    private Book[] bookList;

    @FXML
    GridPane bookGridPane;

    @FXML
    public void listBooks(){
        for (Book book : bookList) {
            bookGridPane.addRow(currentRow++, 
                                new Label_18(book.getTitle()), 
                                new Label_18(book.getAuther()), 
                                new Label_18(book.getPublisher()), 
                                new Label_18(book.getPublishDate()), 
                                new Label_18(String.format("%.2f", book.getRating())), 
                                new BuyButton(currentRow)
                                );
        }
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
        int rowIndex;
        BuyButton(int rowIndex){
            super("Buy");
            this.rowIndex = rowIndex;
            
            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                }
            });
            
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        library = new Library();
        bookList  = library.getBookList();
    }
}