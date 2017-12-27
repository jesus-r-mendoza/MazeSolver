package stack_maze;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * The graphical representation of the provided {@code Maze}, and its solution 
 * if one exists.
 * 
 * @author Jesus R. Mendoza
 * @see    Maze
 */
public class MazePane extends GridPane 
{
    /**
     * The instance of {@code Maze} being represented by this pane.
     */
    private Maze maze;
    
    /**
     * The labels that make up the {@code Maze}, representing each location of 
     * the {@code Maze}.
     */
    private Label[][] lbls;    
    
    /**
     * Constructs a new {@code MazePane} depicting the provided {@code Maze}.
     * 
     * @param m the {@code Maze} which this {@code MazePane} will depict 
     */
    public MazePane(Maze m) {
        maze = m;
        setUpLabels(maze.getBoard());
    }
    
    /**
     * Sets up the labels, that will be displayed, based on the provided 
     * two-dimensional character array. 
     * 
     * @param map the character array to use for reference
     */
    private void setUpLabels(char[][] map) 
    {
        lbls = new Label[map.length][map[0].length];
        
        for(int j = 0; j < lbls.length; j++) {
            for(int k = 0; k < lbls[0].length; k++) {
                Label lab = new Label();
                setUpLabel(map, lab, j, k);
                lbls[j][k] = lab;
                this.add(lab, k, j);
            }
        }
    }
    
    /**
     * Sets up an individual label and applies the corresponding style, depending
     * on the reference array. Paints walls as black, movable paths as light blue,
     * starting and ending points as orange, and traversed path as red.
     * 
     * @param map the two-dimensional character reference array
     * @param l   the label which will be set up
     * @param row the row index to refer to in the reference array
     * @param col the column index to refer to in the reference array
     */
    private void setUpLabel(char[][] map, Label l, int row, int col) 
    {
        l.setPrefHeight(9);
        l.setPrefWidth(16);
        
        switch(map[row][col]) {
            case '1':                
                break; 
            case 'S':
                l.setText("S");
                l.getStyleClass().add("start");
                break;
            case 'E':
                l.setText("E");
                l.getStyleClass().add("end");
                break;
            case '0':   
                l.getStyleClass().add("wall");
                break;
            case '*':
                l.getStyleClass().add("path");
        }
    }
    
    /**
     * Checks whether or not there exists a solution, if yes then it depicts it. 
     * 
     * @return true if a solution exists, false otherwise
     */
    public boolean showSolution() 
    {
        char[][] solution = maze.solve();
        if(solution != null) {
            this.getChildren().clear();
            setUpLabels(solution);
            return true;
        }
        return false;
    }
}
