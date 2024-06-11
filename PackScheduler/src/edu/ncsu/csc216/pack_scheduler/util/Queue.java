/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * The Queue interface has a generic type E and has 5 methods
 * each concrete class implements the interface's methods
 * enqueue adds elements to the queue
 * dequeue removes elements from the queue
 * size() returns the size of the queue, which is the number of elements in the queue
 * setCapacity sets the capacity of the queue
 * @author Armando
 * @param <E> is a generic type of elements
 *
 */
public interface Queue<E> {

	/**
	 * The enqueue method is in charge of adding the Element parameter element to the back of the queue
	 * we first check for room inside the queue, we do this by checking the capacity of the Queue
	 * @param element is the element to be added to the queue after error checking
	 * @throws IllegalArgumentException if there is no room in the queue, if capacity has been reached
	 */
	void enqueue(E element);
	
	/**
	 * This dequeue method removes the element from the queue and returns the element
	 * if the queue is empty, we throw a NoSuchElementException
	 * @return E the element that is removed from the queue
	 */
	E dequeue();
	
	/**
	 * This method checks if the queue is empty
	 * 
	 * @return true if the queue is empty, return false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * This method returns the size of the queue which is the number of elements in the queue
	 * 
	 * @return size is the size of the queue
	 */
	int size();
	
	/**
	 * The setCapacity method sets the capacity of the queue to the parameter capacity
	 * we throw IAE if the capacity parameter is negative or if it is less than the size of the queue
	 * @param capacity is the capacity to be set to the capacity of the queue after being error checked
	 * @throws IllegalArgumentException if the capacity parameter is invalid
	 */
	void setCapacity(int capacity);
}
