package stack_maze;

/**
 * A coordinate to represent a position in the maze, which is represented by a
 * two dimensional array. Serves to simplify the search, traversal, and 
 * allocation of a step in the Maze.
 * 
 * @author Jesus R. Mendoza
 */
public class Coor 
{
    /**
     * The x coordinate of this {@code Coor}, representative a the row position
     * in the two dimensional array representing the {@code Maze}.
     */
    protected final int x;
    
    /**
     * The y coordinate of this {@code Coor}, representative a the column position
     * in the two dimensional array representing the {@code Maze}.
     */
    protected final int y;
    
    /**
     * Constructs a {@code Coor} at the specified x-coordinate (row) and 
     * y-coordinate (column).
     * 
     * @param xCoor the x-coordinate (representing the row index)
     * @param yCoor the y-coordinate (representing the column index)
     */
    public Coor(int xCoor, int yCoor) {
        x = xCoor;
        y = yCoor;
    }
    
    /**
     * Returns the {@code String} representation of this {@code Coor} as a 
     * coordinate point format - (x, y).
     * 
     * @return the {@code String} representation of this {@code Coor}
     */
    @Override
    public String toString() {
        return " (" + x + ", " + y + ")";
    }
}
