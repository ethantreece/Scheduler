/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Test the LinkedList class
 * @author crjone24
 *
 */
class LinkedListTest {

	/**
	 * We test the constructor to ensure the size of the LinkedList is 0
	 */
	@Test
	void testLinkedListConstructor() {
		LinkedList<String> test = new LinkedList<String>();
		assertEquals(0, test.size());
	}

	/**
	 * We test adding objects to our LinkedList and check the sizes as we go along we
	 * also check for exceptions that are thrown, to ensure they are thrown
	 */
	@Test
	void testAddObject() {
		LinkedList<String> test = new LinkedList<String>();
		test.add(0, "zero");
		test.add(1, "one");
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
	 * We test removing objects from our LinkedList we check the size of the
	 * LinkedList as we go we also ensure that proper exceptions are thrown
	 */
	@Test
	void testRemoveObject() {
		LinkedList<String> test = new LinkedList<String>();
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
		assertEquals("zero", test.remove(0));
		assertEquals("one", test.get(0));
		assertEquals("nine", test.remove(8));
		assertEquals("three", test.remove(2));
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(8));
	}

	/**
	 * this test tests setting objects to the LinkedList we check the values at
	 * indexes as e go along we ensure that proper exceptions are thrown
	 */
	@Test
	void testSetObject() {
		LinkedList<String> test = new LinkedList<String>();
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
		assertThrows(IndexOutOfBoundsException.class, () -> test.set(100, "test"));
		assertThrows(IndexOutOfBoundsException.class, () -> test.set(-1, "test"));
		assertThrows(NullPointerException.class, () -> test.add(5, null));
		assertThrows(IllegalArgumentException.class, () -> test.set(5, "nine"));
	}

	/**
	 * We test getting our objects from the LinkedList we check the values at indexes
	 * as we go along we ensure that proper exceptions are thrown
	 */
	@Test
	void testGetObject() {
		LinkedList<String> test = new LinkedList<String>();
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
		assertEquals("two", test.get(2));
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(100));
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(-1));
	}
	
	@Test
	void testLinkedListIterator() {
		LinkedList<String> test = new LinkedList<String>();
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
		ListIterator<String> testIterator = test.listIterator(0);
		
		assertThrows(NoSuchElementException.class, () -> testIterator.previous());
		assertThrows(IllegalStateException.class, () -> testIterator.remove());
		assertThrows(IllegalStateException.class, () -> testIterator.set("twenty"));
		
		assertTrue(testIterator.hasNext());
		assertFalse(testIterator.hasPrevious());
		assertEquals("zero", testIterator.next());
		assertTrue(testIterator.hasPrevious());
		assertEquals(0, testIterator.previousIndex());
		assertEquals(1, testIterator.nextIndex());
		assertEquals("zero", testIterator.previous());
		
		assertThrows(NullPointerException.class, () -> testIterator.set(null));
	}

}
