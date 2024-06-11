/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * A class that throws an exception for an invalid transition per the FSM
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
public class InvalidTransitionException extends Exception {

	/**
	 * Default serialization value for InvalidTransitionException
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor for InvalidTransitionException class
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
	/**
	 * constructor for invalid transition exception with custom message
	 * @param message is the custom message
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
}
