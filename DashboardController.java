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
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class DashboardController extends MainController implements Initializable{

    private SortCriteria sortCriteria;

    @FXML
    MenuButton sortMenuButton;

    @FXML
    void sortName(){listBooksInGrid(SortCriteria.BOOK_NAME);}
    @FXML
    void sortAuthor(){listBooksInGrid(SortCriteria.BOOK_AUTHOR);}
    @FXML
    void sortPublisher(){listBooksInGrid(SortCriteria.BOOK_PUBLISHER);}
    @FXML
    void sortPublishDate(){listBooksInGrid(SortCriteria.BOOK_PUBLISH_DATE);}
    @FXML
    void sortRating(){listBooksInGrid(SortCriteria.BOOK_RATING);}

    // the number off columns that will be displayed in the dashboard of the programme
    // i.e. all the sortable columns must be prewiewd
    // p.s. any (+1) is added to take in consideration the column of BuyButton 
    static final int PREVIEW_COLUMNS = SortCriteria.values().length;
    // all columns have the attribute-viewing columns
    // plus one extra column to add th BuyButton
    static final int TOTAL_COLUMNS = PREVIEW_COLUMNS + 1;


    // the grid which lists the books to the user
    @FXML
    GridPane bookGrid;
    
    // a TextField where you search for a book using it's name
    @FXML
    TextField searchBar;

    // the pane that encapsulates the book grid
    @FXML
    ScrollPane scrollPane;

    // a fede transition to be used later on
    private FadeTransition ft = new FadeTransition(Duration.millis(200));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listBooksInGrid(SortCriteria.BOOK_NAME);
    
        setUpFadeTransition();

        searchBar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    searchABookInGrid(searchBar.getText());
                }
            }
        });
    }

    // loades the user page when triggered
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

    // lists the books in the grid sorted based on a desired sort criteria
    private void listBooksInGrid(SortCriteria sortCriteria){
        this.sortCriteria = sortCriteria;

        bookGrid.getChildren().remove(TOTAL_COLUMNS, bookGrid.getChildren().size());
        
        sortMenuButton.setText(sortCriteria.sortLabel);
        
        int currentRow = 1;
        
        for (Book book : getSortedBooks(sortCriteria)) {
            // dont list the book at dashboard
            // if the logged-in user owns it
            if (userHasBook(book)) continue;

            Label_18[] tempAttributes = new Label_18[PREVIEW_COLUMNS];
            for (SortCriteria sortableColumn : SortCriteria.values()) {
                // set the value of the attribute we want to show
                // and extract it's value from the column defined for each attribute
                tempAttributes[sortableColumn.value] = new Label_18(book.getStringAttributes()[sortableColumn.corrospondingDatabaseColumn.value]);
                
            }

            // adds the listed attributes 
            // plus a BuyButton alingside the attributes
            bookGrid.addRow(currentRow, tempAttributes);
            bookGrid.add(new BuyButton(currentRow), PREVIEW_COLUMNS, currentRow);

            currentRow++;
        }
    }

    // checks with the Library if the logged-in user already has this book 
    private boolean userHasBook(Book book){
        for (Book booki : library.getActiveUserBooks()) {
            if(book.equals(booki)) return true;
        }
        return false;
    }

    // search the book grid for a book with a specified name
    private void searchABookInGrid(String bookName){
        for (int i = TOTAL_COLUMNS; i < bookGrid.getChildren().size() ; i+=TOTAL_COLUMNS) {
            Label tempLabel = (Label)bookGrid.getChildren().get(i);

            // if the book was found 
            // triger an animation to specify 
            // the book location on the screen
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
            
            setOnAction(buyHandler);
        }

        // handle the purchace process
        EventHandler<ActionEvent> buyHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_18 ownedLabel = new Label_18("Owned");
    
                library.add(getSortedBooks(sortCriteria)[rowIndex - 1]);
                
                // remove the "Buy" button and replace it with the label "Owned" after purchasing a book
                bookGrid.getChildren().set((TOTAL_COLUMNS)*(rowIndex+1) - 1, ownedLabel);
                GridPane.setConstraints(ownedLabel, PREVIEW_COLUMNS, rowIndex);
            }
        };
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