import javafx.stage.Stage;

public class MainController{
    // the initial fxml file to be loaded into the programme
    static final String INITIAL_FXML_FILE = ".\\gui\\login.fxml";

    protected Library library = App.library;
    protected  Book[][] bookListsSortedDifferently = App.bookListsSortedDifferently;

    // get the stage from where it was created (the javafx.application.Application class)
    protected Stage primaryStage = App.primaryStage;   

    protected Book[] getSortedBooks(int sortCriteria){
        return bookListsSortedDifferently[sortCriteria];
    }
}
