/**
 * NAME: Nathaniel Karter
 * ID: A16226916
 * EMAIL: nkarter@ucsd.edu
 * 
 * SOURCES:
 * -CSE 11: Introduction to Computer Science and Object-Oriented Programming
 * -Professor Cao's CSE 11 Zoom Lectures
 */

/**
 * This is PetriDish.java, which contains the PetriDish class. This is a 
 * representation of a grid of Cell objects that is used as a simple version
 * of Conway's Game of Life
 */

import java.util.List;
import java.util.ArrayList;

/**
 * This is the PertiDish class, which has one important instance variable.
 * The dish variable is a 2D matrix of Cell objects that represents a board
 */
public class PetriDish
{
    public Cell[][] dish;
    public List<Movable> movables;
    public List<Divisible> divisibles;

    private static final String SPACE = " ";
    private static final String CELL_MOVE_UP = "CellMoveUp";
    private static final String CELL_MOVE_TOGGLE = "CellMoveToggle";
    private static final String CELL_MOVE_TOGGLE_CHILD = "CellMoveToggleChild";
    private static final String CELL_MOVE_DIAGONAL = "CellMoveDiagonal";
    private static final String CELL_DIVIDE = "CellDivide";
    private static final String CELL_STATIONARY = "CellStationary";
    private static final String NULL_STRING = "null";
    private static final String SAMPLE_CELL_STRING = "CellMoveUp 1";
    private static final int UPDATE_LOW_BOUND = 2;
    private static final int UPDATE_HIGH_BOUND = 3;

    /**
     * The main constructor for PetriDish objects. It changes dish based on the
     * contents of the String matrix argument.
     * 
     * @param board A 2D array of Strings that are used to create Cell objects
     */
    public PetriDish(String[][] board)
    {
        int rows = board.length;
        int cols = board[0].length;

        // dish is the same size as board
        dish = new Cell[rows][cols];

        // Go throughout the entire matrix
        for(int currRow = 0; currRow < rows; currRow++)
        {
            for(int currCol = 0; currCol < cols; currCol++)
            {
                String cellInfo = board[currRow][currCol];

                // Check if the current String is null
                // Make the corresponding Cell also null
                if(cellInfo.equals(NULL_STRING))
                {
                    dish[currRow][currCol] = null;
                    continue;
                }

                // Get the String data at each index and split it into a name
                // And a number
                String cellType =
                    cellInfo.substring(0, cellInfo.indexOf(SPACE));

                // The name represents the type of Cell
                // The number represents the mass of the Cell
                int cellMass = Integer.parseInt(
                    cellInfo.substring(cellInfo.indexOf(SPACE) + 1));

                // Based on the type of Cell, make a new Cell at current index
                switch(cellType)
                {
                    case CELL_MOVE_UP:
                        dish[currRow][currCol]
                            = new CellMoveUp(currRow, currCol, cellMass);
                        break;

                    case CELL_MOVE_TOGGLE:
                        dish[currRow][currCol]
                            = new CellMoveToggle(currRow, currCol, cellMass);
                        break;

                    case CELL_MOVE_TOGGLE_CHILD:
                        dish[currRow][currCol]
                            = new CellMoveToggleChild(currRow, currCol,
                                                      cellMass);
                        break;

                    case CELL_MOVE_DIAGONAL:
                        dish[currRow][currCol]
                            = new CellMoveDiagonal(currRow, currCol, cellMass);
                        break;

                    case CELL_DIVIDE:
                        dish[currRow][currCol]
                            = new CellDivide(currRow, currCol, cellMass);
                        break;

                    case CELL_STATIONARY:
                        dish[currRow][currCol]
                            = new CellStationary(currRow, currCol, cellMass);
                        break;
                }
            }
        }
    }

    /**
     * Get a list of all neighboring cells (8 direction) from a specific
     * location. Wrapping is allowed
     * 
     * @param row the row to check
     * @param col the column to check
     * @return a list of cells who are next to the specified index
     */
    public List<Cell> getNeighborsOf(int row, int col)
    {
        int rows = dish.length;
        int cols = dish[0].length;

        // check if location is valid
        if(row < 0 || row >= rows || col < 0 || col >= cols)
        {
            return null;
        }

        // make a List to hold neighbors
        List<Cell> nghbrs = new ArrayList<Cell>();

        // NORTHWEST
        if(dish[(row - 1 + rows) % rows][(col - 1 + cols) % cols] != null)
            nghbrs.add(dish[(row - 1 + rows) % rows][(col - 1 + cols) % cols]);
        // NORTH
        if(dish[(row - 1 + rows) % rows][(col + cols) % cols] != null)
            nghbrs.add(dish[(row - 1 + rows) % rows][(col + cols) % cols]);
        // NORTHEAST
        if(dish[(row - 1 + rows) % rows][(col + 1 + cols) % cols] != null)
            nghbrs.add(dish[(row - 1 + rows) % rows][(col + 1 + cols) % cols]);
        // WEST
        if(dish[(row + rows) % rows][(col - 1 + cols) % cols] != null)
            nghbrs.add(dish[(row + rows) % rows][(col - 1 + cols) % cols]);
        // EAST
        if(dish[(row + rows) % rows][(col + 1 + cols) % cols] != null)
            nghbrs.add(dish[(row + rows) % rows][(col + 1 + cols) % cols]);
        // SOUTHWEST
        if(dish[(row + 1 + rows) % rows][(col - 1 + cols) % cols] != null)
            nghbrs.add(dish[(row + 1 + rows) % rows][(col - 1 + cols) % cols]);
        // SOUTH
        if(dish[(row + 1 + rows) % rows][(col + cols) % cols] != null)
            nghbrs.add(dish[(row + 1 + rows) % rows][(col + cols) % cols]);
        // SOUTHEAST
        if(dish[(row + 1 + rows) % rows][(col + 1 + cols) % cols] != null)
            nghbrs.add(dish[(row + 1 + rows) % rows][(col + 1 + cols) % cols]);

        return nghbrs;
    }

    /**
     * All Movable Cells will move according to their getMove() method.
     * If two move to the same spot, the smaller will be destroyed.
     */
    public void move()
    {
        int rows = dish.length;
        int cols = dish[0].length;

        for(int row = 0; row < rows; row++)
        {
            for(int col = 0; col < cols; col++)
            {
                // Verify that cell is Movable
                if(dish[row][col] instanceof Movable)
                {
                    // Check for wrapping
                    int[] newLoc = ((Movable)dish[row][col]).getMove();
                    int newRow = (newLoc[0] + rows) % rows;
                    int newCol = (newLoc[1] + cols) % cols;

                    // If new location is empty
                    if(dish[newRow][newCol] == null)
                    {
                        dish[row][col].currRow = newRow;
                        dish[row][col].currCol = newCol;
                        dish[newRow][newCol] = dish[row][col];
                        dish[row][col] = null;
                    }
                    // If a non Movable Cell is there
                    else if(!(dish[newRow][newCol] instanceof Movable))
                    {
                        dish[row][col].currRow = newRow;
                        dish[row][col].currCol = newCol;
                        dish[newRow][newCol].apoptosis();
                        dish[newRow][newCol] = dish[row][col];
                        dish[row][col] = null;
                    }
                    // If the new spot is occupied by a Movable cell
                    else if(dish[newRow][newCol] instanceof Movable)
                    {
                        // the larger one will stay
                        if(dish[row][col].compareTo(dish[newRow][newCol]) > 0)
                        {
                            dish[row][col].currRow = newRow;
                            dish[row][col].currCol = newCol;
                            dish[newRow][newCol].apoptosis();
                            dish[newRow][newCol] = dish[row][col];
                            dish[row][col] = null;
                        }
                        if(dish[row][col].compareTo(dish[newRow][newCol]) < 0)
                        {
                            dish[row][col].apoptosis();
                            dish[row][col] = null;
                        }
                        //if they are the same size, both die
                        else
                        {
                            dish[row][col].apoptosis();
                            dish[newRow][newCol].apoptosis();
                            dish[row][col] = null;
                            dish[newRow][newCol] = null;
                        }
                    }
                }
            }
        }
    }

    /**
     * All Divisible Cells will move according to their getDivide() method.
     * If two move to the same spot, the smaller will be destroyed.
     */
    public void divide()
    {
        int rows = dish.length;
        int cols = dish[0].length;

        for(int row = 0; row < rows; row++)
        {
            for(int col = 0; col < cols; col++)
            {
                // if the current cell is a Divisible cell
                if(dish[row][col] instanceof Divisible)
                {
                    // get the location that it would divide to
                    int[] newLoc = ((Divisible)dish[row][col]).getDivision();
                    int newRow = (newLoc[0] + rows) % rows;
                    int newCol = (newLoc[1] + cols) % cols;

                    // fill in the spot with a new cell if it is empty
                    if(dish[newRow][newCol] == null)
                    {
                        dish[newRow][newCol] = dish[row][col].newCellCopy();
                        dish[newRow][newCol].currRow = newRow;
                        dish[newRow][newCol].currCol = newCol;
                    }
                    // if the spot to divide to is occuppied by a Divisible
                    else if(dish[newRow][newCol] instanceof Divisible)
                    {
                        if(dish[row][col].compareTo(dish[newRow][newCol]) > 0)
                        {
                            dish[newRow][newCol].apoptosis();
                            dish[newRow][newCol] = dish[row][col].newCellCopy();
                            dish[newRow][newCol].currRow = newRow;
                            dish[newRow][newCol].currCol = newCol;
                        }
                        if(dish[row][col].compareTo(dish[newRow][newCol]) < 0)
                        {
                            //Do nothing if the other cell is larger
                        }
                        else
                        {
                            dish[newRow][newCol].apoptosis();
                            dish[newRow][newCol] = null;
                        }
                    }
                }
            }
        }
    }

    /**
     * Update the dish by spawning new cells in elgible empty spaces
     */
    public void update()
    {
        // create a dish that will maintain all initial positions
        Cell[][] before = new Cell[dish.length][dish[0].length];

        // create a deep copy of dish
        for(int row = 0; row < before.length; row++)
        {
            for(int col = 0; col < before[row].length; col++)
            {
                if(dish[row][col] != null)
                {
                    before[row][col] = dish[row][col].newCellCopy();
                }
            }
        }

        // create a new PetriDish object to maintain all of this PetriDish's
        // instance variables. This is used to perform cell movement and 
        // apoptosis simultaneously.
        PetriDish copy = new PetriDish(new String[][]{{SAMPLE_CELL_STRING}});
        copy.dish = before;

        // perform apoptosis nested for loop
        for(int row = 0; row < before.length; row++)
        {
            for(int col = 0; col < before[row].length; col++)
            {
                List<Cell> neighbors = copy.getNeighborsOf(row, col);
                if(before[row][col] != null && 
                   before[row][col].checkApoptosis(neighbors))
                {
                    dish[row][col].apoptosis();
                    dish[row][col] = null;
                }
            }
        }

        // spawn new cells nested for loop
        for(int row = 0; row < dish.length; row++)
        {
            for(int col = 0; col < dish[row].length; col++)
            {
                // if a spot in the original dish is empty
                if(before[row][col] == null)
                {
                    // if empty spot has a # of neighbors in between boundaries
                    if(copy.getNeighborsOf(row, col).size()
                        >= UPDATE_LOW_BOUND &&
                       copy.getNeighborsOf(row, col).size()
                        <= UPDATE_HIGH_BOUND)
                    {
                        // the new cell spawn there based on the 1st neighbor
                        dish[row][col] =
                            copy.getNeighborsOf(row, col).get(0).newCellCopy();
                    }
                }
            }
        }
    }

    /**
     * A method that calls on move(), divide(), and update() in that order
     */
    public void iterate()
    {
        move();
        divide();
        update();
    }
}