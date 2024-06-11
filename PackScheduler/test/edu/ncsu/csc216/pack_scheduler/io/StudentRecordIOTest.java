package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the StudentRecordIO class.
 * 
 * @author Jongwoo Shin
 *
 */
class StudentRecordIOTest {

	/** Valid student records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid student records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	/** Expected results for valid students in student_records.txt - line 1 */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** Expected results for valid students in student_records.txt - line 2 */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** Expected results for valid students in student_records.txt - line 3 */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** Expected results for valid students in student_records.txt - line 4 */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** Expected results for valid students in student_records.txt - line 5 */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** Expected results for valid students in student_records.txt - line 6 */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** Expected results for valid students in student_records.txt - line 7 */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** Expected results for valid students in student_records.txt - line 8 */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** Expected results for valid students in student_records.txt - line 9 */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** Expected results for valid students in student_records.txt - line 10 */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** Array to hold expected results */
	private String[] validStudents = { validStudent0, validStudent1, validStudent2, validStudent3, validStudent4,
			validStudent5, validStudent6, validStudent7, validStudent8, validStudent9 };
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

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test readStudentRecords() with a valid test file and a file that contains
	 * duplicates. The duplicates should not be read from the file. The students
	 * should be ordered alphabetically starting with last name, first name, then
	 * id.
	 */
	@Test
	public void testReadStudentRecordsValid() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());

			assertEquals(validStudents[3], students.get(0).toString());
			assertEquals(validStudents[6], students.get(1).toString());
			assertEquals(validStudents[4], students.get(2).toString());
			assertEquals(validStudents[5], students.get(3).toString());
			assertEquals(validStudents[2], students.get(4).toString());
			assertEquals(validStudents[8], students.get(5).toString());
			assertEquals(validStudents[0], students.get(6).toString());
			assertEquals(validStudents[9], students.get(7).toString());
			assertEquals(validStudents[1], students.get(8).toString());
			assertEquals(validStudents[7], students.get(9).toString());

		} catch (FileNotFoundException e) {
			fail("Unexpected error reading" + validTestFile);
		}
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/duplicate_students.txt");
			assertEquals(5, students.size());

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
		SortedList<Student> students;
		try {
			students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}

		SortedList<Student> s1 = null;
		try {
			s1 = StudentRecordIO.readStudentRecords(nonexistentFile);
			fail("invalid test file was read");

		} catch (FileNotFoundException e) {
			assertNull(s1);
		}
	}

	/**
	 * Tests writeStudentRecords() by attempting to write to a portion of a file
	 * system where access is not provided.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

		Exception exception = assertThrows(IOException.class,
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", exception.getMessage());
	}

	/**
	 * Tests the default constructor.
	 */
	@Test
	public void testConstructor() {
		StudentRecordIO sr = assertDoesNotThrow(() -> new StudentRecordIO());
		assertNotEquals(null, sr);
	}
}
