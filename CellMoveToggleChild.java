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
 * This is the CellMoveToggleChild.java file,
 * which has the CellMoveToggleChild class.
 * java.util.List is imported for the checkApoptosis method.
 */

import java.util.List;

/**
 * This is the class CellMoveToggleChild,
 * which extends CellMoveToggle. There is an added instance variable named
 * numAlive, which is an int containing how many CellMoveToggleChild objects 
 * are on the board
 */
public class CellMoveToggleChild extends CellMoveToggle implements Movable
{
    public static int numAlive;

    private static final int NUM_ALIVE_FOR_APOPTOSIS = 10;

    /**
     * Basic constuctor for CellMoveToggleChild, increments numAlive
     * 
     * @param currRow An int for current row in grid
     * @param currCol An int for current column in grid
     * @param mass An int for representing mass
     */
    public CellMoveToggleChild(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
        numAlive++;
    }

    /**
     * A copy contuctor which copies values from the input CellMoveToggleChild
     * 
     * @param otherCell A CellMoveToggle whose values are copied
     */
    public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild)
    {
        super(otherCellMoveToggleChild);
        numAlive++;
    }

    /**
     * Returns a String representation of the CellMoveToggleChild
     * 
     * @return the same String as the super class
     */
    public String toString()
    {
        return super.toString();
    }

    /**
     * Performs apoptosis utiliing the parent's method.
     * Also decrements numAlive.
     */
    public void apoptosis()
    {
        super.apoptosis();
        numAlive--;
    }

    /**
     * Returns if it is valid for cell to undergo apoptosis
     * 
     * @param neighbors A list of nearby Cells
     * @return if parent class can perform apoptosis and
     * numAlive < NUM_ALIVE_FOR_APOPTOSIS
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        return super.checkApoptosis(neighbors) &&
        numAlive < NUM_ALIVE_FOR_APOPTOSIS;
    }

    /**
     * Returns a deep copy of the calling object
     * 
     * @return a deep copy of the specific calling Cell
     */
    public Cell newCellCopy()
    {
        CellMoveToggleChild copy = new CellMoveToggleChild(getCurrRow(),
                                                           getCurrCol(),
                                                           getMass());
        return copy;
    }
}