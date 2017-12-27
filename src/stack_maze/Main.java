package stack_maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creates the Graphical User Interface to input and solve {@code Maze}s.
 * 
 * @author Jesus R. Mendoza
 */
public class Main extends Application {
    
    @Override 
    public void start(Stage primaryStage)
    {
        MazeGUI root = new MazeGUI();
        
        Scene scene = new Scene(root, 700, 650);
        scene.getStylesheets().add("stack_maze/styles.css");
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
