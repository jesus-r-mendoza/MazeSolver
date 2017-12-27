package stack_maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Un-initializable class used only to parse a file into a {@code Maze}.
 * 
 * @author Jesus R. Mendoza
 */
public class FileParser 
{
    /**
     * Cannot instantiate a {@code FileParser}.
     */
    private FileParser() {}
    
    /**
     * Parses a given file into a {@code Maze} and returns that {@code Maze}
     * object, otherwise returns null if some error occurred or the file could 
     * not be parsed.
     * 
     * @param  f the file
     * @return a {@code Maze} object if file was successfully parsed or
     *         null if file could not create a {@code Maze}
     */
    public static Maze parse(File f) 
    {
        try(Scanner reader = new Scanner(f)) 
        {
            int rows, cols, startRow, startCol;
            String line;
            String[] parts;
            Coor start;            
            
            line = reader.nextLine();
            parts = line.split(" ");
            rows = Integer.parseInt(parts[0]);
            cols = Integer.parseInt(parts[1]);
            
            line = reader.nextLine();
            parts = line.split(" ");
            startRow = Integer.parseInt(parts[0]);
            startCol = Integer.parseInt(parts[1]);
            start = new Coor(startRow, startCol);
                        
            char[][] map = new char[rows][cols];
            
            for(int i = 0; i < rows; i++) {
                line = reader.nextLine();
                map[i] = line.toCharArray();
            }            
            return new Maze(map,start);
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERR: File Not Found");
        } catch (NumberFormatException ex) {
            System.out.println("ERR: One of the first two rows of the file doesn't use numbers.");
        } 
        System.out.println("ERR: Maze from this file could not be created");
        System.out.println();
        return null;
    }
}
