/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayStack implements the Stack list. ArrayStack overrides the Stack methods
 * to provide custom implementation of the 5 methods. The arrayStack has a
 * capacity field that holds the capacity of the stack
 * 
 * @author Armando
 * @author Ethan
 * @param <E> arbitrary object of generic type
 *
 */
public class ArrayStack<E> implements Stack<E> {

	/** This is the stack which will hold objects */
	ArrayList<E> stack;
	/** The capacity field stores the capacity of the stack */
	int capacity;

	/**
	 * Constructs the ArrayStack object with the given capacity
	 * 
	 * @param capacity capacity
	 */
	public ArrayStack(int capacity) {
		stack = new ArrayList<E>();
		setCapacity(capacity);
	}

	@Override
	public void push(E element) {
		if (size() >= capacity)
			throw new IllegalArgumentException();
		stack.add(element);
	}

	@Override
	public E pop() {
		if (size() == 0)
			throw new EmptyStackException();
		return stack.remove(size() - 1);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
