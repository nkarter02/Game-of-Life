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
 * This is the Cell.java file which contains the abstract class Cell.
 * java.util.List is imported for the checkApoptosis method.
 */

import java.util.List;

/**
 * This is the abstract class cell. It has three instance variables, which
 * are used to determine where a cell is on a grid, and how large it is. The
 * class also contains two constuctors and one abstract class, which will be
 * implemented by its subclasses.
 */
public abstract class Cell implements Comparable<Cell>
{
   public int currRow;
   public int currCol;
   public int mass;

   /**
    * The main constuctor for Cell. Passes arguments to instance variables
    *
    * @param currRow An int for current row in grid
    * @param currCol An int for current column in grid
    * @param mass An int for representing mass
    */
   public Cell(int currRow, int currCol, int mass)
   {
        this.currRow = currRow;
        this.currCol = currCol;
        this.mass = mass;

        // All values must be >= 0, so values are changed to 0 if negative
        if(this.currRow < 0)
            this.currRow = 0;
        if(this.currCol < 0)
            this.currCol = 0;
        if(this.mass < 0)
            this.mass = 0;
    }

    /**
     * A copy contuctor which copies values from the argument Cell
     * 
     * @param otherCell A cell whose values are copied
     */
    public Cell(Cell otherCell)
    {
        this.currRow = otherCell.currRow;
        this.currCol = otherCell.currCol;
        this.mass = otherCell.mass;
    }

    /**
     * Meant to represent the death of a cell, all values are made -1
     */
    public void apoptosis()
    {
        currRow = currCol = mass = -1;
    }

    /**
     * Basic getter method for currRow
     * 
     * @return currRow
     */
    public int getCurrRow()
    {
        return currRow;
    }
    /**
     * Basic getter method for currCol
     * 
     * @return currCol
     */
    public int getCurrCol()
    {
        return currCol;
    }

    /**
     * Basic getter method for mass
     * 
     * @return mass
     */
    public int getMass()
    {
        return mass;
    }

    /**
     * Compares the mass of this cell and input cell
     * 
     * @param otherCell the cell that is being compared
     * @return the difference between the two masses
     */
    public int compareTo(Cell otherCell)
    {
        if(otherCell == null)
            return 0;
        return this.mass - otherCell.getMass();
    }

    /**
     * Determines if cell should perform apoptosis.
     * To be implemented by subclasses.
     * 
     * @param neighbors A list of nearby Cells
     * @return Whether or not the cell should do apoptosis
     */
    public abstract boolean checkApoptosis(List<Cell> neighbors);

    /**
     * Returns a deep copy of the calling object
     * 
     * @return a deep copy of the specific calling Cell
     */
    public abstract Cell newCellCopy();
}