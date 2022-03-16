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
 * This is the CellMoveToggle.java file which has the CellMoveToggle class.
 * java.util.List is imported for the checkApoptosis method.
 */

import java.util.List;

/**
 * This is the class CellMoveToggle, which extends CellMoveUp. There is an added
 * instance variable named toggle, which is an boolean indicating if
 * the cell is toggled or not
 */
public class CellMoveToggle extends CellMoveUp implements Movable
{

    public boolean toggled;

    private static final String TOGGLED_STRING = "T";
    private static final String NOT_TOGGLED_STRING = "t";
    private static final int LESS_THAN_NEIGHBORS = 2;
    private static final int GREATER_THAN_NEIGHBORS = 5;

    /**
     * Basic constuctor for CellMoveToggle, always sets toggled to true
     * 
     * @param currRow An int for current row in grid
     * @param currCol An int for current column in grid
     * @param mass An int for representing mass
     */
    public CellMoveToggle(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
        toggled = true;
    }

    /**
     * A copy contuctor which copies values from the argument CellMoveToggle
     * 
     * @param otherCell A CellMoveToggle whose values are copied
     */
    public CellMoveToggle(CellMoveToggle otherCellMoveToggle)
    {
        super(otherCellMoveToggle);
        this.toggled = otherCellMoveToggle.toggled;
    }

    /**
     * Returns a String representation of the CellMoveToggle
     * 
     * @return TOGGLED_STRING if toggled, otherwise return NOT_TOGGLED_STRING
     */
    public String toString()
    {
        if(toggled)
            return TOGGLED_STRING;
        return NOT_TOGGLED_STRING;
    }

    /**
     * Returns if it is valid for cell to undergo apoptosis
     * 
     * @param neighbors A list of nearby Cells
     * @return if neighbors < LESS_THAN_NEIGHBORS or > GREATER_THAN_NEIGHBORS
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        return neighbors.size() < LESS_THAN_NEIGHBORS ||
               neighbors.size() > GREATER_THAN_NEIGHBORS;
    }

    /**
     * Returns a deep copy of the calling object
     * 
     * @return a deep copy of the specific calling Cell
     */
    public Cell newCellCopy()
    {
        CellMoveToggle copy = new CellMoveToggle(getCurrRow(),
                                                 getCurrCol(), getMass());
        copy.toggled = this.toggled;
        return copy;
    }

    /**
     * Returns where the Cell should move depending on toggled
     * Toggled flips value
     * 
     * @return an array with the updated row and column values
     */
    public int[] getMove()
    {
        if(toggled)
        {
            toggled = !toggled;
            return new int[]{getCurrRow() - 1, getCurrCol()};
        }
        else
        {
            toggled = !toggled;
            return new int[]{getCurrRow(), getCurrCol()};
        }
    }
}
