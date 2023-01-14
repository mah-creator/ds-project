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
    // the number off attributes that will be displayed in the dashboard of the programme
    static final int BOOK_ATTRIBUTES_TO_PREVIEW = 5;

    // a difinition of how the attributes are ordered
    static final int TITLE = 0;
    static final int AUTHOR = 1;
    static final int PUBLISHER = 2;
    static final int PUBLUSH_DATE = 3;
    static final int RATING = 4;

    // the grid which lists the books to the user
    @FXML
    GridPane dashboardBookGrid;
    
    // a TextField where you search for a book using it's name
    @FXML
    TextField searchBar;

    // the pane that encapsulates the dashboardBookGrid
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
                    searchABookForSearchBar(searchBar.getText());
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
            // dont list the book at dashboard
            // if the logged-in user owns it
            if (userHasBook(book)) continue;

            // gets the attributes listed above
            Label_18[] tempBookAttributes = new Label_18[BOOK_ATTRIBUTES_TO_PREVIEW];
            tempBookAttributes[TITLE] = new Label_18(book.getTitle());
            tempBookAttributes[AUTHOR] = new Label_18(book.getAuther());
            tempBookAttributes[PUBLISHER] = new Label_18(book.getAuther());
            tempBookAttributes[PUBLUSH_DATE] = new Label_18(book.getPublishDate());
            tempBookAttributes[RATING] = new Label_18(String.format(".2f", book.getRating()));

            // adds the listed attributes 
            dashboardBookGrid.addRow(currentRow, tempBookAttributes);
            // plus a BuyButton alingside the attributes
            dashboardBookGrid.add(new BuyButton(currentRow), BOOK_ATTRIBUTES_TO_PREVIEW+1, currentRow);
        }
    }

    // checks with the Library if the logged-in user already has this book 
    private boolean userHasBook(Book book){
        for (Book book2 : library.getUserBooksList()) {
            if(book.equals(book2)) return true;
        }
        return false;
    }

    // search the book grid for a book with a specified name
    private void searchABookForSearchBar(String bookName){
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

    // defines several attributes for a transition animation object
    // to be used later on
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