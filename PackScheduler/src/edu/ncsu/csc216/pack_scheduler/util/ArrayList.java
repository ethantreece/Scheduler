/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom ArrayList class
 * @param <E> object class that arraylist will hold
 * 
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
public class ArrayList<E> extends AbstractList<E> {

	/** Inital size of array */
	private static final int INIT_SIZE = 10;

	/** Array list generic objects */
	private E[] list;

	/** Number of actual objects in a array */
	private int size;

	/** Constructor for ArrayList*/
	@SuppressWarnings("unchecked")
	public ArrayList() {
		//E item = (E) new Object();
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}

	/** Getter methods for an object in the array 
	 * @param index int of object to retrieve
	 * @throws IndexOutOfBoundsException if out of size range
	 * @return object matching index
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Getter for size
	 * @return int size of objects in array
	 */
	@Override
	public int size() {
		return size;
	}

	/** Set an index to a certain object
	 * @param index int of where to insert object
	 * @param element object to add
	 * @throws IndexOutOfBoundsException if out of size range
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is duplicate
	 * @return old element that was replaced
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (element == list[i]) {
				throw new IllegalArgumentException();
			}
		}
		E oldObject = list[index];
		list[index] = element;
		return oldObject;
	}

	/** Add item to array at a certain index
	 * @param index int of where to insert object
	 * @param element object to add
	 * @throws IndexOutOfBoundsException if out of size range
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is duplicate
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (element == list[i]) {
				throw new IllegalArgumentException();
			}
		}
		if (index != list.length && list[index] != null) {
			if (list[list.length - 1] == null) {
				for (int i = list.length - 1; i > index; i--) {
					list[i] = list[i - 1];
				}
			} else {
				growArray();
				for (int i = size - 1; i > index; i--) {
					list[i] = list[i - 1];
				}
			}
		} else if (index == list.length){
			growArray();
		}
		list[index] = element;
		size++;
	}

	/** Grows array when size surpasses array length */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newList;
		//E item = (E) new Object();
		newList = (E[]) new Object[size * 2];
		for (int i = 0; i < size; i++) {
			newList[i] = list[i];
		}
		list = newList;
	}

	/** Remove object at an index
	 * @param index int of where to remove object
	 * @throws IndexOutOfBoundsException if out of size range
	 * @return removedObject object that was removed
	 */
	@Override
	public E remove(int index) {
		E removedObject = list[index];
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = index; i < size; i++) {
			if (( i + 1) < size) {
				list[i] = list[i + 1];
			} else {
				list[i] = null;
				break;
			}
		}
		size--;
		return removedObject;
	}

}
