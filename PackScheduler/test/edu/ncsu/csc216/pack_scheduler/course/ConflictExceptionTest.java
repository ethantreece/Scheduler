
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the ConflictException class. The parameterized and parameterless
 * constructors are tested to verify that the correct messages are displayed.
 * 
 * @author Jongwoo Shin
 *
 */
class ConflictExceptionTest {

	/**
	 * Tests the parameterized constructor
	 * ConflictException.ConflictException(String message). The correct message
	 * should be the string passed in the parameter.
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Tests the parameterless constructor ConflictException.ConflictException().
	 * The correct message should be "Schedule conflict."
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
