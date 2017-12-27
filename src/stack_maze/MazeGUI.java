package stack_maze;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Graphical User Interface (GUI) which allows a user to import a maze file and see 
 * its graphical representation unsolved. Also, allows the user to visually
 * display the path of the solution, if one exists, on the graphical maze.
 * 
 * @author Jesus R. Mendoza
 * @see    MazePane
 * @see    Maze
 */
public class MazeGUI extends BorderPane 
{
    /**
     * File chooser that allows the user to search their directory and select a file.
     */
    private FileChooser fc;
    
    /**
     * The instance of {@code Maze} which this GUI will display.
     */
    private MazePane maze;
    
    /**
     * Keeps track of whether or not this maze has been solved.
     */
    private boolean solved = false;
    
    /**
     * Constructs the GUI with all of its components.
     */
    public MazeGUI() {
        fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Text File", "*.txt"));
        setUp();
    }
    
    /**
     * Sets up the components on this GUI. Includes buttons, labels, and the
     * {@code MazePane}.
     */
    private void setUp() 
    {
        VBox lbls = new VBox(4);
        Label info = new Label("Choose a Maze file. (.txt)");
        Label warning1 = new Label("WARNING: Do not use very large maze files, attempt at your own risk.");
        Label warning2 = new Label("           Use console, --> Driver.java <-- for large files instead.");
        lbls.getChildren().addAll(info, warning1, warning2);
        HBox btnPanel = new HBox(50);
        btnPanel.setPadding(new Insets(20,20,50,20));
            
        Button btnSolve = new Button("Solve");        
        btnSolve.getStyleClass().add("button");
        btnSolve.setOnAction(e -> { 
            solved = maze.showSolution(); 
            if(solved) {
                info.setText("You found a Solution! Now let's try another file!");
                btnSolve.setDisable(true);
            }
            else {
                info.setText("No Solutions Found! Now let's try another file!");
                btnSolve.setDisable(true);
            }
        });
        btnSolve.setOnMouseEntered(e -> {
                btnSolve.getStyleClass().add("hovered");
        });
        btnSolve.setOnMouseExited(e -> {
            btnSolve.getStyleClass().remove("hovered");
        });
        
        Button btnInput = new Button("Input");
        btnInput.getStyleClass().add("button");
        btnInput.setOnMouseEntered(e -> {
            btnInput.getStyleClass().add("hovered");
        });
        btnInput.setOnMouseExited(e -> {
            btnInput.getStyleClass().remove("hovered");
        });
        btnInput.setOnAction(e -> {
            File f = fc.showOpenDialog(null);
            if(f != null) {
                Maze m = FileParser.parse(f);
                if(m != null) {
                    maze = new MazePane(m);
                    setCenter(maze);
                    btnSolve.setDisable(false);
                    info.setText("Click the SOLVE button to find a solution!");
                }
                else
                    info.setText("The file you tried to open was invalid.");
            }
        }); 
        
        btnSolve.setDisable(true);
        btnPanel.getChildren().addAll(btnInput, btnSolve, lbls);
        setBottom(btnPanel);
    }
}
