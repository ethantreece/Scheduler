/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class.
 * 
 * @author Jongwoo Shin
 * @author Haleema Begum
 *
 */
class CourseCatalogTest {

	/**
	 * Test the parameterless constructor for CourseCatalog.
	 */
	@Test
	void testCourseCatalog() {
		CourseCatalog cc = assertDoesNotThrow(() -> new CourseCatalog());
		String[][] stringCatalog = cc.getCourseCatalog();
		assertEquals(0, stringCatalog.length);

	}

	/**
	 * Tests newCourseCatalog() method.
	 */
	@Test
	void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");
		cc.newCourseCatalog();
		String[][] stringCatalog = cc.getCourseCatalog();
		assertEquals(0, stringCatalog.length);
	}

	/**
	 * Test method for loadCoursesFromFile(). 
	 */
	@Test
	void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records1.txt");
		String[][] stringCatalog = cc.getCourseCatalog();
		assertEquals(13, stringCatalog.length);
	}

	/**
	 * Tests addCourseToCatalog() with valid inputs.
	 */
	@Test
	void testAddCourseToCatalogValid() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records1.txt");
		cc.addCourseToCatalog("CSC400", "Introduction to magic", "001", 5, "hpotter", 10, "MW", 2200, 2300);
		String[][] stringCatalog = cc.getCourseCatalog();
		assertEquals(14, stringCatalog.length);
	}
	
	/**
	 * Tests addCourseToCatalog() with invalid values.
	 */
	@Test
	public void testAddCourseToCatalogInvalid() {
		CourseCatalog c = new CourseCatalog();
		c.addCourseToCatalog("CSC400", "Introduction to magic", "001", 5, "hpotter", 10, "MW", 2200, 2300);
		assertFalse(c.addCourseToCatalog("CSC400", "Introduction to magic", "001", 5, "fbaggins", 10, "TH", 1100, 1200));
	}

	/**
	 * Tests removeCourseFromCatalog() with valid inputs.
	 */
	@Test
	void testRemoveCourseFromCatalogValid() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records1.txt");
		cc.removeCourseFromCatalog("CSC116", "003");
		String[][] stringCatalog = cc.getCourseCatalog();
		assertEquals(12, stringCatalog.length);
	}

	/**
	 * Tests removeCourseFromCatalog() with invalid inputs.
	 */
	@Test
	public void testRemoveCourseFromCatalogInvalid() {
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile("test-files/course_records1.txt");
		assertFalse(c.removeCourseFromCatalog("CSC272", "999"));
	}
	
	/**
	 * Tests getCourseFromCatalog().
	 */
	@Test
	void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records1.txt");
		Course course = new Course("CSC116", "Intro to Programming - Java", "001", 3, null, 10, "MW", 910, 1100);
		assertEquals(course, cc.getCourseFromCatalog("CSC116", "001"));

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#getCourseCatalog()}.
	 */
	@Test
	void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records1.txt");
		String[][] stringCatalog = cc.getCourseCatalog();
		assertEquals("CSC116", stringCatalog[0][0]);
		assertEquals("Intro to Programming - Java", stringCatalog[0][2]);
		assertEquals("001", stringCatalog[0][1]);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#saveCourseCatalog(java.lang.String)}.
	 */
	@Test
	void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
//		cc.loadCoursesFromFile("test-files/course_records.txt");
		cc.addCourseToCatalog("CSC400", "Introduction to magic", "001", 5, "hpotter", 10, "MW", 2200, 2300);
		String[][] stringCatalog = cc.getCourseCatalog();
		assertEquals(1, stringCatalog.length);
		cc.saveCourseCatalog("test-files/actual_magic.txt");
		checkFiles("test-files/expected_magic.txt", "test-files/actual_magic.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
				Scanner actScanner = new Scanner(new File(actFile));) {
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
