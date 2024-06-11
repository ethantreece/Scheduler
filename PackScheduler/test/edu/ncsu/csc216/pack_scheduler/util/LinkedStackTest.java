/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedStack class
 * @author rosas
 *
 */
class LinkedStackTest {
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#LinkedStack()}.
	 */
	@Test
	void testLinkedStack() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		assertEquals(0, stack.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#push(java.lang.Object)}.
	 */
	@Test
	void testPush() {
		LinkedStack<String> stack = new LinkedStack<String>(3);
		stack.push("Ethan");
		assertEquals(1, stack.size());
		stack.push("Armando");
		stack.push("Camille");
		assertEquals(3, stack.size());
		
		assertThrows(IllegalArgumentException.class, () -> stack.push("test"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	void testPop() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		stack.push("Ethan");
		stack.push("Armando");
		stack.push("Camille");
		assertEquals("Camille", stack.pop());
		assertEquals(2, stack.size());
		stack.push("apple");
		stack.push("orange");
		assertEquals("orange", stack.pop());
		assertEquals("apple", stack.pop());
		assertEquals("Armando", stack.pop());
		assertEquals(1, stack.size());
		assertEquals("Ethan", stack.pop());
		assertEquals(0, stack.size());
		
		assertThrows(EmptyStackException.class, () -> stack.pop());
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#setCapacity(int)}.
	 */
	@Test
	void testSetCapacity() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		assertTrue(stack.isEmpty());
		stack.push("Ethan");
		stack.push("Armando");
		stack.push("Camille");
		stack.push("apple");
		stack.setCapacity(4);
		assertFalse(stack.isEmpty());
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(2));
	}

}
