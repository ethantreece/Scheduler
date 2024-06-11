/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedStack impelements the Stack list with an object E. The Stack methods are
 * then overwritten to provide custom implementation for LinkedStack.
 * @author Armando
 * @author Ethan
 * @author Camille
 * @param <E> arbitrary object of generic type
 *
 */
public class LinkedStack<E> implements Stack<E> {

	/** Stack of generic type E */
	LinkedAbstractList<E> stack;
	
	/**
	 * Constructs the LinkedStack object with a given capacity
	 * @param capacity capacity
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<E>(capacity);
	}

	@Override
	public void push(E element) {
		stack.add(0, element);
	}

	@Override
	public E pop() {
		if(stack.size() == 0) throw new EmptyStackException();
		return stack.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
	}

}
