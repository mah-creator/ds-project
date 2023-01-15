import javafx.stage.Stage;

public class MainController{
    // the initial fxml file to be loaded into the programme
    static final String INITIAL_FXML_FILE = ".\\gui\\TestLoginController.fxml";

    protected Library library = App.library;

    // get the stage from where it was created (the javafx.application.Application class)
    protected Stage primaryStage = App.primaryStage;   

    protected Book[] getSortedBooks(SortCriteria sortCriteria){
        return library.getSortedBooks(sortCriteria);
    }
}
