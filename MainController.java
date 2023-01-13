import javafx.stage.Stage;

public class MainController{
    // the initial fxml file to be loaded into the programme
    static final String INITIAL_FXML_FILE = ".\\gui\\login.fxml";

    private Library library = new Library();
    private Book[] bookList = library.getBookList();

    // get the stage from where it was created (the javafx.application.Application class)
    private Stage primaryStage = App.primaryStage;
}
