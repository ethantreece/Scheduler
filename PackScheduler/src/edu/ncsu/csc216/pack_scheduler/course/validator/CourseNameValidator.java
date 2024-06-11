/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;


/**
 * The CourseNameValidator is implemented using the state pattern
 * The four inner classes implement the State abstract class
 * The state abstract class has three methods for the three different possibilities
 * letter, digit, or other
 * the concrete states each have their own implementation for the three methods
 * 
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
public class CourseNameValidator {

	/** Is true if the end state is valid per the FSM*/
	private boolean validEndState;
	/** letterCount holds the amount of letters */
	private int letterCount;
	/** digitCount variable holds the amount of digits */
	private int digitCount;

	/** This is the state for the optional 1 letter suffix */
	private final SuffixState stateSuffix = new SuffixState();
	/** This is the state for a number */
	private final NumberState stateNumber = new NumberState();
	/** This is the initial state */
	private final InitialState stateInitial = new InitialState();
	/** This is the current state, it begins at stateInitial but changes throughout the FSM */
	private State currentState = stateInitial;
	/** This is the state for when on a letter */
	private final LetterState stateLetter = new LetterState();
	
	/**
	 * the isValid method returns a boolean true if the course name is valid
	 * returns false otherwise
	 * @param name is the course name
	 * @return validEndState determines if it is at a valid end state per the FSM and requirements
	 * @throws InvalidTransitionException if there is a transition that is not valid, not a letter or digit
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		for( int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (Character.isLetter(ch)) {
				currentState.onLetter();
			} else if (Character.isDigit(ch)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		reset();
		return validEndState;
	}
	
	/**
	 * The state abstract class creates common behavior for the inner class
	 * State has three methods for each inner class to implement
	 * @author Kiran
	 * @author Armando
	 * @author Kennedy
	 */
	public abstract class State {
		/**
		 * The onLetter method handles a letter input
		 * @throws InvalidTransitionException which the inner class implements
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		/**
		 * The onDigit method handles a digit input
		 * @throws InvalidTransitionException which the inner class implements
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		/**
		 * The onOther method handles input that is not a digit or letter
		 * @throws InvalidTransitionException since it handles an invalid input
		 */
		public void onOther() throws InvalidTransitionException {
			reset();
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * The letterState inner class extends the abstract class State, it provides unique implementation for the three methods
	 * Is used when input is a letter
	 * @author Armando
	 * @author Kennedy
	 * @author Kiran
	 */
	private class LetterState extends State {
		/** This constant stores the max number of valid prefix letters */
		static final int MAX_PREFIX_LETTERS = 4;
		
		/**
		 * The onLetter method implementation increases the letterCount and checks to see
		 * see if the the letter counter is greater than the maximum number of letters allowed
		 * @throws InvalidTransitionException if there course name starts with more than 4 letters
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			letterCount++;
			if (letterCount > MAX_PREFIX_LETTERS) {
				reset();
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		
		/**
		 * The onDigit method implementation increases the digit count and assigns the currentState to the number state
		 */
		@Override
		public void onDigit() {
			digitCount++;
			currentState = stateNumber;

		}
	}

	/**
	 * The initial state extends the abstract class state
	 * it provides unique implementation for the three methods
	 * Essentially resets the State
	 * @author Kennedy
	 * @author Armando
	 * @author Kiran
	 *
	 */
	private class InitialState extends State {
		/**
		 * The onLetter method sets validEndState to false
		 * sets the current state to a letter state
		 * sets the letter count to 1
		 */
		@Override
		public void onLetter() {
			validEndState = false;
			currentState = stateLetter;
			letterCount = 1;
		}

		/**
		 * The onDigit method calls the reset method
		 * @throws InvalidTransitionException if the first input is a digit
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			reset();
			throw new InvalidTransitionException("Course name must start with a letter.");

		}

	}

	/**
	 * The number state extends the abstract class State
	 * it provides unique implementation for the three methods
	 * Is used when input is a digit
	 * @author Kennedy
	 * @author Kiran
	 * @author Armando
	 *
	 */
	private class NumberState extends State {
		/** This constant is the max number of digits for the course number */
		static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * The onLetter method checks the number of digits to not be equal to the max number
		 * if the digit count does not equal the max number, we call the reset method
		 * if the digit count is equal to the max number allowed, the currentState is set to suffix state
		 * @throws InvalidTransitionException if there are not 3 digits
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if(digitCount != COURSE_NUMBER_LENGTH) {
				reset();
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			if(digitCount == COURSE_NUMBER_LENGTH) {
				currentState = stateSuffix;
			}
		}

		/**
		 * The onDigit method increases the digitCount by 1 and sets the validEndState to false
		 * If the digitCount is equal to the course number length we set the validEndState to true
		 * @throws InvalidTransitionException if the digitCount is greater than the number length for a course
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			digitCount++;
			validEndState = false;
			if(digitCount > COURSE_NUMBER_LENGTH) {
				reset();
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			if(digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
			}
		}

	}

	/**
	 * The suffix state inner class extends the abstract class State
	 * provides unique implementation for the three methods
	 * It is used after the last digit input
	 * @author Armando
	 * @author Kiran
	 * @author Kennedy
	 */
	private class SuffixState extends State {

		/**
		 * The onLetter method sets the currentState to the letterState
		 * calls the reset method
		 * @throws InvalidTransitionException the course name can only have a 1 letter suffix
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			currentState = stateLetter;
			reset();
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");

		}
		
		/**
		 * The onDigit method calls the reset method
		 * @throws InvalidTransitionException the course name can no longer contain digits after the suffix
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			reset();
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}

	}
	
	/**
	 * The reset method is a private method which
	 * sets the letterCount to 0
	 * sets the digitCount to 0
	 * sets the currentState to the initial state
	 */
	private void reset() {
		letterCount = 0;
		digitCount = 0;
		currentState = stateInitial;
	}
}
