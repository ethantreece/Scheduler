/**
 * 
 */
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
 * Tests the FacultyDirectory class
 * @author crjone24
 *
 */
class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "Ulty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test failing password */
	private static final String FAILINGPW = "";
	/** Tests null password */
	private static final String NULLPW = null;
	/** Test max COURSES */
	private static final int MAX_COURSES = 2;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("awitt"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		// Test that if there are Faculty in the directory, they
		// are removed after calling newFacultyDirectory().
		FacultyDirectory fd = new FacultyDirectory();

		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);

		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultyFromFileValid() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile() using invalid inputs.
	 */
	@Test
	public void testLoadFacultyFromFileInvalid() {
		FacultyDirectory fd = new FacultyDirectory();

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> fd.loadFacultyFromFile("test-files/fakeFile.txt"));
		assertEquals("Unable to read file test-files/fakeFile.txt", exception.getMessage());
	}

	/**
	 * Tests FacultyDirectory.addFaculty() using valid inputs.
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		assertTrue(fd.addFaculty("abc", "abc", "abc", "abc@sbc.com", PASSWORD, PASSWORD, MAX_COURSES));
	}

	/**
	 * Tests FacultyDirectory.addFaculty() using invalid inputs, which should throw
	 * IllegalArgumentException.
	 */
	@Test
	public void testAddFacultyInvalid() {
		FacultyDirectory fd1 = new FacultyDirectory();
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, FAILINGPW, PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, FAILINGPW, MAX_COURSES));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, NULLPW, PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, NULLPW, MAX_COURSES));
		assertEquals("Invalid password", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class,
				() -> fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "notpw", MAX_COURSES));
		assertEquals("Passwords do not match", exception.getMessage());

		fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		assertFalse(fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));

		assertFalse(fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 2));
	}

	/**
	 * Tests FacultyDirectory.removeFaculty(). The Faculty should be removed from
	 * the Faculty Directory.
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add Faculty and remove
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		assertTrue(fd.removeFaculty("awitt"));
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Fiona", facultyDirectory[0][0]);
		assertEquals("Meadows", facultyDirectory[0][1]);
		assertEquals("fmeadow", facultyDirectory[0][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory(). The Faculty should be saved
	 * onto the specified file.
	 */
	@Test
	public void testSaveFacultyDirectoryValid() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add a Faculty
		fd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 2);
		assertEquals(1, fd.getFacultyDirectory().length);
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory() using invalid inputs, which
	 * should throw IllegalArgumentException.
	 */
	@Test
	public void testSaveFacultyDirectoryInvalid() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 2);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> fd.saveFacultyDirectory("/home/sesmith5/actual_faculty_records.txt"));
		assertEquals("Unable to write to file /home/sesmith5/actual_faculty_records.txt", exception.getMessage());
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
