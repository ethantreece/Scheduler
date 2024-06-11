package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests the Faculty class and its functionality
 * @author Ethan
 *
 */
class FacultyTest {

	/** Faculty's first name. */
	private String firstName = "first";
	/** Faculty's last name */
	private String lastName = "last";
	/** Faculty's id */
	private String id = "flast";
	/** Faculty's email */
	private String email = "first_last@ncsu.edu";
	/** Faculty's password */
	private String password = "password";
	/** Maximum number of courses a faculty could have */
	private int maxCourses = 3;

	/**
	 * Tests constructing a Faculty without a parameter for max credits.
	 */
	@Test
	public void testFacultyWithoutMaxCredits() {
		Faculty s = assertDoesNotThrow(() -> new Faculty(firstName, lastName, id, email, password, maxCourses),
				"Should not throw exception");
		assertAll("Faculty", () -> assertEquals(firstName, s.getFirstName(), "Invalid first name"),
				() -> assertEquals(lastName, s.getLastName(), "Invalid last name"),
				() -> assertEquals(id, s.getId(), "Invalid id"),
				() -> assertEquals(email, s.getEmail(), "Invalid email"),
				() -> assertEquals(password, s.getPassword(), "Invalid password"));

	}

	/**
	 * Tests constructing a Faculty with all parameters.
	 */
	@Test
	public void testFacultyWithMaxCourses() {

		Faculty s = assertDoesNotThrow(() -> new Faculty(firstName, lastName, id, email, password, maxCourses),
				"Should not throw exception");
		assertAll("Faculty",

				() -> assertEquals(firstName, s.getFirstName(), "Invalid first name"),
				() -> assertEquals(lastName, s.getLastName(), "Invalid last name"),
				() -> assertEquals(id, s.getId(), "Invalid id"),
				() -> assertEquals(email, s.getEmail(), "Invalid email"),
				() -> assertEquals(password, s.getPassword(), "Invalid password"),
				() -> assertEquals(maxCourses, s.getMaxCourses(), "Invalid max courses"));
	}

	/**
	 * Tests setFirstName(). The test only considers invalid values, which should
	 * throw IllegalArgumentException.
	 * 
	 * @param invalidFirstName invalid first name to set
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "" })
	public void testSetFirstName(String invalidFirstName) {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(invalidFirstName, lastName, id, email, password, maxCourses));
		assertEquals("Invalid first name", exception.getMessage());
	}

	/**
	 * Tests setLastName(). The test only considers valid values for lastName.
	 * 
	 * @param lastName Name valid last name to test
	 */
	@ParameterizedTest
	@ValueSource(strings = { "John", "Jasone", "Chris" })
	public void testSetLastNameValid(String lastName) {

		Faculty student = assertDoesNotThrow(() -> new Faculty(firstName, lastName, id, email, password, maxCourses),
				"Should not throw exception");
		assertEquals(lastName, student.getLastName(), "Failed test with valid student last name - " + lastName);

	}

	/**
	 * Tests setLastName() with only invalid values, which should throw
	 * IllegalArgumentException.
	 */
	@Test
	public void testLastNameInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, null, id, email, password, maxCourses));
		assertEquals("Invalid last name", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, "", id, email, password, maxCourses));
		assertEquals("Invalid last name", exception.getMessage());

	}

	/**
	 * Tests setId() with only invalid values, which should throw
	 * IllegalArgumentException.
	 */
	@Test
	public void testSetIdInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, null, email, password, maxCourses));
		assertEquals("Invalid id", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, "", email, password, maxCourses));
		assertEquals("Invalid id", exception.getMessage());

	}

	/**
	 * Tests setEmail() with only invalid values, which should throw
	 * IllegalArgumentExeption.
	 */
	@Test
	public void testSetEmailInvalid() {

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, null, password, maxCourses));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, "", password, maxCourses));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, "abc.com", password, maxCourses));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, "abc@sitecom", password, maxCourses));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, "a.bc@sitecom", password, maxCourses));
		assertEquals("Invalid email", exception.getMessage());
	}

	/**
	 * Tests setPassword() with only invalid values, which should throw
	 * IllegalArgumentExeption.
	 */
	@Test
	public void testSetPasswordInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, null, 3));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, email, "", 3));
		assertEquals("Invalid password", exception.getMessage());

	}

	/**
	 * Tests setMaxCredits() using only invalid values, which should throw
	 * IllegalArgumentExeption.
	 */
	@Test
	public void testSetMaxCreditsInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, password, 0));
		assertEquals("Invalid max courses", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, password, 19));
		assertEquals("Invalid max courses", exception.getMessage());

	}

	/**
	 * Tests equals() using only valid values.
	 */
	@Test
	public void testEqualsObjectValid() {
		Faculty e1 = new Faculty(firstName, lastName, id, email, password, maxCourses);
		Faculty e2 = new Faculty(firstName, lastName, id, email, password, maxCourses);
		assertTrue(e1.equals(e2));
		assertTrue(e1.equals(e1));
	}

	/**
	 * Tests equals() using only invalid values.
	 */
	@Test
	public void testEqualsObjectInvalid() {
		Faculty e1 = new Faculty(firstName, lastName, id, email, password, maxCourses);
		Faculty e2 = new Faculty(firstName, lastName, id, email, password, maxCourses);

		e2 = new Faculty(firstName, lastName, id, "abc@site.com", password, maxCourses);
		assertFalse(e1.equals(e2));

		e2 = new Faculty("abc", lastName, id, email, password, maxCourses);
		assertFalse(e1.equals(e2));

		e2 = new Faculty(firstName, "abc", id, email, password, maxCourses);
		assertFalse(e1.equals(e2));

		e2 = new Faculty(firstName, lastName, "abc", email, password, maxCourses);
		assertFalse(e1.equals(e2));

		e2 = new Faculty(firstName, lastName, id, email, "abc", maxCourses);
		assertFalse(e1.equals(e2));

		e2 = new Faculty(firstName, lastName, id, email, password, 1);
		assertFalse(e1.equals(e2));

		Object e4 = null;
		assertFalse(e1.equals(e4));
	}

	/**
	 * Tests toString() with only valid inputs.
	 */
	@Test
	public void testToString() {
		Faculty e1 = new Faculty(firstName, lastName, id, email, password, maxCourses);
		String s = "first,last,flast,first_last@ncsu.edu,password,3";
		assertEquals(s, e1.toString());
	}

	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		Faculty s1 = new Faculty(firstName, lastName, id, email, password, maxCourses);
		Faculty s2 = new Faculty(firstName, lastName, id, email, password, maxCourses);
		Faculty s3 = new Faculty("abc", lastName, id, email, password, maxCourses);
		Faculty s4 = new Faculty(firstName, "abc", id, email, password, maxCourses);
		Faculty s5 = new Faculty(firstName, lastName, "abc", email, password, maxCourses);
		Faculty s6 = new Faculty(firstName, lastName, id, "abc@abc.com", password, maxCourses);
		Faculty s7 = new Faculty(firstName, lastName, id, email, "abc", maxCourses);
		Faculty s8 = new Faculty(firstName, lastName, id, email, password, 1);

		assertEquals(s1.hashCode(), s2.hashCode());

		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

}
