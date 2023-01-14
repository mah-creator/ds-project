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

public class DashboardController extends MainController implements Initializable{
    @FXML
    GridPane dashboardBookGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listBooksForDashboard();
    }

    @FXML
    void goToUserPage() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\userpage.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
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

    private void listBooksForDashboard(){
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

                    try {
                        // add the purchased book to the corrosponding user (activeUser in the Library class)
                        library.add(bookList[BuyButton.this.rowIndex - 1]);
                        
                        // remove the "Buy" button and replace it with the label "Owned" after purchasing a book
                        dashboardBookGrid.getChildren().set(6*(BuyButton.this.rowIndex+1) - 1, ownedLabel);
                        GridPane.setConstraints(ownedLabel, 5, rowIndex);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                }
            });
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
    
}
