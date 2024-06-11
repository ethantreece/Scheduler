
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Represents the custom exception ConflictException. The exception is thrown
 * when there is a conflict in meeting days and times between two activities.
 * ConflictException should ultimately be caught and handled instead of reaching
 * the user.
 * 
 * @author Jongwoo Shin
 *
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to throw ConflictException with the specified message.
	 * 
	 * @param message specified message to be passed
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Constructor to throw ConflictException with the default message "Schedule
	 * conflict".
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
