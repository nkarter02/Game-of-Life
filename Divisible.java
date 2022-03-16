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
 * This is the Divisible.java file which contains the Divisible interface.
 */

/**
 * The Divisible interface has one method to find where Divisibles can divide
 */
public interface Divisible
{
    /**
     * Determines where a Divisble object can divide
     * 
     * @return an array of ints representing spots to divide to
     */
    public abstract int[] getDivision();
}
