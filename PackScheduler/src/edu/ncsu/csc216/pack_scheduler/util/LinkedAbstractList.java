/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom LinkedList for course roll This class extends the AbstractList it
 * uniquely implements the 5 methods to add elements to the list, we have the
 * add method to remove, we have the remove method, to set, we have the set
 * method
 * 
 * @param <E> object class that linkedlist will hold
 * 
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** Reference to first item in linkedlist */
	private ListNode front;
	/** This is a reference to the last node in the list */
	private ListNode back;
	/** size is the size of the list */
	private int size;
	/** capacity is the capacity of the list */
	private int capacity;

	/**
	 * Constructor for LinkedList
	 * 
	 * @param capacity cap of list
	 */
	public LinkedAbstractList(int capacity) {
		this.front = null;
		this.size = 0;
		if (capacity >= 0) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException();
		}
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public E get(int index) {
		ListNode current = front;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int index, E element) {
		ListNode currentElement = front;
		if (element == null)
			throw new NullPointerException();
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();

		for (int i = 0; i < size; i++) {
			if (currentElement.data.equals(element))
				throw new IllegalArgumentException();
			currentElement = currentElement.next;
		}

		if (this.size == this.capacity)
			throw new IllegalArgumentException();

		if (index == 0) {
			ListNode temp = front;
			front = new ListNode(element, temp);
			if (size == 0) {
				back = front;
			} else {
				back = front;
				for (int i = 0; i < size; i++) {
					back = back.next;
				}
			}
		} else if (index == size) {
			back.next = new ListNode(element);
			back = back.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}

			current.next = new ListNode(element, current.next);

		}
		size++;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E value = null;
		if (index == 0) {
			value = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
			if (index == size - 1) {
				back = current;
			}
		}
		size--;
		return value;
	}

	@Override
	public E set(int index, E element) {
		ListNode current = front;
		E value;
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		if (index == 0) {
			value = front.data;
			front.data = element;
		} else {
			current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			value = current.data;
			current.data = element;

		}
		return value;
	}

	private class ListNode {

		/** data object of list node */
		public E data;

		/** Reference to the next list node in list */
		public ListNode next;

		ListNode(E data) {
			this.data = data;
			this.next = null;
		}

		ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

	}

	/**
	 * Sets the capacity of the linked list after error checking
	 * 
	 * @param capacity is the capacity of the list to be checked and set
	 * @throws IllegalArgumentException if the capacity is negative or if it is lees than the list's size
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
