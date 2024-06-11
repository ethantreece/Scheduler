package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A test class for InvlaidTransitionException
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Test method for InvalidTransitionException default constructor.
	 */
	@Test
	void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for InvalidTransitionException default constructor.
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException ce = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ce.getMessage());
	}
}
