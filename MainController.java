import javafx.stage.Stage;

public class MainController{
    // the initial fxml file to be loaded into the programme
    static final String INITIAL_FXML_FILE = ".\\gui\\login.fxml";

<<<<<<< HEAD
    protected Library library = new Library();
    protected Book[] bookList = library.getBookList();
=======
    protected Library library = App.library;
    protected  Book[] bookList = App.bookList;
>>>>>>> main

    // get the stage from where it was created (the javafx.application.Application class)
    protected Stage primaryStage = App.primaryStage;   
}
