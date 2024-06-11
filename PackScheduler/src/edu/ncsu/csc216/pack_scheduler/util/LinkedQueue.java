/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * LinkedQueue implements the Queue list. LinkedQueue overrides
 * the Queue methods to provide custom implementation
 * LinkedQueue implements the 5 methods from Queue
 * it adds the elements via enqueue()
 * removes the elements via dequeue()
 * checks to see if the queue is empty via isEmpty()
 * gets the size of the queue via size()
 * @author Ethan
 * @author Armando
 * @author Camille
 * @param <E> arbitrary object, generic type
 */
public class LinkedQueue<E> implements Queue<E> {

	/** the capacity field stores the capacity for the LinkedQueue */
	int capacity;
	/** This is a list of generic type E that stores objects */
	LinkedAbstractList<E> list;
	
	/**
	 * Constructs the LinkedQueue object with a given capacity
	 * @param capacity is the capacity of the list when constructed
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}

	@Override
	public void enqueue(E element) {
		list.add(size(), element);
	}

	@Override
	public E dequeue() {
		if(size() == 0) throw new NoSuchElementException();
		return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
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
		if (element == null) throw new IllegalArgumentException();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == element) {
				return true;
			}
		}
		return false;
	}

}
