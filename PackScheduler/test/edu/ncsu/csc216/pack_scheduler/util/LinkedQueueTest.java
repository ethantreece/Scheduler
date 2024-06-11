/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedQueue class
 * @author Ethan
 *
 */
class LinkedQueueTest {

	/**
	 * Test LinkedQueue() constructor
	 */
	@Test
	void testLinkedQueue() {
		LinkedQueue<String> array = new LinkedQueue<String>(10);
		assertEquals(0, array.size());
	}

	/**
	 * Test enqueue() and dequeue() methods
	 */
	@Test
	void testEnqueueAndDequeue() {
		LinkedQueue<String> array = new LinkedQueue<String>(10);
		array.enqueue("Apple");
		array.enqueue("Banana");
		array.enqueue("Dog");
		assertEquals(3, array.size());
		assertEquals("Apple", array.dequeue());
		assertEquals("Banana", array.dequeue());
		assertEquals(1, array.size());
		assertFalse(array.isEmpty());
		
		assertEquals("Dog", array.dequeue());
		assertEquals(0, array.size());
		assertTrue(array.isEmpty());
		
		assertThrows(NoSuchElementException.class, () -> array.dequeue());
		
		LinkedQueue<String> array2 = new LinkedQueue<String>(10);
		array2.enqueue("Apple");
		array2.enqueue("Banana");
		array2.enqueue("Dog");
		array2.enqueue("Test");
		
		array2.setCapacity(4);
		
		assertThrows(IllegalArgumentException.class, () -> array2.enqueue("fail"));
		assertThrows(IllegalArgumentException.class, () -> array2.setCapacity(2));
		
		assertThrows(IllegalArgumentException.class, () -> array2.contains(null));
		assertTrue(array2.contains("Apple"));
		assertTrue(array2.contains("Banana"));
		assertTrue(array2.contains("Dog"));
		assertTrue(array2.contains("Test"));
		assertFalse(array2.contains("Zebra"));
		
	}
	
	/**
	 * Test setCapacity() method
	 */
	@Test
	void testSetCapacity() {
		LinkedQueue<String> array = new LinkedQueue<String>(10);
		array.enqueue("Apple");
		array.enqueue("Banana");
		array.enqueue("Dog");
		array.enqueue("Test");
		
		array.setCapacity(4);
		
		assertThrows(IllegalArgumentException.class, () -> array.enqueue("fail"));
		assertThrows(IllegalArgumentException.class, () -> array.setCapacity(2));
	}

}
