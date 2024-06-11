/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests LinkedAbstractList
 * 
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
public class LinkedAbstractListTest {

	/**
	 * We test the constructor to ensure the size of the ArrayList is 0
	 */
	@Test
	public void testlinkedListConstructor() {
		LinkedAbstractList<String> test = new LinkedAbstractList<String>(2);
		assertEquals(0, test.size());
		assertThrows(IllegalArgumentException.class,
				() -> new LinkedAbstractList<String>(-2));
	}
	
	/**
	 * We test adding objects to our ArrayList and check the sizes as we go along we
	 * also check for exceptions that are thrown, to ensure they are thrown
	 */
	@Test
	public void testAddElement() {
		LinkedAbstractList<String> test = new LinkedAbstractList<String>(11);
		assertThrows(NullPointerException.class,
				() -> test.add(0, null));
		test.add(0, "zero");
		test.add(1, "one");
		test.add(2, "two");
		test.add(3, "three");
		test.add(4, "four");
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(5));
		assertThrows(IllegalArgumentException.class,
				() -> test.add(5, "four"));
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
		assertThrows(IllegalArgumentException.class, 
				() -> test.add(11, "eleven"));
		
		test.remove(10);
		test.add(4, "three and a half");
		
		LinkedAbstractList<String> test2 = new LinkedAbstractList<String>(10);
		test2.add(0, "apple");
		test2.add(0, "orange");
		test2.add(1, "banana");
		test2.add(3, "kiwi");
		assertEquals(4, test2.size());
		assertEquals("orange", test2.get(0));
		assertEquals("banana", test2.get(1));
		assertEquals("apple", test2.get(2));
		assertEquals("kiwi", test2.get(3));
		
	}
	
	/**
	 * We test removing objects from our ArrayList we check the size of the
	 * ArrayList as we go we also ensure that proper exceptions are thrown
	 */
	@Test
	public void testRemoveElement() {
		LinkedAbstractList<String> test = new LinkedAbstractList<String>(10);
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
		test.remove(0);
		assertEquals("one", test.get(0));
		assertEquals(9, test.size());
		test.remove(3);
		assertEquals("five", test.get(3));
		assertEquals(8, test.size());
		test.remove(7);
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(7));
		assertThrows(IndexOutOfBoundsException.class, () -> test.remove(7));
		assertThrows(IndexOutOfBoundsException.class, () -> test.remove(-21));
	}

	/**
	 * this test tests setting objects to the arrayList we check the values at
	 * indexes as e go along we ensure that proper exceptions are thrown
	 */
	@Test
	public void testSetElement() {
		LinkedAbstractList<String> test = new LinkedAbstractList<String>(10);
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
	 * We test getting our objects from the ArrayList we check the values at indexes
	 * as we go along we ensure that proper exceptions are thrown
	 */
	@Test
	public void testGetElement() {
		LinkedAbstractList<String> test = new LinkedAbstractList<String>(10);
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
	
	/**
	 * Tests the setCapacity method in the LinkedAbstractList class
	 */
	@Test
	public void testSetCapacity() {
		
		LinkedAbstractList<String> test = new LinkedAbstractList<String>(10);
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
		
		assertThrows(IllegalArgumentException.class, () -> test.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> test.setCapacity(5));
		
	}

}
