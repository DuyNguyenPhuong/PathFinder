import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* Creates a maze given some input file. It then can print out the solutions.
*
*/
public class Maze { 
    //Number of rows in the maze.
    private int numRows;
    
    //Number of columns in the maze.
    private int numColumns;
    
    //Grid coordinates for the starting maze square
    private int startRow;
    private int startColumn;
    
    //Grid coordinates for the final maze square
    private int finishRow;
    private int finishColumn;
    
    //A double array of the MazeSquares in the maze
    MazeSquare[][] squares;
    
    /**
     * Creates an empty maze with no squares.
     */
    public Maze() {
    } 
    
    /**
     * Load takes in the input file, and if the file exists
     * then it extracts the dimensions and creates the starting
     * and ending squares and all the squares in between. It takes
     * the information for each square and stores it in a MazeSquare
     * object, which is put two-dimensional array called "squares."
     * @param fileName
     * @return boolean
     */
    public boolean load(String fileName) { 
      File inputFile = new File(fileName);
      Scanner scanner = null;
      try {
        scanner = new Scanner(inputFile);
      } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
      }

      numRows = Integer.parseInt(scanner.next());
      numColumns = Integer.parseInt(scanner.next());
      squares = new MazeSquare[numRows][numColumns];
      startRow = Integer.parseInt(scanner.next());
      startColumn = Integer.parseInt(scanner.next());
      finishRow = Integer.parseInt(scanner.next());
      finishColumn = Integer.parseInt(scanner.next());

      for(int i=0; i<numRows; i++){
        String linecode = scanner.next();
        for (int j=0; j<numColumns; j++) {
          String code = linecode.substring(j,j+1);
          if(code.equals("7")){
            squares[i][j] = new MazeSquare(true, true, i, j);
          } else if(code.equals("|")) {
            squares[i][j] = new MazeSquare(false, true, i, j);
          } else if(code.equals("_")) {
            squares[i][j] = new MazeSquare(true, false, i, j);
          } else if(code.equals("*")) {
            squares[i][j] = new MazeSquare(false, false, i, j);
          } else {
            return false;
          }
          
        }
      }
      

      return true;
    }  
    
    /**
     * Checks if the number is within a set of defined bounds.
     * @param number
     * @param lowerBound
     * @param upperBound
     * @return boolean 
     */
    private static boolean isInRange(int number, int lowerBound, int upperBound) {
        return number < upperBound && number >= lowerBound;
    }
    
    /**
     * Print the maze with information given. We loop through each row 
     * of the maze, and then loop through the maze square objects in the maze and
     * print out all the characters in each maze square object.
     * @param solution
     */
    public void print(boolean solution) {
        //We'll print off each row of squares in turn.
        for(int row = 0; row < numRows; row++) {
            
            //Print each of the lines of text in the row
            for(int charInRow = 0; charInRow < 4; charInRow++) {
                //Need to start with the initial left wall.
                if(charInRow == 0) {
                    System.out.print("+");
                } else {
                    System.out.print("|");
                }
                
                for(int col = 0; col < numColumns; col++) {
                    MazeSquare curSquare = this.getMazeSquare(row, col);
                    if(charInRow == 0) {
                        //We're in the first row of characters for this square - need to print
                        //top wall if necessary.
                        if(curSquare.hasTopWall()) {
                            System.out.print(getTopWallString());
                        } else {
                            System.out.print(getTopOpenString());
                        }
                    } else if(charInRow == 1 || charInRow == 3) {
                        //These are the interior of the square and are unaffected by
                        //the start/final state.
                        if(curSquare.hasRightWall()) {
                            System.out.print(getRightWallString());
                        } else {
                            System.out.print(getOpenWallString());
                        }
                    } else {
                        //We must be in the second row of characters.
                        //This is the row where start/finish should be displayed if relevant
                        
                        //Check if we're in the start or finish state
                        if(startRow == row && startColumn == col) {
                            System.out.print("  S  ");
                        } else if(finishRow == row && finishColumn == col) {
                            System.out.print("  F  ");
                        } else if(curSquare.getSolutionPiece()) {
                          System.out.print("  *  ");
                        } else {
                            System.out.print("     ");
                        }
                        if(curSquare.hasRightWall()) {
                            System.out.print("|");
                        } else {
                            System.out.print(" ");
                        }
                    } 
                }
                
                //Now end the line to start the next
                System.out.print("\n");
            }           
        }
        
        //Finally, we have to print off the bottom of the maze, since that's not explicitly represented
        //by the squares. Printing off the bottom separately means we can think of each row as
        //consisting of four lines of text.
        printFullHorizontalRow(numColumns);
    }
    
    /**
     * Prints the very bottom row of characters for the bottom row of maze squares (which is always walls).
     * numColumns is the number of columns of bottom wall to print.
     */
    private static void printFullHorizontalRow(int numColumns) {
        System.out.print("+");
        for(int row = 0; row < numColumns; row++) {
            //We use getTopWallString() since bottom and top walls are the same.
            System.out.print(getTopWallString());
        }
        System.out.print("\n");
    }
    
    /**
     * Returns a String representing the bottom of a horizontal wall.
     */
    private static String getTopWallString() {
        return "-----+";
    }
    
    /**
     * Returns a String representing the bottom of a square without a
     * horizontal wall.
     */
    private static String getTopOpenString() {
        return "     +";
    }
    
    /**
     * Returns a String representing a left wall (for the interior of the row).
     */
    private static String getRightWallString() {
        return "     |";
    }
    
    /**
     * Returns a String representing no left wall (for the interior of the row).
     */
    private static String getOpenWallString() {
        return "      ";
    }
    
    /**
     * Returns the MazeSquare object based on the row and column input.
     * @param row the row of the corresponding square
     * @param col the column of the corresponding square
     * @return MazeSquare object
     */
    public MazeSquare getMazeSquare(int row, int col) {
        return squares[row][col];
    }

    /**
     * check if the maze square above the current one is directly 
     * accessible and unvisited. Return the MazeSquare if it's unvisited
     * and acessbile, else, return null.
     * @param tempSquare the MazeSquare we are currently on.
     * @return MazeSquare or null
     */
    public MazeSquare checkTop(MazeSquare tempSquare){
      int nextRow = tempSquare.getRow() - 1;
      int nextColumn = tempSquare.getColumn();
      if (!tempSquare.hasTopWall()){
        if (isInRange(nextRow, 0, numRows)){
          if (!squares[nextRow][nextColumn].getVisited()){
            return squares[nextRow][nextColumn];
          }
        }
      }
      return null;
    }

    /**
     * check if the maze square to the right of the current one is directly 
     * accessible and unvisited. Return the MazeSquare if it's unvisited
     * and acessbile, else, return null.
     * @param tempSquare the MazeSquare we are currently on.
     * @return MazeSquare or null
     */
    public MazeSquare checkRight(MazeSquare tempSquare){
      int nextRow = tempSquare.getRow();
      int nextColumn = tempSquare.getColumn()+1;
      if (!tempSquare.hasRightWall()){
        if (isInRange(nextColumn, 0, numColumns)){
          if (!squares[nextRow][nextColumn].getVisited()){
            return squares[nextRow][nextColumn];
          }
        }
      }
      return null;
    }
    
    /**
     * check if the maze square under the current one is directly 
     * accessible and unvisited. Return the MazeSquare if it's unvisited
     * and acessbile, else, return null.
     * @param tempSquare the MazeSquare we are currently on.
     * @return MazeSquare or null
     */
    public MazeSquare checkBottom(MazeSquare tempSquare){
      int nextRow = tempSquare.getRow() + 1;
      int nextColumn = tempSquare.getColumn();
      if (isInRange(nextRow, 0, numRows)){
        if (!squares[nextRow][nextColumn].hasTopWall()){
          if (!squares[nextRow][nextColumn].getVisited()){
            return squares[nextRow][nextColumn];
          }
        }
      }
      return null;
    }

    /**
     * check if the maze square to the left of the current one is directly 
     * accessible and unvisited. Return the MazeSquare if it's unvisited
     * and acessbile, else, return null.
     * @param tempSquare the MazeSquare we are currently on.
     * @return MazeSquare or null
     */
    public MazeSquare checkLeft(MazeSquare tempSquare){
      int nextRow = tempSquare.getRow();
      int nextColumn = tempSquare.getColumn() -1;
      if (isInRange(nextColumn, 0, numColumns)){
        if (!squares[nextRow][nextColumn].hasRightWall()){
          if (!squares[nextRow][nextColumn].getVisited()){
            return squares[nextRow][nextColumn];
          }
        }
      }
      return null;
    }

    /**
     * Create a stack of type MazeSquare, then use the check methods
     * to build up a stack of maze squares leading to the solution.
     * @return stack of MazeSquares which is the solution to the maze.
     * If the stack is empty, return null. 
     */
    public Stack<MazeSquare> getSolution() {
      Stack<MazeSquare> solution = new LinkedStack<MazeSquare>();
      MazeSquare startSquare = squares[startRow][startColumn];
      solution.push(startSquare);
      startSquare.setVisited();
      MazeSquare currentSquare = startSquare;
      
      while (!solution.isEmpty()){
        if (currentSquare.getRow() == finishRow && currentSquare.getColumn() == finishColumn){
          return solution;
        }

        if(checkTop(currentSquare) != null){
          solution.push(checkTop(currentSquare));
          checkTop(currentSquare).setSolutionPiece();
          MazeSquare adjacentSquare = checkTop(currentSquare);
          checkTop(currentSquare).setVisited();
          currentSquare = adjacentSquare;
        } 
        
        else if(checkRight(currentSquare) != null){
          solution.push(checkRight(currentSquare));
          checkRight(currentSquare).setSolutionPiece();
          MazeSquare adjacentSquare = checkRight(currentSquare);
          checkRight(currentSquare).setVisited();
          currentSquare = adjacentSquare;
        }

        else if(checkLeft(currentSquare) != null){
          solution.push(checkLeft(currentSquare));
          checkLeft(currentSquare).setSolutionPiece();
          MazeSquare adjacentSquare = checkLeft(currentSquare);
          checkLeft(currentSquare).setVisited();
          currentSquare = adjacentSquare;
        }

        else if(checkBottom(currentSquare) != null){
          solution.push(checkBottom(currentSquare));
          checkBottom(currentSquare).setSolutionPiece();
          MazeSquare adjacentSquare = checkBottom(currentSquare);
          checkBottom(currentSquare).setVisited();
          currentSquare = adjacentSquare;
        }

        else {
          currentSquare.resetSolutionPiece();
          solution.pop();
          currentSquare = solution.peek();
        }
      }
      
      if (solution.isEmpty()){
       return null;
      }
      else{
        return solution;
      }
  }
 
    /**
     * Take maze file the user inputs in the command line argument,
     * then lets the user choose whether or not they would like
     * a maze solution displayed for them. 
     * @param args name of the maze file
     */
    public static void main(String[] args) {
      if(args.length == 1) {
        Maze myMaze = new Maze();
        myMaze.load(args[0]);

        Scanner myObj = new Scanner(System.in);
        System.out.println("Do you want to display the solution?");
        System.out.println("Type \"yes\" or \"no\"");
        String userInput = myObj.nextLine();
        if (userInput.equals("yes")){
          Stack<MazeSquare> solutionPath = myMaze.getSolution();
          if (solutionPath == null){
            System.out.println("This maze is unsolvable!");
          }
          else{
            System.out.println("This is a solution!");
            myMaze.print(false);
          } 
        } 
        else if (userInput.equals("no")){
          System.out.println("Okay good luck!");
          myMaze.print(false);
        } 
        else{
          System.out.println("Please type \"yes\" or \"no\"");
        }
      }
    } 
}