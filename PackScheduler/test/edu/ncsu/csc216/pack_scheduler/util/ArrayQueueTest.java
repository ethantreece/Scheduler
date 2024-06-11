/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Tests the ArrayQueue class
 * @author Ethan
 *
 */
class ArrayQueueTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#ArrayQueue()}.
	 */
	@Test
	void testArrayQueue() {
		ArrayQueue<String> array = new ArrayQueue<String>(10);
		assertEquals(0, array.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	void testEnqueueAndDequeue() {
		ArrayQueue<String> array = new ArrayQueue<String>(10);
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
		
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#setCapacity(int)}.
	 */
	@Test
	void testSetCapacity() {
		ArrayQueue<String> array = new ArrayQueue<String>(10);
		array.enqueue("Apple");
		array.enqueue("Banana");
		array.enqueue("Dog");
		array.enqueue("Test");
		
		array.setCapacity(4);
		
		assertThrows(IllegalArgumentException.class, () -> array.enqueue("fail"));
		assertThrows(IllegalArgumentException.class, () -> array.setCapacity(2));
	}

}
