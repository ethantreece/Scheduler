package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * Tests StudentDirectory.
 * 
 * @author Sarah Heckman
 * @author Jongwoo Shin
 * @author Armando Rosas
 * @author Haleema Begum
 */
public class StudentDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test failing password */
	private static final String FAILINGPW = "";
	/** Tests null password */
	private static final String NULLPW = null;
	/** Test max credits */
	private static final int MAX_CREDITS = 15;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		// Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		// Test that if there are students in the directory, they
		// are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();

		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);

		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFileValid() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile() using invalid inputs.
	 */
	@Test
	public void testLoadStudentsFromFileInvalid() {
		StudentDirectory sd = new StudentDirectory();

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> sd.loadStudentsFromFile("test-files/fakeFile.txt"));
		assertEquals("Unable to read file test-files/fakeFile.txt", exception.getMessage());
	}

	/**
	 * Tests StudentDirectory.addStudent() using valid inputs.
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		assertTrue(sd.addStudent("abc", "abc", "abc", "abc@sbc.com", PASSWORD, PASSWORD, MAX_CREDITS));
	}

	/**
	 * Tests StudentDirectory.addStudent() using invalid inputs, which should throw
	 * IllegalArgumentException.
	 */
	@Test
	public void testAddStudentInvalid() {
		StudentDirectory sd1 = new StudentDirectory();
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, FAILINGPW, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, FAILINGPW, MAX_CREDITS));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, NULLPW, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, NULLPW, MAX_CREDITS));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "notpw", MAX_CREDITS));
		assertEquals("Passwords do not match", exception.getMessage());

		sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertFalse(sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));

		assertFalse(sd1.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 2));
	}

	/**
	 * Tests StudentDirectory.removeStudent(). The student should be removed from
	 * the Student Directory.
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("daustin"));
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Lane", studentDirectory[0][0]);
		assertEquals("Berg", studentDirectory[0][1]);
		assertEquals("lberg", studentDirectory[0][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory(). The student should be saved
	 * onto the specified file.
	 */
	@Test
	public void testSaveStudentDirectoryValid() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory() using invalid inputs, which
	 * should throw IllegalArgumentException.
	 */
	@Test
	public void testSaveStudentDirectoryInvalid() {
		StudentDirectory sd = new StudentDirectory();
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> sd.saveStudentDirectory("/home/sesmith5/actual_student_records.txt"));
		assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", exception.getMessage());
	}

	/**
	 * Helper method to compare two files for the same contents.
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
