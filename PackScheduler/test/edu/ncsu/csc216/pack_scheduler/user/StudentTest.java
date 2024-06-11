package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.NullAndEmptySource;

import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests the Student class.
 * 
 * Note that test methods for all getters have been omitted. They will be tested
 * through other methods.
 * 
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
class StudentTest {

	/** Student's first name. */
	private String firstName = "first";
	/** Student's last name */
	private String lastName = "last";
	/** Student's id */
	private String id = "flast";
	/** Student's email */
	private String email = "first_last@ncsu.edu";
	/** Student's password */
	private String password = "password";
	/** Maximum number of credit hours */
	private int maxCredits = 15;

	/**
	 * Tests constructing a Student without a parameter for max credits.
	 */
	@Test
	public void testStudentWithoutMaxCredits() {
		Student s = assertDoesNotThrow(() -> new Student(firstName, lastName, id, email, password),
				"Should not throw exception");
		assertAll("Student", () -> assertEquals(firstName, s.getFirstName(), "Invalid first name"),
				() -> assertEquals(lastName, s.getLastName(), "Invalid last name"),
				() -> assertEquals(id, s.getId(), "Invalid id"),
				() -> assertEquals(email, s.getEmail(), "Invalid email"),
				() -> assertEquals(password, s.getPassword(), "Invalid password"));

	}

	/**
	 * Tests constructing a Student with all parameters.
	 */
	@Test
	public void testStudentWithMaxCredits() {

		Student s = assertDoesNotThrow(() -> new Student(firstName, lastName, id, email, password, maxCredits),
				"Should not throw exception");
		assertAll("Student",

				() -> assertEquals(firstName, s.getFirstName(), "Invalid first name"),
				() -> assertEquals(lastName, s.getLastName(), "Invalid last name"),
				() -> assertEquals(id, s.getId(), "Invalid id"),
				() -> assertEquals(email, s.getEmail(), "Invalid email"),
				() -> assertEquals(password, s.getPassword(), "Invalid password"),
				() -> assertEquals(maxCredits, s.getMaxCredits(), "Invalid max credits"));
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
				() -> new Student(invalidFirstName, lastName, id, email, password));
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

		Student student = assertDoesNotThrow(() -> new Student(firstName, lastName, id, email, password, maxCredits),
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
				() -> new Student(firstName, null, id, email, password));
		assertEquals("Invalid last name", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class, () -> new Student(firstName, "", id, email, password));
		assertEquals("Invalid last name", exception.getMessage());

	}

	/**
	 * Tests setId() with only invalid values, which should throw
	 * IllegalArgumentException.
	 */
	@Test
	public void testSetIdInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, password));
		assertEquals("Invalid id", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, password));
		assertEquals("Invalid id", exception.getMessage());

	}

	/**
	 * Tests setEmail() with only invalid values, which should throw
	 * IllegalArgumentExeption.
	 */
	@Test
	public void testSetEmailInvalid() {

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, password));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", password));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "abc.com", password));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "abc@sitecom", password));
		assertEquals("Invalid email", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "a.bc@sitecom", password));
		assertEquals("Invalid email", exception.getMessage());
	}

	/**
	 * Tests setPassword() with only invalid values, which should throw
	 * IllegalArgumentExeption.
	 */
	@Test
	public void testSetPasswordInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class, () -> new Student(firstName, lastName, id, email, ""));
		assertEquals("Invalid password", exception.getMessage());

	}

	/**
	 * Tests setMaxCredits() using only invalid values, which should throw
	 * IllegalArgumentExeption.
	 */
	@Test
	public void testSetMaxCreditsInvalid() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, password, 0));
		assertEquals("Invalid max credits", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, password, 19));
		assertEquals("Invalid max credits", exception.getMessage());

	}

	/**
	 * Tests equals() using only valid values.
	 */
	@Test
	public void testEqualsObjectValid() {
		Student e1 = new Student(firstName, lastName, id, email, password, maxCredits);
		Student e2 = new Student(firstName, lastName, id, email, password, maxCredits);
		assertTrue(e1.equals(e2));
		assertTrue(e1.equals(e1));
	}

	/**
	 * Tests equals() using only invalid values.
	 */
	@Test
	public void testEqualsObjectInvalid() {
		Student e1 = new Student(firstName, lastName, id, email, password, maxCredits);
		Student e2 = new Student(firstName, lastName, id, email, password, maxCredits);

		e2 = new Student(firstName, lastName, id, "abc@site.com", password, maxCredits);
		assertFalse(e1.equals(e2));

		e2 = new Student("abc", lastName, id, email, password, maxCredits);
		assertFalse(e1.equals(e2));

		e2 = new Student(firstName, "abc", id, email, password, maxCredits);
		assertFalse(e1.equals(e2));

		e2 = new Student(firstName, lastName, "abc", email, password, maxCredits);
		assertFalse(e1.equals(e2));

		e2 = new Student(firstName, lastName, id, email, "abc", maxCredits);
		assertFalse(e1.equals(e2));

		e2 = new Student(firstName, lastName, id, email, password, 16);
		assertFalse(e1.equals(e2));

		Object e4 = null;
		assertFalse(e1.equals(e4));
	}

	/**
	 * Tests toString() with only valid inputs.
	 */
	@Test
	public void testToString() {
		Student e1 = new Student(firstName, lastName, id, email, password, maxCredits);
		String s = "first,last,flast,first_last@ncsu.edu,password,15";
		assertEquals(s, e1.toString());
	}

	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		Student s1 = new Student(firstName, lastName, id, email, password, maxCredits);
		Student s2 = new Student(firstName, lastName, id, email, password, maxCredits);
		Student s3 = new Student("abc", lastName, id, email, password, maxCredits);
		Student s4 = new Student(firstName, "abc", id, email, password, maxCredits);
		Student s5 = new Student(firstName, lastName, "abc", email, password, maxCredits);
		Student s6 = new Student(firstName, lastName, id, "abc@abc.com", password, maxCredits);
		Student s7 = new Student(firstName, lastName, id, email, "abc", maxCredits);
		Student s8 = new Student(firstName, lastName, id, email, password, 13);

		assertEquals(s1.hashCode(), s2.hashCode());

		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Tests the compareTo method to ensure the correct integer is returned.
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("John", "Doe", "jdoe", "jdoe@abc.com", "pw", 15);
		Student s2 = new Student("James", "Dan", "jadoe", "jdan@abc.com", "pw", 12);
		Student s3 = new Student("James", "Dan", "jadoe", "jdan@abc.com", "pw", 12);
		Student s4 = new Student("James", "Dan", "jbdoe", "jdan@abc.com", "pw", 12);
		Student s5 = new Student("Jbmes", "Dan", "jbdoe", "jdan@abc.com", "pw", 12);
		assertEquals(1, s1.compareTo(s2));
		assertEquals(-1, s2.compareTo(s1));
		assertEquals(0, s2.compareTo(s3));
		assertEquals(1, s4.compareTo(s3));
		assertEquals(-1, s3.compareTo(s4));
		assertEquals(1, s5.compareTo(s4));
		assertEquals(-1, s4.compareTo(s5));
	}

}
