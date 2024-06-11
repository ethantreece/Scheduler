/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Tests the ArrayStack class
 * @author Armando rosas
 *
 */
class ArrayStackTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#ArrayStack()}.
	 */
	@Test
	void testArrayStack() {
		ArrayStack<String> arrayStackTest = new ArrayStack<String>(10);
		assertEquals(0, arrayStackTest.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	void testPush() {
		ArrayStack<String> arrayStackTest = new ArrayStack<String>(3);
		arrayStackTest.push("Ethan");
		assertEquals(1, arrayStackTest.size());
		arrayStackTest.push("Armando");
		arrayStackTest.push("Camille");
		assertEquals(3, arrayStackTest.size());
		
		assertThrows(IllegalArgumentException.class, () -> arrayStackTest.push("test"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#pop()}.
	 */
	@Test
	void testPop() {
		ArrayStack<String> arrayStackTest = new ArrayStack<String>(10);
		arrayStackTest.push("Ethan");
		arrayStackTest.push("Armando");
		arrayStackTest.push("Camille");
		assertEquals("Camille", arrayStackTest.pop());
		assertEquals(2, arrayStackTest.size());
		arrayStackTest.push("apple");
		arrayStackTest.push("orange");
		assertEquals("orange", arrayStackTest.pop());
		assertEquals("apple", arrayStackTest.pop());
		assertEquals("Armando", arrayStackTest.pop());
		assertEquals(1, arrayStackTest.size());
		assertEquals("Ethan", arrayStackTest.pop());
		assertEquals(0, arrayStackTest.size());
		
		assertThrows(EmptyStackException.class, () -> arrayStackTest.pop());
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#setCapacity(int)}.
	 */
	@Test
	void testSetCapacity() {
		ArrayStack<String> arrayStackTest = new ArrayStack<String>(10);
		assertTrue(arrayStackTest.isEmpty());
		arrayStackTest.push("Ethan");
		arrayStackTest.push("Armando");
		arrayStackTest.push("Camille");
		arrayStackTest.push("apple");
		arrayStackTest.setCapacity(4);
		assertFalse(arrayStackTest.isEmpty());
		assertThrows(IllegalArgumentException.class, () -> arrayStackTest.setCapacity(2));
	}

}
