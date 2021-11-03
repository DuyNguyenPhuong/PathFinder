/**
* MazeSquare represents a single square within a Maze.
* @author Anna Rafferty
* @author Anya Vostinar
*/ 
public class MazeSquare {
    //Wall variables
    private boolean hasTopWall = false;
    private boolean hasRightWall = false;

    //Other variables
    private boolean visited = false;
    private boolean solutionPiece = false;
		
    //Location of this square in a larger maze.
    private int row;
    private int col;
		
    public MazeSquare(boolean top, boolean right, int r, int c) {
      hasTopWall = top;
      hasRightWall = right;
      row = r;
      col = c;
    }
		
    /**
     * Checks if the MazeSquare has a top wall.
     * @return boolean
     */
    public boolean hasTopWall() {
        return hasTopWall;
    }
		
    /**
     * Checks if the MazeSquare has a right wall. 
     * @return boolean
     */
    public boolean hasRightWall() {
        return hasRightWall;
    }
		
    /**
     *  Gets the number of the MazeSquare's row.
     * @return integer 
     */
    public int getRow() {
        return row;
    }
		
    /**
     * Gets the number of the MazeSquare's column.
     * @return integer
     */
    public int getColumn() {
        return col;
    }

    /**
     * Mark a MazeSquare as visited.
     */
    public void setVisited() {
      visited = true;
    }

    /**
     * Mark the MazeSquare as unvisited.
     */
    public void setUnvisited() {
      visited = false;
    }

    /**
     * Checks if the MazeSquare is visited.
     * @return boolean
     */
    public boolean getVisited() {
      return visited;
    }

    /**
     * Checks if the MazeSqaure is part of the solution. 
     * @return boolean
     */
    public boolean getSolutionPiece() {
      return solutionPiece;
    }

    /**
     * Mark the MazeSquare as part of the solution. 
     */
    public void setSolutionPiece() {
      solutionPiece = true;
    }

    /**
     * Mark the MazeSquare as not part of the solution set.
     */
    public void resetSolutionPiece() {
      solutionPiece = false;
    }  
} 