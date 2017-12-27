package stack_maze;

/**
 * A representation of the inputed Maze file, which uses three two-dimensional 
 * arrays to find a solution of itself. Uses a breadth search to find a solution,
 * since it checks all cardinal directions at each location and follows all paths
 * until one correct solution is found.
 * 
 * @author Jesus R. Mendoza
 */
public class Maze 
{
    /**
     * The original configuration of the {@code Maze}, represented as a 
     * two-dimensional character array.
     */
    private final char[][] board;
    
    /**
     * The deep copy of the original character array, except with the changes
     * to display the path.
     */
    private char[][] solution;
    
    /**
     * Two-dimensional array, with the same dimensions as this {@code Maze}, 
     * which helps connect the boolean array to the character array by holding
     * the index of each spot. Also, it simplifies the way the stack (which 
     * holds the path traversed) can identify its path.
     */
    private Coor[][] coordinates;
    
    /**
     * Two-dimensional array, with the same dimensions as this {@code Maze}, 
     * which keeps track of whether or not a spot has already been visited.
     */
    private boolean[][] visited;
    
    /**
     * Boolean value that represents whether the {@code Maze} has been completed.
     */
    private boolean done = false;
    
    /**
     * The number of rows in this {@code Maze}.
     */
    private final int rows;
    
    /**
     * The number of columns in this {@code Maze}.
     */
    private final int cols;
    
    /**
     * The number of total steps between the start (entrance) to the end (exit) 
     * of the {@code Maze}.
     */
    private int steps = 0;

    /**
     * The Stack that will store the positions of the path as the {@code Maze}
     * is traversed.
     */
    private Stack<Coor> path;
    
    /**
     * Coordinate to reference the current position as the {@code Maze} is 
     * traversed.
     */
    private Coor currPos;
    
    /**
     * Constructs a {@code Maze} given the character array that it will represent
     * and the starting positions (entrance to the maze).
     * 
     * @param map   the two-dimensional character array
     * @param start the starting position represented as a coordinate: {@code Coor}
     */
    public Maze(char [][] map, Coor start) 
    {
        board = map;
        currPos = start;
        rows = board.length;
        cols = board[0].length;
        path = new Stack();
        if(board != null && board.length != 0) 
        {
            coordinates = new Coor[rows][cols];
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    coordinates[i][j] = new Coor(i,j);
                }
            }            
            visited = new boolean[rows][cols];
        }
    }
    
    /**
     * Finds one solution of the {@code Maze} based on its original state. Returns
     * the character array of the solution that was found, otherwise returns
     * {@code null} is no solution was found.
     * 
     * @return the character array of the solution
     */
    public char[][] solve() 
    {
        char[][] copy = new char[rows][cols];
        for(int j = 0; j < rows; j++) {
            System.arraycopy(board[j], 0, copy[j], 0, cols);
        }        
        
        path.push(currPos);
        mark(currPos);
        while(!path.isEmpty() && !done) 
        {               
            if(topValid() && !visited[currPos.x - 1][currPos.y]){
                currPos = coordinates[currPos.x - 1][currPos.y]; 
                path.push(currPos);
                mark(currPos);
            }            
            else if(rightValid() && !visited[currPos.x][currPos.y +1]) {
                currPos = coordinates[currPos.x][currPos.y +1]; 
                path.push(currPos);
                mark(currPos);
            }
            else if(btmValid() && !visited[currPos.x + 1][currPos.y]) {
                currPos = coordinates[currPos.x + 1][currPos.y]; 
                path.push(currPos);
                mark(currPos);
            }
            else if(leftValid() && !visited[currPos.x][currPos.y -1]) {
                currPos = coordinates[currPos.x][currPos.y -1]; 
                path.push(currPos);
                mark(currPos);
            }
            else {
                path.pop();
                currPos = path.top();
            }
        }
        
        if(done) {            
            steps = path.size();
            while(!path.isEmpty()) {
                Coor temp = path.pop();
                copy[temp.x][temp.y] = '*';
            }            
            solution = copy;            
            return copy;
        }
        else 
            return null;          
    }
    
    /**
     * Marks the specified position as having been visited, by marking that 
     * position as true in the boolean array with the same dimensions as the maze.
     * 
     * @param pos the position to mark as visited
     */
    private void mark(Coor pos) {
        visited[pos.x][pos.y] = true;
    }    
    
    /**
     * Checks whether the next step to the top is in bounds and valid.
     * 
     * @return whether or not one step top is valid
     */
    private boolean topValid() {
        if(currPos.x - 1 >= 0)          
            return canMove(-1, 0);
        return false;
    }
    
    /**
     * Checks whether the next step to the bottom is in bounds and valid.
     * 
     * @return whether or not one step bottom is valid
     */
    private boolean btmValid() {
        if(currPos.x + 1 < rows) 
            return canMove(1, 0);        
        return false;
    }
    
    /**
     * Checks whether the next step to the left is in bounds and valid.
     * 
     * @return whether or not one step left is valid
     */
    private boolean leftValid() {
        if(currPos.y - 1 >= 0) 
            return canMove(0, -1);        
        return false;
    }   
    
    /**
     * Checks whether the next step to the right is in bounds and valid.
     * 
     * @return whether or not one step right is valid
     */
    private boolean rightValid() {
        if(currPos.y + 1 < cols) 
            return canMove(0, 1);        
        return false;
    } 
    
    /**
     * Checks whether the next step, in the specified cardinal direction, is valid.
     * Does this by adding or subtracting 1 from the horizontal (to move left or
     * right) or the vertical (to move up or down) component of the current 
     * position in the two dimensional array. This assumes that the movement is 
     * in bounds.
     * 
     * @param vert the vertical addition/subtraction to specify direction
     * @param horz the horizontal addition/subtraction to specify direction
     * @return 
     */
    private boolean canMove(int vert, int horz) {
        switch(board[currPos.x + vert][currPos.y + horz]) {
            case '1': return true;             
            case '0': return false;
            case 'S': return true;
            case 'E':
                done = true;
                return true;
            default:  return false;
        }
    }
    
    /**
     * Prints the solution (maze with path displayed) to the console.
     */
    public void printSolution() 
    {      
        System.out.println();
        if(solution != null) {
            for(int i = 0; i < solution.length; i++) {
                for(int j = 0; j < solution[0].length; j++) 
                    System.out.print(solution[i][j]);
                System.out.println();
            }
            System.out.println();
            System.out.println("Solution Found!");
            System.out.println();
            System.out.println("Steps Taken: " + steps);
            System.out.println();
        }
        else {
            System.out.println();
            System.out.println("No Solution Found");
            System.out.println();
        }
    }
    
    /**
     * Returns the number of steps of the path that was found.
     * 
     * @return the number of steps from start to finish
     */
    public int countSteps() {  return steps;  }
    
    /**
     * Returns the {@code Maze} in its original configuration, since this copy
     * remains unchanged.
     * 
     * @return the original maze
     */
    public char[][] getBoard() {  return board;  }
    
    /**
     * Returns the solution if one was found.
     * 
     * @return the solution
     */
    public char[][] getSolution() {  return solution;  }
}