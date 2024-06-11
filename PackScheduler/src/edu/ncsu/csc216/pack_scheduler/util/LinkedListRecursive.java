/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This class is a list of linked nodes that is handled recursively. Traversal through the linked list
 * consists of calling on the ListNode inner class's methods. The LinkedListRecursive class uses public 
 * classes to call recursive inner classes. The public classes handle special cases and checks before 
 * giving the information to the ListNode's methods. The ListNode's methods use recursion in order 
 * to get information, add elements, set elements, remove elements, and check if an element is in the list.
 * @author Armando rosas
 * @author crjone24
 * @param <E> The generic type that will be held within the linked list.
 */
public class LinkedListRecursive<E> {
	/** The first list node in the linked list */
	private ListNode front;
	/** The amount of nodes in the linked list */
	private int size;
	
	/**
	 * Constructor for the recursive linked list. Instantiates the front of the list 
	 * as null and the size as zero.
	 */
	public LinkedListRecursive() {
		this.front = null;
		this.size = 0;
	}

	/**
	 * Returns if the linked list currently has no nodes by checking the size of the list.
	 * @return True if the linked list is empty, false if it is not.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the current amount of nodes in the linked list. 
	 * @return The size of the linked list.
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * A public method for the add method. Takes in an element and if it is not null or already
	 * in the list, then it will add it to the list. It will either do this by adding it to the 
	 * front ListNode if the front is null, or it will delegate to the ListNode private add method
	 * to add the element recursively to the end of the list. Size is incremented.
	 * @param element The data that will be added to the end of the list.
	 * @throws NullPointerException If the incoming element is null.
	 * @throws IllegalArgumentException If the element is a duplicate of an element already in the list.
	 * @return True if the data was able to be added, false if it was unable to be added.
	 */
	public boolean add(E element) {
		
		if(element == null) {
			throw new NullPointerException("element is null");
		}
		if(contains(element)) {
			throw new IllegalArgumentException("cannot add duplicate");
		}
		else if (size == 0) {
			front = new ListNode(element, null);
			size++;
		}
		else {
			front.add(element);
			size++;
		}
		return true;
	}
	
	/**
	 * A public method for the add method. Takes in an element and and index, and if it is not null, if the element is already
	 * in the list, or if the index is out of bounds then it will add it to the list at the specified index. It 
	 * will either do this by adding it to the front ListNode if the front is null, or it will delegate to the 
	 * ListNode private add method to add the element recursively at the index that was given. Size is incremented.
	 * @param index The index where the element is to be added.
	 * @param element The element that is to be added to the list.
	 * @throws NullPointerException If the incoming element is null.
	 * @throws IllegalArgumentException If the element is a duplicate of an element already in the list.
	 * @throws IndexOutOfBoundsException If the incoming index is not within the bounds of 0 and size - 1.
	 */
	public void add(int index, E element) {
		if(element == null) {
			throw new NullPointerException("element is null");
		}
		if(contains(element)) {
			throw new IllegalArgumentException("cannot add duplicate");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		if (index == 0 || size == 0) {
			front = new ListNode(element, front);
			size++;
		}
		else {
			front.add(index, element);
			size++;
		}
	}
	
	/**
	 * A public method for the get method. Takes in an index and if it is in bounds then it will get 
	 * the item from the list. It will delegate to the ListNode private get method to iterate through
	 * the list.
	 * @param index The index of the item that the caller is trying to get.
	 * @return The item that the caller was trying to retrieve.
	 * @throws IndexOutOfBoundsException If the incoming index is not within the bounds of 0 and size - 1.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		if (index == 0) {
			return front.data;
		}
		return front.get(index);
	}
	
	/**
	 * A public method for the remove method. Takes in an element and if it is not null or if the list is not
	 * empty, then the element is removed from the list. This is either done by deleting the element at the front
	 * and making the front equal its next ListNode if the element is in the front node, or by delegating to the 
	 * ListNode private remove method to iterate through the list and remove the element's node. Size is decremented.
	 * @param element The element to be removed from the list.
	 * @return True if the element was able to be removed, false if the element was null, the list was empty, or if the 
	 * element wasn't on the list.
	 */
	public boolean remove(E element) {
		if(element == null) {
			//throw new NullPointerException("element is null");
			return false;
		}
		if (isEmpty()) {
			return false;
		}
		if (element == front.data) {
			front = front.next;
			size--;
			return true;
		}
		
		if(front.remove(element)) {
			size--;
			return true;
		} else {
			return false;
		}
//		size--;
//		return front.remove(element);
	}
	
	/**
	 * A public method for the remove method. Takes in an index and if it is not out of bounds, then the 
	 * element is removed from the list. This is either done by deleting the node in the front ListNode
	 * and making the front equal its next ListNode if the index is equal to zero, or by delegating 
	 * to the ListNode private remove method to iterate through the list and remove the element's node.
	 * Size is decremented.
	 * @param index The index of the node that will be removed from the list.
	 * @throws IndexOutOfBoundsException If the incoming index is not within the bounds of 0 and size - 1.
	 * @return The element at the index that was removed from the list.
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		if (index == 0) {
			E returnObject = front.data;
			front = front.next;
			size--;
			return returnObject;
		}
		size--;
		return front.remove(index);
	}
	
	/**
	 * A public method for the set method. Takes in an index and an element, and if the element isn't null, the list
	 * isn't empty, and the index isn't out of bounds, the item gets set into the linked list. It does this by either
	 * setting the front data to the element if the index is 0, or delegating to the ListNode private set method.
	 * @param index The index of the node where the element is to be set.
	 * @param element The element to be set in the list.
	 * @throws IndexOutOfBoundsException If the incoming index is not within the bounds of 0 and size - 1.
	 * @throws NullPointerException If the incoming element is null.
	 * @throws IllegalArgumentException If the element is a duplicate of an element already in the list.
	 * @return The element that used to be at the index specified.
	 */
	public E set(int index, E element) {
		if(element == null) {
			throw new NullPointerException("element is null");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("duplicate");
		}
		if (index == 0) {
			E returnObject = front.data;
			front.data = element;
			return returnObject;
		} 
		else {
			return front.set(index, element);
		}
	}
	
	/**
	 * A public method for the contains method. Takes in an element, and if the element isn't null and if the 
	 * list isn't empty, look through the list for the element. It will do this by delegating to the ListNode 
	 * private contains method. If the item is in the list, the method returns true, and if it can't be found, 
	 * if the list is empty, or if the element is null then it returns false.
	 * @param element The element being searched for in the list.
	 * @return True if the element is in the list and False if the element is null, if the list is empty, or 
	 * if the element couldn't be found in the list.
	 */
	public boolean contains(E element) {
		if(front == null) {
			return false;
		}
		if(element == null) {
			return false;
		}
		
		return front.contains(element);
	}
	
	/**
	 * A private class for the ListNode class. This class works to hold data, as well as a reference to the next list node, 
	 * creating a linked list. These ListNodes work recursively, with the methods using references to themselves in order to
	 * traverse through the list. This class allows the linked list to be added to, removed from, have an element retrieved, 
	 * have and element set, and see if an element exists in the list. 
	 * @author crjone24
	 * @author ethan
	 * @author Armando rosas
	 *
	 */
	private class ListNode {
		/** An element that is held in the list node, of a generic type E that will be set upon instantiation. */
		E data;
		/** Reference to the next list node in the series, allowing for a list of linked list nodes. */
		ListNode next;
		
		/**
		 * Private recursive method for the add function. Checks to see if the incoming index is 1, and if it is then
		 * it adds the element right after the current element. If index isn't one, the index is decreased
		 * and the method is called again until the index is 1.
		 * @param index The incoming index, indicating how far the element is from being at the correct 
		 * place in the list.
		 * @param element The element that is to be added to the list.
		 */
		void add(int index, E element) {
			if (index == 1) {
				next = new ListNode(element, next);
			} else {
				next.add(index - 1, element);
			}
		}
		
		/**
		 * Private recursive method for the add function. Checks to see if there isn't another list node in the list and 
		 * places the element there if there isn't and returns true. If there is, then the method is called again until 
		 * there isn't another next element.
		 * @param element The element that is getting added to the list.
		 * @return True if the element was added to the list.
		 */
		boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				return true;
			} else {
				return next.add(element);
			}
		}
		
		/**
		 * Private recursive method for the get function. Checks to see if the index is 0, and if it is then the data at the 
		 * current node is returned. If it isn't, then the method is called again with the next list node until the index 
		 * reaches 0.
		 * @param index The index of the item that is being retrieved. Tells the method how far away it is from the desired item until it reaches 0.
		 * @return The element that was at the initially specified index.
		 */
		E get(int index) {
			if (index == 1) {
				return next.data;
			}
			return next.get(index - 1);
		}
		
		/**
		 * Private recursive method for the remove function. Checks to see if the index is 1, and if it is then reference to next
		 * becomes what was at next.next, essentially deleting the reference to was was previously at next from the list and thus 
		 * removing it from the list. If the index is not 1, then the method is called again with the next list node until the index
		 * reaches 0.
		 * @param index The index of the item that is being removed. Tells the method how far away it is from the desired item until it reaches 0.
		 * @return The element that was removed from the list.
		 */
		E remove(int index) {
			if (index == 1) {
				E temp = next.data;
				next = next.next;
				return temp;
			} else {
				return next.remove(index - 1);
			}
		}
		
		/**
		 * Private recursive method for the remove function. Checks to see if there isn't anything in the list node's next field, and if 
		 * there isn't, then it returns false, as the element wasn't in the list to remove. Otherwise, it checks to see if the data at 
		 * next matches the element that is to be deleted. If so, then It replaces the reference to that specific node with the node after it
		 * essentially deleting the element from list, and it returns true. If neither of these are true, the method is called again for the 
		 * next list node until the next field is null or the element is found and removed.
		 * @param element The element to be removed from the list. 
		 * @return True if the element was able to be removed, False if the element didn't exist in the list.
		 */
		boolean remove(E element) {
			if (next == null) {
				return false;
			}
			if (next.data == element) {
				this.next = next.next;
				return true;
			} else {
				return next.remove(element);
			}
		}
		
		/**
		 * Private recursive method for the set function. Checks to see if the index is 0, and if it is then the data at the 
		 * current node is returned and the incoming element is set in its place. If it isn't, then the method is called again with
		 * the next list node until the index reaches 0.
		 * @param index The index where the element will be set. Tells the method how far away it is from the desired place until it reaches 0.
		 * @param element The element that will replace an element already in the list.
		 * @return The element that was at the initially at the specified index before being replaced.
		 */
		E set(int index, E element) {
			if (index == 0) {
				E temp = data;
				data = element;
				return temp;
			} else {
				return next.set(index - 1, element);
			}
		}
		
		/**
		 * Private recursive method for the contains function. it checks to see if the data at next matches the element that 
		 * is being checked for. If so, then the method returns true. Otherwise, it checks to see if there isn't anything in 
		 * the list node's next field, and if there isn't, then it returns false, as the element wasn't in the list. If neither 
		 * of these are true, the method is called again for the next list node until the next field is null or the element is 
		 * found in the list.
		 * @param element The element that is being checked for in the list.
		 * @return True if the element was in the list, False if the element didn't exist in the list.
		 */
		boolean contains(E element) {
			if (data == element) {
				return true;
			} else if (next == null) {
				return false;
			} else {
				return next.contains(element);
			}
		}
		
		/**
		 * Constructor for the ListNode class. Initializes the data as the incoming data, and the 
		 * next field as the incoming list node.
		 * @param data The data that the list node will reference.
		 * @param next The next list node that will be referenced by the list node being constructed. 
		 */
		ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
