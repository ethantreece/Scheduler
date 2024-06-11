package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * The CourseNameValidatorFSMTest tests CourseNameValidatorFSM
 * test the transition out of each state
 * letter, digit, or other
 * @author Kennedy
 * @author Kiran
 * @author Armando
 */
class CourseNameValidatorFSMTest {
	/** c is an instance of CourseNameValidatorFSM used for testing */
	CourseNameValidatorFSM c = new CourseNameValidatorFSM();

	@Test
	void testValidStates() {
		// Valid for 1 letters with 3 digits no suffix
		try {
			assertTrue(c.isValid("C116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		// Valid for 2 letters with 3 digits no suffix
		try {
			assertTrue(c.isValid("CS116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		// Valid for 3 letters with 3 digits no suffix
		try {
			assertTrue(c.isValid("CSC116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		// Valid for 4 letters with 3 digits no suffix
		try {
			assertTrue(c.isValid("CSCA116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		// Valid for 4 letters with 3 digits a suffix
		try {
			assertTrue(c.isValid("CSCA116A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	@Test
	void testInvalidStates() {
		// Doesn't start with a letter
		try {
			assertTrue(c.isValid("1SCA116"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must start with a letter.");
		}
		// Doesn't start with a letter or a number
		try {
			assertTrue(c.isValid("#SCA116"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
		// Five letters
		try {
			assertTrue(c.isValid("CSCTA116"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot start with more than 4 letters.");
		}
		// Only 1 digit
		try {
			assertTrue(c.isValid("CSCT1A"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}
		// Only 2 digit
		try {
			assertTrue(c.isValid("CSCT12A"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}
		// More than 3 digit
		try {
			assertTrue(c.isValid("CSCT1222"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only have 3 digits.");
		}
		// Suffix with 2 letters
		try {
			assertTrue(c.isValid("CSCA122AA"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");
		}
		// Suffix with 1 digit
		try {
			assertTrue(c.isValid("CSCA122A1"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
		}
	}

}
