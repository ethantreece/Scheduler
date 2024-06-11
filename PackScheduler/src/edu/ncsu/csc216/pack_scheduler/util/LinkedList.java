package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class handles a doubly-linked list. The ListNode inner class uses a next and previous reference
 * that allows the list to access the ListNode before and after the current ListNode. It uses an iterator
 * to navigate through the LinkedList. The private class for the iterator uses the methods from the parent
 * class to traverse and modify the list. The list checks to make sure that no duplicate objects are 
 * added or set to the list.
 * @author crjone24
 * @param <E> The generic object type that will be given upon instantiation.
 *
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	
	/** Reference to the first ListNode in the LinkedList. A bumper ListNode with null data.*/
	ListNode front;
	/** Reference to the last ListNode in the LinkedList. A bumper ListNode with null data. */
	ListNode back;
	/** The current size of the LinkedList */
	int size;
	
	/**
	 * Constructor for the LinkedList. Sets the front and back ListNodes to a dummy ListNode with 
	 * null data, and makes the front and back reference each other. Sets the size to 0.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		
		front.next = back;
		back.prev = front;
		
		size = 0;
	}
	
	/**
	 * Returns a ListIterator object that will iterate and modify the LinkedList.
	 * @param index The index of the object that will be retrieved and/or modify.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * Adds the incoming element to the index specified in the LinkedList. Checks to see if the element
	 * isn't in the list already, then add it to the LinkedList
	 * @throws IllegalArgumentException If the element is already in the Linked List
	 * @throws IndexOutOfBoundsException If the index is out of bounds.
	 */
	@Override
	public void add(int index, E element) {
		
		if(contains(element)) throw new IllegalArgumentException();
		
		super.add(index, element);
		
	}

	/**
	 * Sets the incoming element to the index specified in the LinkedList. Checks to see if the element
	 * isn't in the list already, then sets it into the LinkedList
	 * @throws IllegalArgumentException If the element is already in the Linked List
	 * @throws IndexOutOfBoundsException If the index is out of bounds.
	 */
	@Override
	public E set(int index, E element) {
		
		if(contains(element)) throw new IllegalArgumentException();
		
		return super.set(index, element);
	}

	/**
	 * Returns the current size of the list from the size field.
	 * @return The size of the Linked List.
	 */
	@Override
	public int size() {
		return size;
	}
	
	private class ListNode {
		/** The data held by the current ListNode */
		E data;
		/** Reference to the next ListNode in the list */
		ListNode next;
		/** Reference to the previous list node in the list */
		ListNode prev;
		
		/**
		 * Constructor for the ListNode class. Sets the data, while setting the references to next and
		 * previous to null.
		 * @param data The data that will be set to the current ListNode.
		 */
		ListNode(E data){
			this.data = data;
			next = null;
			prev = null;
		}
		
		/**
		 * Constructor for the ListNode class. Sets the data, as well as setting the previous and next
		 * nodes to the incoming information.
		 * @param data The data that will be set to the current ListNode.
		 * @param prev The ListNode that will be held as the previous ListNode in the list.
		 * @param next The ListNode that will be held as the next ListNode in the list.
		 */
		ListNode(E data, ListNode prev, ListNode next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private class LinkedListIterator implements ListIterator<E> {

		/** Reference to the ListNode that is at index - 1 of the list. */
		ListNode previous;
		/** Reference to the ListNode that is at index of the list. */
		ListNode next;
		/** The index of the ListNode that is at previous. */
		int previousIndex;
		/** The index of the ListNode that is at next. */
		int nextIndex;
		/** The ListNode that is  */
		ListNode lastRetrieved;
		
		/**
		 * LinkedListIterator object represents the current location of the LinkedListIterator in
		 * the LinkedList. Because we're working with a doubly linked list, a LinkedListIterator can move
		 * forward or backward in the list during traversal. The current location of the LinkedListIterator
		 * should always be between two ListNodes.
		 * @param index index of the LinkedListIterator
		 * @throws IndexOutOfBoundsException if the index is out of bounds
		 */
		LinkedListIterator(int index){
			
			if(index < 0 || index > size) throw new IndexOutOfBoundsException();
			
			previous = front;
			for(int i = 0; i < index; i++) {
				previous = previous.next;
			}
			
			next = previous.next;
			
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}
		
		/**
		 * Returns true if there is a next node in the list. If the next nodes data
		 * is null than false is returned.
		 * @returns true if there's a next node, false if the next nodes data is null
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Moves the LinkedListIterator forward in the list before returning the value
		 * but after retrieving the value to return. Sets lastRetrieved to the ListNode that
		 * contains the returned value. Updates previousIndex and nextIndex to be one higher.
		 * @returns the element in the next ListNode is returned
		 * @throws NoSuchElementException if the next ListNodes data is null
		 */
		@Override
		public E next() {
			if(next.data == null) throw new NoSuchElementException();
			
			E stored = next.data;
			lastRetrieved = next;
			
			next = next.next;
			previous = previous.next;
			
			previousIndex++;
			nextIndex++;
			
			return stored;
		}

		/**
		 * Returns true if there is a previous node in the list. If the previous nodes data
		 * is null than false is returned.
		 * @returns true if there's a previous node, false if the previous nodes data is null
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * Moves the LinkedListIterator backward in the list before returning the value
		 * but after retrieving the value to return. Sets lastRetrieved to the ListNode that
		 * contains the returned value. Updates previousIndex and nextIndex to be one lower.
		 * @returns the element in the previous ListNode is returned
		 * @throws NoSuchElementException if the previous ListNodes data is null
		 */
		@Override
		public E previous() {
			if(previous.data == null) throw new NoSuchElementException();
			
			E stored = previous.data;
			lastRetrieved = previous;
			
			previous = previous.prev;
			next = next.prev;
			
			previousIndex--;
			nextIndex--;
			
			return stored;
		}

		/**
		 * Gets the index of next ListNode.
		 * @returns the index of the next ListNode
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Gets the index of the previous ListNode.
		 * @returns the index of the previous ListNode
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the element last returned by the last call to previous() or
		 * next(). This means that the lastRetrieved ListNode is removed, and 
		 * the size is decremented.
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) throw new IllegalStateException();
			
			lastRetrieved.prev.next = lastRetrieved.next;
			lastRetrieved.next.prev = lastRetrieved.prev;
		
			
			lastRetrieved = null;
			
			
			
			size--;
		}

		/**
		 * Modifies the element returned by the last call to previous() or next().
		 * This means that the lastRetrieved ListNodes data is changed to the data in
		 * the parameters. lastRetrieved is then set to null.
		 * @param data element that's replacing the lastRetrieved ListNodes data
		 * @throws IllegalArgumentException if the data parameter is null
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void set(E data) {
			if(lastRetrieved == null) throw new IllegalStateException();
			if(data == null) throw new NullPointerException();
			
			
			lastRetrieved.data = data;
			lastRetrieved = null;
		}

		/**
		 * Inserts the element before the element that would be returned by
		 * next(). Size is then incremented. lastRetrieved is set to null.
		 * previousIndex and nextIndex are also incremented. 
		 * @param data element that is added
		 * @throws NullPointerException if data is null
		 */
		@Override
		public void add(E data) {
			if(data == null) throw new NullPointerException();

			previous.next = new ListNode(data, previous, next);
			next.prev = previous.next;
			
			previous = previous.next;
			
			previousIndex++;
			nextIndex++;
			
			lastRetrieved = null;
			size++;
		}
		
	}
	
}
