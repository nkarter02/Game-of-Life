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
 * This is the CellStationary.java file which contains the class CellStationary
 * java.util.List is imported for the checkApoptosis method.
 */

import java.util.List;

/**
 * This is the class CellStationary, which extends Cell.
 */
public class CellStationary extends Cell
{
    private static final String STATIONARY_STRING = ".";
    private static final int MIN_NEIGHBORS = 3;
    private static final int MAX_NEIGHBORS = 7;

    /**
     * Basic constuctor for CellStationary
     * 
     * @param currRow An int for current row in grid
     * @param currCol An int for current column in grid
     * @param mass An int for representing mass
     */
    public CellStationary(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
    }

    /**
     * A copy contuctor which copies values from the argument CellStationary
     * 
     * @param otherCell A CellStationary whose values are copied
     */
    public CellStationary(CellStationary otherCellStationary)
    {
        super(otherCellStationary);
    }

    /**
     * Returns a String representation of the CellStationary
     * 
     * @return STATIONARY_STRING
     */
    public String toString()
    {
        return STATIONARY_STRING;
    }

    /**
     * Returns if it is valid for cell to undergo apoptosis
     * 
     * @param neighbors A list of nearby Cells
     * @return if # of neighbors is between MIN_NEIGHBORS and MAX_NEIGHBORS
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        return neighbors.size() >= MIN_NEIGHBORS &&
               neighbors.size() <= MAX_NEIGHBORS;
    }

    /**
     * Returns a deep copy of the calling object
     * 
     * @return a deep copy of the specific calling Cell
     */
    public Cell newCellCopy()
    {
        CellStationary copy = new CellStationary(getCurrRow(),
                                                 getCurrCol(), getMass());
        return copy;
    }

    /**
     * Returns where the Cell should stay
     * 
     * @return an array with the updated row and column values
     */
    public int[] getMove()
    {
        return new int[]{getCurrRow(), getCurrCol()};
    }
}
