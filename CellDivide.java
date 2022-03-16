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
 * This is the CellDivide.java file which contains the class CellDivide.
 * java.util.List is imported for the checkApoptosis method.
 */

import java.util.List;

/**
 * This is the class CellDivide, which extends Cell. There is an added
 * instance variable named direction, which is an int indicating which way
 * the cell will travel;
 */
public class CellDivide extends Cell implements Divisible
{
    public int direction;
    
    private static final String DIVIDE_TO_STRING = "+";
    private static final int NEIGHBORS_FOR_APOPTOSIS = 3;
    private static final int DOWN = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;

    /**
     * Basic constuctor for CellDivide, always sets direction to 1
     * 
     * @param currRow An int for current row in grid
     * @param currCol An int for current column in grid
     * @param mass An int for representing mass
     */
    public CellDivide(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
        direction = 1;
    }

    /**
     * A copy contuctor which copies values from the argument CellDivide
     * 
     * @param otherCell A CellDivide whose values are copied
     */
    public CellDivide(CellDivide otherCellDivide)
    {
        super(otherCellDivide);
        this.direction = otherCellDivide.direction;
    }

    /**
     * Returns a String representation of the CellDivide
     * 
     * @return DIVIDE_TO_STRING
     */
    public String toString()
    {
        return DIVIDE_TO_STRING;
    }

    /**
     * Returns if it is valid for cell to undergo apoptosis
     * 
     * @param neighbors A list of nearby Cells
     * @return if neighbors == NEIGHBORS_FOR_APOPTOSIS
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        return neighbors.size() == NEIGHBORS_FOR_APOPTOSIS;
    }

    /**
     * Returns a deep copy of the calling object
     * 
     * @return a deep copy of the specific calling Cell
     */
    public Cell newCellCopy()
    {
        CellDivide copy = new CellDivide(getCurrRow(), getCurrCol(), getMass());
        copy.direction = this.direction;
        return copy;
    }

    /**
     * Based on the direction of the Cell, give coordinates for the new Cell
     * Also cycle the direction
     */
    public int[] getDivision()
    {
        if(direction == DOWN)
        {
            direction++;
            return new int[]{getCurrRow() + 1, getCurrCol()};
        }
        else if(direction == UP)
        {
            direction++;
            return new int[]{getCurrRow() - 1, getCurrCol()};
        }
        else if(direction == LEFT)
        {
            direction++;
            return new int[]{getCurrRow(), getCurrCol() - 1};
        }
        else
        {
            direction = DOWN;
            return new int[]{getCurrRow() - 1, getCurrCol()};
        }
    }
}