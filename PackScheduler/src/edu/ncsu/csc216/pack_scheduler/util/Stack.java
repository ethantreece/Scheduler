/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * The Stack interface has a generic type of E it has 5 methods to be
 * implemented which are push, pop, isEmpty, size, and setCapacity This
 * interface allows for implementation in each class that implements the
 * interface
 * 
 * @author Armando
 * @author Ethan
 * @author Camille
 * @param <E> is a generic type for the Stack interface
 *
 */
public interface Stack<E> {

	/**
	 * This method is to be implemented, it adds the element to the top of the stack
	 * 
	 * @param element is the element to be added to the stack
	 * @throws IllegalArgumentException if the capacity has been reached
	 */
	void push(E element);

	/**
	 * This method removes the element at the top of the stack
	 * 
	 * @return E the element that is removed
	 */
	E pop();

	/**
	 * This method checks to see if the stack is empty
	 * 
	 * @return true if the stack is empty, returns false if the stack is not empty
	 */
	boolean isEmpty();

	/**
	 * This method returns the size of the stack, the number of elements in the
	 * stack is the size
	 * 
	 * @return size is the size of the stack
	 */
	int size();

	/**
	 * This method sets the capacity of the stack
	 * 
	 * @param capacity is the capacity of the stack to be set after error checking
	 * @throws IllegalArgumentException if the capacity parameter is negative or
	 *                                  less than the number of elements in the
	 *                                  stack, the size
	 */
	void setCapacity(int capacity);

}
