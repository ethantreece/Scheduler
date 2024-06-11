package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Tests the SortedList class.
 * 
 * @author Jongwoo Shin
 * @author Haleema Begum
 * @author Armando Rosas
 *
 */
public class SortedListTest {

	/**
	 * Tests SortedList(). The SortedList should be empty once constructed. The size
	 * of SortedList should grow correctly.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		// Remember the list's initial capacity is 10
		list.add("apple");
		list.add("mango");
		list.add("orange");
		list.add("banana");
		list.add("avacado");
		list.add("pineapple");
		list.add("tomato");
		list.add("grape");
		list.add("strawberry");
		list.add("grapefruit");
		list.add("blackberry");
		assertEquals(11, list.size());
		assertTrue(list.contains("apple"));

	}

	/**
	 * Tests add(). The elements should be added to the list correctly. Checks that
	 * the element is added and sorted in the correct order.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// adding apple to the front, grapefruit to middle, and tomato to the end
		SortedList<String> list2 = new SortedList<String>();
		list2.add("apple");
		list2.add("mango");
		list2.add("orange");
		list2.add("banana");
		list2.add("avacado");
		list2.add("pineapple");
		list2.add("tomato");
		list2.add("grape");
		list2.add("strawberry");
		list2.add("grapefruit");
		list2.add("blackberry");
		assertEquals("apple", list2.get(0));
		assertEquals("grapefruit", list2.get(5));
		assertEquals("tomato", list2.get(10));

		Exception e = assertThrows(NullPointerException.class, () -> list2.add(null));
		assertEquals(null, e.getMessage());

		e = assertThrows(IllegalArgumentException.class, () -> list2.add("apple"));
		assertEquals("Element already in list.", e.getMessage());
	}

	/**
	 * Tests get(). Checks that the index returned is correct position in the list.
	 * Should throw IndexOutOfBoundsException if the index is out of range.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		// Test getting an element from an empty list
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		assertEquals(null, e.getMessage());

		// Add some elements to the list
		list.add("apple");
		list.add("avacodo");
		list.add("banana");

		// Test getting an element at an index < 0
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals(null, e.getMessage());

		// Test getting an element at size
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
		assertEquals(null, e.getMessage());

	}

	/**
	 * Tests remove(). Checks that the element is correctly removed from the
	 * position in the list. Should throw IndexOutOfBoundsException if index is out
	 * of range.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		assertEquals(null, e.getMessage());

		// Add some elements to the list - at least 4
		list.add("apple");
		list.add("avocado");
		list.add("banana");
		list.add("blackberry");

		// Test removing an element at an index < 0
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals(null, e.getMessage());

		// Test removing an element at size
		e = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
		assertEquals(null, e.getMessage());

		// Test removing a middle element
		list.remove(1);
		assertEquals(3, list.size());

		// Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());

		// Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());

		// Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}

	/**
	 * Tests indexOf(). Checks that the index returned is the index of the first
	 * occurrence in the list. Should throw NullPointerException if the specified
	 * element is null.
	 * 
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));

		// Add some elements
		list.add("apple");
		list.add("avocado");
		list.add("banana");
		list.add("blackberry");

		// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("avocado"));
		assertEquals(2, list.indexOf("banana"));
		assertEquals(3, list.indexOf("blackberry"));
		assertEquals(-1, list.indexOf("cherry"));

		// Test checking the index of null
		Exception e = assertThrows(NullPointerException.class, () -> list.indexOf(null));
		assertEquals(null, e.getMessage());
	}

	/**
	 * Tests clear(). Checks that the list is empty after calling clear().
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("apple");
		list.add("avocado");
		list.add("banana");
		list.add("blackberry");
		// Clear the list
		list.clear();
		// Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests isEmpty(). Checks that the right boolean value is returned. Should be
	 * true if list is empty and false if list contains elements.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Test that the list starts empty
		assertTrue(list.isEmpty());
		// Add at least one element
		list.add("watermelon");
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests contains(). Checks that the element is in the list. Should be true if
	 * element exist and false if element doesn't exist.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Test the empty list case
		assertFalse(list.contains("melon"));
		// Add some elements
		list.add("papaya");
		list.add("coconut");
		list.add("kiwi");

		// Test some true and false cases
		assertTrue(list.contains("kiwi"));
		assertTrue(list.contains("papaya"));
		assertTrue(list.contains("coconut"));
		assertFalse(list.contains("pear"));
		assertFalse(list.contains("pomegranate"));

	}

	/**
	 * Tests equals(). Compares two SortedElement lists to see if they are equal if
	 * they contain the same elements in the same order.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("blueberry");
		list1.add("orange");

		list2.add("raspberry");
		list2.add("tangerine");
		list2.add("dragonfruit");

		list3.add("apple");
		list3.add("blueberry");
		list3.add("orange");
		// Test for equality and non-equality
		assertTrue(list1.equals(list3));
		assertFalse(list1.equals(list2));
	}

	/**
	 * Tests hashCode() to see if the hashCodes are generated correctly.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("blueberry");
		list1.add("orange");

		list2.add("raspberry");
		list2.add("tangerine");
		list2.add("dragonfruit");

		list3.add("apple");
		list3.add("blueberry");
		list3.add("orange");
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list3.hashCode());
		assertNotSame(list1.hashCode(), list2.hashCode());
	}

}
