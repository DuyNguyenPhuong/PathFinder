# HW3 SOLVING MAZES
## Overview

The Solving Mazes programs takes the maze input text files and input from the user. The user can see:
- the maze displayed without a solution
- the maze displayed with the solution, marked with `*` (if there is a solution)
- if the maze is unsolvable.

## Usage

When the user runs the program after downloading the code, they must compile the code and then execute the program with the name of the maze file that they would like to input, for example:
```java
$ javac *.java
$ java Maze maze.txt
```
Where `maze.txt` is the name of the maze file which user want (for the example below, it is `maze2.txt`).

After upon execution, the user is prompted whether or not they want to display the solution:
```
$ Do you want to display the solution?
$ Type "yes" or "no"
```
If the user types "yes," the maze is displayed with the solution squares marked with a `*`:
```
$ This is a solution!
$ +-----+-----+-----+-----+
$ |                       |
$ |  S                    |
$ |                       |
$ +     +-----+-----+     +
$ |     |           |     |
$ |  *  |           |     |
$ |     |           |     |
$ +     +-----+-----+-----+
$ |                       |
$ |  *     *     *     F  |
$ |                       |
$ +-----+-----+-----+-----+
```
If the user types "no," then the maze is displayed without the solution: 
```
$ Okay good luck!
$ +-----+-----+-----+-----+
$ |                       |
$ |  S                    |
$ |                       |
$ +     +-----+-----+     +
$ |     |           |     |
$ |     |           |     |
$ |     |           |     |
$ +     +-----+-----+-----+
$ |                       |
$ |                    F  |
$ |                       |
$ +-----+-----+-----+-----+
```

If the user makes a typo when typing yes or no, the user will be asked to type yes or no again: 
```
$ Do you want to display the solution?
$ Type "yes" or "no"
$ yu
$ Please type "yes" or "no"
```

## Prompt

Explain why the Stack is an appropriate ADT to use to simulate a human solving a maze (as opposed to a Queue).

A stack adds and removes a items from the top of a stack. At the same time, a queue adds items to the back, but removes items from the front. The queue would need to remove all the items from the set of solution squares if it hit a dead end, and start over. However, this is not how humans solve a maze, because we remember the last square before a dead end. We just need to go back to that square and try and new direction. This is how a stack works; when we remove the dead end square from the stack, the top item on the stack would be the last square before the dead end. 

## Rubric

### 1. README is clear and complete, including answer to prompt

The prompt is answered above. 

### 2. Mazes can be displayed without a solution shown

If the user doesn't want the solution to be displayed, they can type "no" and the program will print a unmarked maze. For example, the `maze2.txt` file is displayed as:
```
$ Do you want to display the solution?
$ Type "yes" or "no"
$ no
$ Okay good luck!
$ +-----+-----+-----+-----+
$ |                       |
$ |  S                    |
$ |                       |
$ +     +-----+-----+     +
$ |     |           |     |
$ |     |           |     |
$ |     |           |     |
$ +     +-----+-----+-----+
$ |                       |
$ |                    F  |
$ |                       |
$ +-----+-----+-----+-----+
```

### 3. Solved mazes found and displayed correctly
If the user wants the solution to be displayed, they can type "yes" and the program will print a marked-up maze. For example, for the `maze2.txt` file:
```
$ Do you want to display the solution?
$ Type "yes" or "no"
$ yes
$ This is a solution!
$ +-----+-----+-----+-----+
$ |                       |
$ |  S                    |
$ |                       |
$ +     +-----+-----+     +
$ |     |           |     |
$ |  *  |           |     |
$ |     |           |     |
$ +     +-----+-----+-----+
$ |                       |
$ |  *     *     *     F  |
$ |                       |
$ +-----+-----+-----+-----+
```

### 4. Program works on unsolvable mazes 

If the user wants to see the solution to an unsolvable maze, such as `UnsolvableMaze.txt`, the program will print
```
$ This maze is unsolvable!
```

If the user doesn't want to see the solution, then the unmarked maze will be printed:

```
$ Do you want to display the solution?
$ Type "yes" or "no"
$ no
$ Okay good luck!
$ +-----+-----+-----+-----+
$ |                       |
$ |  S                    |
$ |                       |
$ +     +-----+-----+     +
$ |     |           |     |
$ |     |           |     |
$ |     |           |     |
$ +     +-----+-----+-----+
$ |                 |     |
$ |                 |  F  |
$ |                 |     |
$ +-----+-----+-----+-----+
```

### 5. Two distinct mazes provided

There are 3 distinct mazes provided: `maze2.txt.`, `maze3.txt` and `UnsolvableMaze.txt`. `maze3.txt` is shown below. 

```
$ Do you want to display the solution?
$ Type "yes" or "no"
$ yes 
$ This is a solution!
$ +-----+-----+-----+-----+-----+
$ |                             |
$ |                             |
$ |                             |
$ +-----+-----+-----+-----+     +
$ |                       |     |
$ |              S     *  |     |
$ |                       |     |
$ +     +-----+-----+     +     +
$ |     |           |     |     |
$ |     |  *     F  |  *  |     |
$ |     |           |     |     |
$ +     +     +-----+     +     +
$ |     |                 |     |
$ |     |  *     *     *  |     |
$ |     |                 |     |
$ +     +-----+-----+-----+     +
$ |                             |
$ |                             |
$ |                             |
$ +-----+-----+-----+-----+-----+
```

### 6. JavaDocs documentation filled in

All of our methods have been commented in the JavaDocs style, and all the documentation stubs filled in. Here is an example for the `checkRight()` method:
```java
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
```

### 7. Otherwise good style

We follow JavaDocs style on variable, method and class names, indentations, maximum number of characters per line, and no extra commented out code. 
