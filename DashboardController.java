import java.io.File;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class DashboardController extends MainController implements Initializable{
    @FXML
    GridPane dashboardBookGrid;

    @FXML
    TextField searchBar;

    @FXML
    ScrollPane scrollPane;

    private FadeTransition ft = new FadeTransition(Duration.millis(200));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listBooksForDashboard();
    
        setUpFadeTransition();

        searchBar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    searchForBook(searchBar.getText());
                }
            }
        });
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
            if (userHasBook(book)) continue;
            
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
    private boolean userHasBook(Book book){
        for (Book book2 : library.getUserBooksList()) {
            if(book.equals(book2)) return true;
        }
        return false;
    }

    private void searchForBook(String bookName){
        for (int i = 6; i < dashboardBookGrid.getChildren().size() ; i+=6) {
            Label tempLabel = (Label)dashboardBookGrid.getChildren().get(i);

            if(tempLabel.getText().equalsIgnoreCase(bookName)){
                ft.setNode(tempLabel);
                scrollPane.setVvalue(tempLabel.getLayoutY()/primaryStage.getHeight());
                ft.play();
                break;
            }
        }
    }

    private void setUpFadeTransition(){
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(5);
        ft.setAutoReverse(true);
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
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage() + "\n");
                        e.printStackTrace();
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
