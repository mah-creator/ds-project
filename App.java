import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    static Stage primaryStage;
    static Library library;
    @Override
    public void start(Stage primaryStage) throws Exception {
        App.primaryStage = primaryStage;
        App.library = new Library();
        
        primaryStage.setMinWidth(800);
        Scene scene = new Scene(FXMLLoader.load((new File(MainController.INITIAL_FXML_FILE).toURI().toURL())));
        scene.getRoot().requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}