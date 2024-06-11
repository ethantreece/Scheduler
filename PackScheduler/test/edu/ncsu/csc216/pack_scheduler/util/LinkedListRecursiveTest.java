/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the LinkedListRecursive class. Uses LinkedLists tests to test if the LinkedListRecursive 
 * has all of the functionality of a LinkedList, although it is done differently. Calls the public methods for
 * both adds, both removes, set, get, and contains to make sure that they all function properly.
 * @author Armando rosas
 *
 */
class LinkedListRecursiveTest {

	/**
	 * We test the constructor to ensure the size of the LinkedListRecursive is 0
	 */
	@Test
	void testLinkedListRecursiveConstructor() {
		LinkedListRecursive<String> test = new LinkedListRecursive<String>();
		assertEquals(0, test.size());
		assertFalse(test.contains("Apple"));
	}

	/**
	 * We test adding objects to our LinkedListRecursive and check the sizes as we go along we
	 * also check for exceptions that are thrown, to ensure they are thrown
	 */
	@Test
	void testAddObject() {
		LinkedListRecursive<String> test = new LinkedListRecursive<String>();
		test.add("zero");
		test.add("one");
		assertTrue(test.contains("zero"));
		assertTrue(test.contains("zero"));
		test.add(2, "two");
		test.add(3, "three");
		test.add(4, "four");
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(5));
		assertEquals("two", test.get(2));
		test.add(5, "five");
		test.add(6, "six");
		test.add(7, "seven");
		test.add(8, "eight");
		test.add(9, "nine");
		test.add(10, "ten");
		assertEquals("ten", test.get(10));
		assertEquals(11, test.size());
		assertThrows(IndexOutOfBoundsException.class, () -> test.add(100, "test"));
		assertThrows(IndexOutOfBoundsException.class, () -> test.add(-1, "test"));
	}

	/**
	 * We test removing objects from our LinkedListRecursive we check the size of the
	 * LinkedListRecursive as we go we also ensure that proper exceptions are thrown
	 */
	@Test
	void testRemoveObject() {
		LinkedListRecursive<String> test = new LinkedListRecursive<String>();
		test.add(0, "zero");
		test.add(1, "one");
		test.add(2, "two");
		test.add(3, "three");
		test.add(4, "four");
		test.add(5, "five");
		test.add(6, "six");
		test.add(7, "seven");
		test.add(8, "eight");
		test.add(9, "nine");
		assertTrue(test.remove("zero"));
		assertTrue(test.remove("one"));
		assertTrue(test.remove("two"));
		assertEquals("three", test.get(0));
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(8));
		assertEquals("three", test.remove(0));
		assertEquals("nine", test.remove(5));
		assertTrue(test.remove("seven"));
		assertEquals(4, test.size());
	}

	/**
	 * this test tests setting objects to the LinkedListRecursive we check the values at
	 * indexes as e go along we ensure that proper exceptions are thrown
	 */
	@Test
	void testSetObject() {
		LinkedListRecursive<String> test = new LinkedListRecursive<String>();
		test.add(0, "zero");
		test.add(1, "one");
		test.add(2, "two");
		test.add(3, "three");
		test.add(4, "four");
		test.add(5, "five");
		test.add(6, "six");
		test.add(7, "seven");
		test.add(8, "eight");
		test.add(9, "nine");
		assertEquals("two", test.set(2, "new Value"));
		assertEquals("new Value", test.get(2));
		assertEquals("zero", test.set(0, "new zero"));
		assertThrows(IndexOutOfBoundsException.class, () -> test.set(100, "test"));
		assertThrows(IndexOutOfBoundsException.class, () -> test.set(-1, "test"));
		assertThrows(NullPointerException.class, () -> test.add(5, null));
		assertThrows(IllegalArgumentException.class, () -> test.set(5, "nine"));
	}

	/**
	 * We test getting our objects from the LinkedListRecursive we check the values at indexes
	 * as we go along we ensure that proper exceptions are thrown
	 */
	@Test
	void testGetObject() {
		LinkedListRecursive<String> test = new LinkedListRecursive<String>();
		test.add(0, "zero");
		test.add(1, "one");
		test.add(2, "two");
		test.add(3, "three");
		test.add(4, "four");
		test.add(5, "five");
		test.add(6, "six");
		test.add(7, "seven");
		test.add(8, "eight");
		test.add(9, "nine");
		assertEquals("zero", test.get(0));
		assertEquals("one", test.get(1));
		assertEquals("two", test.get(2));
		assertEquals("three", test.get(3));
		assertEquals("four", test.get(4));
		assertEquals("five", test.get(5));
		assertEquals("six", test.get(6));
		assertEquals("seven", test.get(7));
		assertEquals("eight", test.get(8));
		assertEquals("nine", test.get(9));
		
		assertEquals(10, test.size());
		
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(10));
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(100));
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(-1));
		
		assertEquals("nine", test.remove(9));
		assertEquals(9, test.size());
		
		assertTrue(test.remove("eight"));
		assertEquals(8, test.size());
		
		assertFalse(test.remove("eight"));
		assertEquals(8, test.size());
		
		assertEquals("zero", test.get(0));
		assertEquals("one", test.get(1));
		assertEquals("two", test.get(2));
		assertEquals("three", test.get(3));
		assertEquals("four", test.get(4));
		assertEquals("five", test.get(5));
		assertEquals("six", test.get(6));
		assertEquals("seven", test.get(7));
		
		test.add("eight");
		
		assertEquals(9, test.size());
		
		assertEquals("zero", test.get(0));
		assertEquals("one", test.get(1));
		assertEquals("two", test.get(2));
		assertEquals("three", test.get(3));
		assertEquals("four", test.get(4));
		assertEquals("five", test.get(5));
		assertEquals("six", test.get(6));
		assertEquals("seven", test.get(7));
		assertEquals("eight", test.get(8));
		
		assertEquals("zero", test.remove(0));
		assertEquals("one", test.get(0));
		assertEquals(8, test.size());
		
		assertEquals("one", test.get(0));
		assertEquals("two", test.get(1));
		assertEquals("three", test.get(2));
		assertEquals("four", test.get(3));
		assertEquals("five", test.get(4));
		assertEquals("six", test.get(5));
		assertEquals("seven", test.get(6));
		assertEquals("eight", test.get(7));
		
		test.add(0, "zero");
		assertEquals("zero", test.get(0));
		assertEquals(9, test.size());
		
		assertEquals("zero", test.get(0));
		assertEquals("one", test.get(1));
		assertEquals("two", test.get(2));
		assertEquals("three", test.get(3));
		assertEquals("four", test.get(4));
		assertEquals("five", test.get(5));
		assertEquals("six", test.get(6));
		assertEquals("seven", test.get(7));
		assertEquals("eight", test.get(8));
		
		
		
		LinkedListRecursive<Integer> test2 = new LinkedListRecursive<Integer>();
		test2.add(0, 0);
		test2.add(1, 1);
		test2.add(2, 2);
		test2.add(3, 3);
		assertEquals(0, test2.get(0));
		assertEquals(1, test2.get(1));
		assertEquals(2, test2.get(2));
		assertEquals(3, test2.get(3));
	}

}
