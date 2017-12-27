package stack_maze;

import java.io.File;
import java.util.Scanner;

/**
 * Provides alternative way of solving Mazes, as in allows you to use large mazes
 * since it does not need to render a GUI to show the maze. Uses console to input
 * a file (by typing in the file path) and prints out the first solution to the 
 * maze, if one exists.
 * 
 * @author Jesus R. Mendoza
 */
public class Driver {
    
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a File path for your maze (.txt file)");
        String path = scan.nextLine();
        
        File f = new File(path);
        
        Maze m = FileParser.parse(f); 
        if(m != null) {
            m.solve();
            m.printSolution();
        }
    }
    
}
