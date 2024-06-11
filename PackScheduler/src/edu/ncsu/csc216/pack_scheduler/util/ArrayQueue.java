/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * The ArrayQueue Class implements the Queue of generic type it provides unique
 * implementation for the 5 methods as a queue it has the methods to add to the
 * queue via enqueue remove from the queue via dequeue() check to see if the
 * queue if empty via isEmpty() get the size of the queue via size() set the
 * capacity of the queue via setCapacity()
 * 
 * @author Camille
 * @author Ethan
 * @author Armando
 * @param <E> ArrayQueue has a generic type E exactly the same as the Queue
 *            which can hold any object
 */
public class ArrayQueue<E> implements Queue<E> {
	/** the capacity field holds the capacity of the arrayqueue */
	int capacity;
	/** This is a list of generic type E */
	ArrayList<E> list;

	/**
	 * The constructor creates a new ArrayList
	 * the capacity is checked via the setCapacity method
	 * 
	 * @param capacity is the capacity of the ArrayQueue, all the error checking is done via the setCapacity method
	 * @throws IllegalArgumentException if the capacity is negative or less than the size of the list
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}

	@Override
	public void enqueue(E element) {
		if (element == null)
			throw new IllegalArgumentException();
		if (list.size() == capacity)
			throw new IllegalArgumentException();
		list.add(element);

	}

	@Override
	public E dequeue() {
		if (list.size() == 0)
			throw new NoSuchElementException();
		return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size())
			throw new IllegalArgumentException();
		this.capacity = capacity;
	}

	/**
	 * Iterates through the entire list to check if if contains the parameterized
	 * element
	 * 
	 * @param element element
	 * @return true if the list contains element, false if not
	 * @throws IllegalArgumentException if the given element is null
	 */
	public boolean contains(E element) {
		if (element == null)
			throw new IllegalArgumentException();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == element) {
				return true;
			}
		}
		return false;
	}

}
