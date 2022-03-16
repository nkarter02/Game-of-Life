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
 * This is the CellMoveUp.java file which contains the class CellMoveUp.
 * java.util.List is imported for the checkApoptosis method.
 */

import java.util.List;

/**
 * This is the class CellMoveUp, which extends Cell.
 */
public class CellMoveUp extends Cell implements Movable
{
    private static final String MOVEUP_TO_STRING = "^";
    private static final int NEIGHBORS_NOT_FOR_APOPTOSIS = 4;

    /**
     * Basic constuctor for CellMoveUp
     * 
     * @param currRow An int for current row in grid
     * @param currCol An int for current column in grid
     * @param mass An int for representing mass
     */
    public CellMoveUp(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
    }

    /**
     * A copy contuctor which copies values from the argument CellDivide
     * 
     * @param otherCell A CellMoveUp whose values are copied
     */
    public CellMoveUp(CellMoveUp otherCellMoveUp)
    {
        super(otherCellMoveUp);
    }

    /**
     * Returns a String representation of the CellDivide
     * 
     * @return MOVEUP_TO_STRING
     */
    public String toString()
    {
        return MOVEUP_TO_STRING;
    }

    /**
     * Returns if it is valid for cell to undergo apoptosis
     * 
     * @param neighbors A list of nearby Cells
     * @return if neighbors != NEIGHBORS_NOT_FOR_APOPTOSIS
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        return neighbors.size() != NEIGHBORS_NOT_FOR_APOPTOSIS;
    }
    
    /**
     * Returns a deep copy of the calling object
     * 
     * @return a deep copy of the specific calling Cell
     */
    public Cell newCellCopy()
    {
        CellMoveUp copy = new CellMoveUp(getCurrRow(), getCurrCol(), getMass());
        return copy;
    }

    /**
     * Returns where the Cell should move up one row
     * 
     * @return an array with the updated row and column values
     */
    public int[] getMove()
    {
        return new int[]{getCurrRow() - 1, getCurrCol()};
    }
}
