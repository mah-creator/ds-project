import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class userPageController extends MainController implements Initializable{
    private Book[] listOfBookUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfBookUser=library.getUserBooksList(); 
        listBooksForUser();
    }
    //information for chosen book
    @FXML Label bookTitle;
    @FXML Label bookAuthor;
    @FXML Label bookId;
    @FXML Label bookIsbn;
    @FXML Label bookPublisher;
    @FXML Label bookPublishedDate;
    @FXML Label bookRating;
    @FXML Label bookTotalPages;
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
    // back to Dashbord
    @FXML void goToDashBord() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\TestDController.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }

    @FXML 
    void Update() throws Exception{
        Parent parent = FXMLLoader.load((new File(".\\gui\\userpage.fxml")).toURI().toURL());
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
    //put the book the user in grid pane
    public  void listBooksForUser(){
        listOfBookUser=library.getUserBooksList();
        int currentRow = 1;
        for (Book book : listOfBookUser) {

            BookUser.addRow(currentRow, 
                new Label_20(book.getTitle()),
                new HBox( 
                    new viewButton(currentRow),
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
            setAlignment(Pos.CENTER);
            setTranslateX(20);
            setTranslateY(5);
            setStyle("-fx-background-color:red;-fx-border-width:2px;-fx-border-radius:5;-fx-border-color:#575757;");


            //code for Action
            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    library.removeBookFromeUser(listOfBookUser[deleteButton.this.rowIndex-1]);

                    // System.out.print(deleteButton.this.rowIndex);
                    // BookUser.getChildren().remove(deleteButton.this.rowIndex);
                }
            });
        }
    }
    
    class viewButton extends Button{
        private int rowIndex;
        viewButton(int rowIndex){
            super("view");
            this.rowIndex = rowIndex;
            setAlignment(Pos.CENTER);
            setTranslateX(8);
            setTranslateY(5);
            setStyle("-fx-background-color: #19f1b8;-fx-border-width:2px;-fx-border-radius:5;-fx-border-color:#575757;");
            setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    String[] dataForBook =listOfBookUser[viewButton.this.rowIndex-1].getAttributes();
                    putText(dataForBook);
                }
            });
            
        }
    }
    //put informatin for book in grid 
    private void putText(String[] dataForBook){
        for(int i=0;i<dataForBook.length;i++){
            switch(i){
                case  0: bookTitle.setText(dataForBook[i]);break;
                case  1: bookAuthor.setText(dataForBook[i]);break;
                case  2: bookId.setText(dataForBook[i]);break;
                case  3: bookIsbn.setText(dataForBook[i]);break;
                case  4: bookPublisher.setText(dataForBook[i]);break;
                case  5: bookPublishedDate.setText(dataForBook[i]);break;
                case  6: bookRating.setText(dataForBook[i]);break;
                case  7: bookTotalPages.setText(dataForBook[i]);break;

            }
        }

    }
    /**
     * Label class of font size 20
     */
    class Label_20 extends Label{
        Label_20(String text){
            super(text);
            setFont(new Font(20));
            setPadding(new Insets(5, 20, 5, 20));
        }
    }
    
}
