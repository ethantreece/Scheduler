/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import static org.junit.jupiter.api.Assertions.*;


/**
 * This class tests the CourseRoll class and its functionality
 * 
 * 
 * @author Armando
 * @author Kiran
 * @author Kennedy
 *
 */
public class CourseRollTest {
	

	/** Tests CourseRollConstructor */
	@Test
	public void testCourseRollConstructor() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(10, courseRoll.getEnrollmentCap());
		try {
			courseRoll = new CourseRoll(null, 10);
			fail();
		} catch (IllegalArgumentException e) {
			// Do nothing, case passes
		}
		
	}
	
	/** Tests SetEnrollmentCap */
	@Test
	public void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll.setEnrollmentCap(8));
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll.setEnrollmentCap(251));
		courseRoll.setEnrollmentCap(11);
		StudentDirectory s = new StudentDirectory();
		s.loadStudentsFromFile("test-files/expected_t19_student_directory.txt");
		String[][] students = s.getStudentDirectory();
		for (int i = 0; i < 12; i++) {
			courseRoll.enroll(s.getStudentById(students[i][2]));
		}
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll.setEnrollmentCap(10));
	}
	
	/** Tests EnrollStudent */
	@Test
	public void testEnrollStudent() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll.enroll(null));
		courseRoll.enroll(new Student("john", "doe", "sampleId", "email@abc.com", "password"));
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll.enroll(new Student("john", "doe", "sampleId", "email@abc.com", "password")));
		
		Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll2 = c2.getCourseRoll();
		courseRoll2.setEnrollmentCap(12);
		StudentDirectory s = new StudentDirectory();
		s.loadStudentsFromFile("test-files/expected_t19_student_directory.txt");
		String[][] students = s.getStudentDirectory();
		for (int i = 0; i < 12; i++) {
			courseRoll2.enroll(s.getStudentById(students[i][2]));
		}
		
		StudentDirectory s2 = new StudentDirectory();
		s2.loadStudentsFromFile("test-files/expected_t19_student_directory.txt");
		String[][] students2 = s2.getStudentDirectory();
		Course c3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll3 = c3.getCourseRoll();
		for (int i = 0; i < 10; i++) {
			courseRoll3.enroll(s2.getStudentById(students2[i][2]));
		}
		assertEquals(0, courseRoll3.getNumberOnWaitlist());
		assertEquals(0, courseRoll3.getOpenSeats());
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll3.enroll(null));
		courseRoll3.enroll(new Student("1", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("2", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("3", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("4", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("5", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("6", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("7", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("8", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("9", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("10", "doe", "sampleId", "email@abc.com", "password"));
		assertEquals(10, courseRoll3.getNumberOnWaitlist());
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll3.enroll(new Student("11", "doe", "sampleId", "email@abc.com", "password")));
		
		
		
		
	}

	/** Tests CanEnroll */
	@Test
	public void testCanEnroll() {
		Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll2 = c2.getCourseRoll();
		courseRoll2.setEnrollmentCap(12);
		StudentDirectory s = new StudentDirectory();
		s.loadStudentsFromFile("test-files/expected_t19_student_directory.txt");
		String[][] students = s.getStudentDirectory();
		for (int i = 0; i < 12; i++) {
			courseRoll2.enroll(s.getStudentById(students[i][2]));
		}
		assertTrue(courseRoll2.canEnroll(new Student("john", "doe", "sampleId", "email@abc.com", "password")));
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		assertTrue(courseRoll.canEnroll(new Student("john", "doe", "sampleId", "email@abc.com", "password")));
		courseRoll.enroll(new Student("john", "doe", "sampleId", "email@abc.com", "password"));
		assertFalse(courseRoll.canEnroll(new Student("john", "doe", "sampleId", "email@abc.com", "password")));
		
		StudentDirectory s2 = new StudentDirectory();
		s2.loadStudentsFromFile("test-files/expected_t19_student_directory.txt");
		String[][] students2 = s2.getStudentDirectory();
		Course c3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll3 = c3.getCourseRoll();
		for (int i = 0; i < 12; i++) {
			courseRoll3.enroll(s2.getStudentById(students2[i][2]));
		}
		assertFalse(courseRoll3.canEnroll(s2.getStudentById(students[11][2])));
		courseRoll3.enroll(new Student("1", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("2", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("3", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("4", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("5", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("6", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("7", "doe", "sampleId", "email@abc.com", "password"));
		courseRoll3.enroll(new Student("8", "doe", "sampleId", "email@abc.com", "password"));
		assertFalse(courseRoll3.canEnroll(new Student("9", "doe", "sampleId", "email@abc.com", "password")));
	}
	
	/** Tests Dropping a course */
	@Test
	public void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll courseRoll = c.getCourseRoll();
		StudentDirectory s = new StudentDirectory();
		s.loadStudentsFromFile("test-files/expected_t19_student_directory.txt");
		String[][] students = s.getStudentDirectory();
		for (int i = 0; i < 12; i++) {
			courseRoll.enroll(s.getStudentById(students[i][2]));
		}
		assertThrows(IllegalArgumentException.class,
				() -> courseRoll.drop(null));
		courseRoll.drop(new Student("john", "doe", "sampleId", "email@abc.com", "password"));
		
		assertEquals(0, courseRoll.getOpenSeats());
		assertEquals(2, courseRoll.getNumberOnWaitlist());
		courseRoll.drop(s.getStudentById(students[0][2]));
		assertEquals(0, courseRoll.getOpenSeats());
		assertEquals(1, courseRoll.getNumberOnWaitlist());
		
	}
	
}
