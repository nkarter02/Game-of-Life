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
 * This is the Movable.java file which contains the Movable interface.
 */

/**
 * The Movable interface has one method to determine where Movables can move
 */
public interface Movable
{
    /**
     * Determines where a Movable object can move
     * 
     * @return an array of ints representing spots to move
     */
    public abstract int[] getMove();
}
