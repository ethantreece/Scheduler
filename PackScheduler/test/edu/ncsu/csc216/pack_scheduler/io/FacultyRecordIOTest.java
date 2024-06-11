/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * This test class, tests the input and output of the FacultyRecordIO class Per
 * the requirements and writeup, we test that the output is what we intend
 * 
 * @author Armando Rosas
 *
 */
class FacultyRecordIOTest {

	/** Valid student records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid student records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** Expected results for valid students in student_records.txt - line 1 */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	/** Expected results for valid students in student_records.txt - line 2 */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	/** Expected results for valid students in student_records.txt - line 3 */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	/** Expected results for valid students in student_records.txt - line 4 */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	/** Expected results for valid students in student_records.txt - line 5 */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	/** Expected results for valid students in student_records.txt - line 6 */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	/** Expected results for valid students in student_records.txt - line 7 */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	/** Expected results for valid students in student_records.txt - line 8 */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";
	/** Expected results for valid students in student_records.txt - line 9 */
	private String validFaculty8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** Expected results for valid students in student_records.txt - line 10 */
	private String validFaculty9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** Array to hold expected results */
	private String[] validFaculty = { validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4,
			validFaculty5, validFaculty6, validFaculty7, validFaculty8, validFaculty9 };
	/** Hashed Password */
	private String hashPW;
	/** Algorithm to hashed passwords */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** File that does not exist */
	private String nonexistentFile = "test-files/does_not_exist.txt";

	/**
	 * Replaces the substring "pw" with the hashed value for "pw".
	 */
	@BeforeEach
	public void setUp() {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validFaculty.length; i++) {
				validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test readStudentRecords() with a valid test file and a file that contains
	 * duplicates. The duplicates should not be read from the file. The faculty
	 * should be ordered alphabetically starting with last name, first name, then
	 * id.
	 */
	@Test
	public void testReadStudentRecordsValid() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());

			assertEquals(validFaculty[0], faculty.get(0).toString());
			assertEquals(validFaculty[1], faculty.get(1).toString());
			assertEquals(validFaculty[2], faculty.get(2).toString());
			assertEquals(validFaculty[3], faculty.get(3).toString());
			assertEquals(validFaculty[4], faculty.get(4).toString());
			//assertEquals(validFaculty[8], faculty.get(5).toString());
			assertEquals(validFaculty[5], faculty.get(5).toString());
			//assertEquals(validFaculty[9], faculty.get(7).toString());
			assertEquals(validFaculty[6], faculty.get(6).toString());
			assertEquals(validFaculty[7], faculty.get(7).toString());

		} catch (FileNotFoundException e) {
			fail("Unexpected error reading" + validTestFile);
		}
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords("test-files/expected_full_faculty_records.txt");
			assertEquals(8, faculty.size());

		} catch (FileNotFoundException e) {
			fail("Unexpected error reading duplicate_students.txt");
		}
	}

	/**
	 * Tests readStudentRecords() by using an invalid file that is empty or by
	 * attempting to read a file that does not exist.
	 */
	@Test
	public void testReadStudentRecordsInvalid() {
		LinkedList<Faculty> faculty;
		try {
			faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}

		LinkedList<Faculty> s1 = null;
		try {
			s1 = FacultyRecordIO.readFacultyRecords(nonexistentFile);
			fail("invalid test file was read");

		} catch (FileNotFoundException e) {
			assertNull(s1);
		}
	}

//	/**
//	 * Tests writeStudentRecords() by attempting to write to a portion of a file
//	 * system where access is not provided.
//	 */
//	@Test
//	public void testWriteStudentRecordsNoPermissions() {
//		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
//		faculty.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
//
//		Exception exception = assertThrows(IOException.class,
//				() -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_student_records.txt", faculty));
//		assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", exception.getMessage());
//	}

	/**
	 * Tests the default constructor.
	 */
	@Test
	public void testConstructor() {
		FacultyRecordIO sr = assertDoesNotThrow(() -> new FacultyRecordIO());
		assertNotEquals(null, sr);
	}

}
