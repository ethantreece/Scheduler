package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * The ArrayListTest class tests all methods in our ArrayList through a variety
 * of invalid inputs inputs that throw exceptions we use assertEquals to ensure
 * our tests are running as expected
 * 
 * @author Kiran
 * @author Armando
 * @author Kennedy
 *
 */
class ArrayListTest {

	/**
	 * We test the constructor to ensure the size of the ArrayList is 0
	 */
	@Test
	void testArrayListConstructor() {
		ArrayList<String> test = new ArrayList<String>();
		assertEquals(0, test.size());
	}

	/**
	 * We test adding objects to our ArrayList and check the sizes as we go along we
	 * also check for exceptions that are thrown, to ensure they are thrown
	 */
	@Test
	void testAddObject() {
		ArrayList<String> test = new ArrayList<String>();
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
	 * We test removing objects from our ArrayList we check the size of the
	 * ArrayList as we go we also ensure that proper exceptions are thrown
	 */
	@Test
	void testRemoveObject() {
		ArrayList<String> test = new ArrayList<String>();
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
		test.remove(8);
		assertThrows(IndexOutOfBoundsException.class, () -> test.get(8));
	}

	/**
	 * this test tests setting objects to the arrayList we check the values at
	 * indexes as e go along we ensure that proper exceptions are thrown
	 */
	@Test
	void testSetObject() {
		ArrayList<String> test = new ArrayList<String>();
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
	void testGetObject() {
		ArrayList<String> test = new ArrayList<String>();
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

}
