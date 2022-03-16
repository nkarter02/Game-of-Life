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
 * This is the CellMoveDiagonal.java file which contains
 * the class CellMoveDiagonal.
 * java.util.List is imported for the checkApoptosis method.
 */

import java.util.List;

/**
 * This is the class CellMoveDiagonal, which extends Cell. There is are 2
 * instance variable named orientedRight and diagonalMoves. The former is a 
 * boolean indicating if the Cell is oriented to the right. The latter is an
 * int that contains how mnay times the cell has moved diagonally.
 */
public class CellMoveDiagonal extends CellMoveUp implements Movable
{
    public boolean orientedRight;
    public int diagonalMoves;

    private static final String ORIENTED_RIGHT_STRING = "/";
    private static final String NOT_ORIENTED_RIGHT_STRING = "\\";
    private static final int GREATER_THAN_NEIGHBORS = 3;
    private static final int TO_SWITCH_ORIENTATION = 4;

    /**
     * Basic constuctor for CellDivide
     * ALways sets orientedRight to tue and diagonalMoves to 0
     * 
     * @param currRow An int for current row in grid
     * @param currCol An int for current column in grid
     * @param mass An int for representing mass
     */
    public CellMoveDiagonal(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
        orientedRight = true;
        diagonalMoves = 0;
    }

    /**
     * A copy contuctor which copies values from the argument CellMoveDiagonal
     * 
     * @param otherCell A CellMoveDiagonal whose values are copied
     */
    public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal)
    {
        super(otherCellMoveDiagonal);
        this.orientedRight = otherCellMoveDiagonal.orientedRight;
        this.diagonalMoves = otherCellMoveDiagonal.diagonalMoves;
    }

    /**
     * Returns a String representation of the CellMoveToggle
     * 
     * @return ORIENTED_RIGHT_STRING if orientedRight,
     * otherwise return NOT_TOGGLED_STRING
     */
    public String toString()
    {
        if(orientedRight)
            return ORIENTED_RIGHT_STRING;
        return NOT_ORIENTED_RIGHT_STRING;
    }

    /**
     * Returns if it is valid for cell to undergo apoptosis
     * 
     * @param neighbors A list of nearby Cells
     * @return if cell has more than GREATER_THAN_NEIGHBORS neighbors
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        return neighbors.size() > GREATER_THAN_NEIGHBORS;
    }

    /**
     * Returns a deep copy of the calling object
     * 
     * @return a deep copy of the specific calling Cell
     */
    public Cell newCellCopy()
    {
        CellMoveDiagonal copy = new CellMoveDiagonal(getCurrRow(),
                                                     getCurrCol(), getMass());
        copy.orientedRight = this.orientedRight;
        copy.diagonalMoves = this.diagonalMoves;
        return copy;
    }

    /**
     * Returns where the Cell should move depending on orientedRight
     * Also changes orientedRight if diagonalMoves is a multiple of four
     * 
     * @return an array with the updated row and column values
     */
    public int[] getMove()
    {
        if(orientedRight)
        {
            diagonalMoves++;
            if(diagonalMoves % TO_SWITCH_ORIENTATION == 0)
                orientedRight = !orientedRight;
            return new int[]{getCurrRow() - 1, getCurrCol() + 1};
        }
        else
        {
            diagonalMoves++;
            if(diagonalMoves % TO_SWITCH_ORIENTATION == 0)
                orientedRight = !orientedRight;
            return new int[]{getCurrRow() - 1, getCurrCol() - 1};
        }
    }
}