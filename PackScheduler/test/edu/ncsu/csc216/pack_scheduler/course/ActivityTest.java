
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity class. The focus of this test class is to make sure
 * Activity.checkConflict() method works as intended. The tests revolve around
 * constructing different instances of Course, using checkConflict(), and
 * testing if ConflictExcpetion is thrown correctly and accordingly.
 * 
 * @author Jongwoo Shin
 *
 */
class ActivityTest {

	/**
	 * Tests method checkConflict() with valid inputs for Course objects that do not
	 * cause conflicts.
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "TH", 1330,
				1445);
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));

		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "MTWHF", 1330,
				1445);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "MTWHF", 1446,
				1530);
		assertDoesNotThrow(() -> a3.checkConflict(a4));
		assertDoesNotThrow(() -> a4.checkConflict(a3));

		Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "A", 0, 0);
		Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "A", 0, 0);
		assertDoesNotThrow(() -> a5.checkConflict(a6));
		assertDoesNotThrow(() -> a6.checkConflict(a5));
		assertDoesNotThrow(() -> a5.checkConflict(a1));
		assertDoesNotThrow(() -> a1.checkConflict(a5));

	}

	/**
	 * Tests method checkConflict() with invalid inputs for Course objects, which
	 * should throw ConflictException. Constructs the objects with the same start
	 * and end times for the test.
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "M", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

	}

	/**
	 * Tests method checkConflict() by having the start time of a course overlap the
	 * end time of another course, which should throw ConflictException.
	 */
	@Test
	public void testCheckConflictEndTimeStartTime() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "M", 1445, 1600);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}

	/**
	 * Tests method checkConflict() by having a course's start time or end time in
	 * between another course's meeting times, which should throw ConflictException.
	 */
	@Test
	public void testCheckConflictBetweenTimes() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "MWHF", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "H", 1400, 1600);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "H", 1300, 1500);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 20, "H", 1400, 1430);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

		Exception e3 = assertThrows(ConflictException.class, () -> a1.checkConflict(a3));
		assertEquals("Schedule conflict.", e3.getMessage());

		Exception e4 = assertThrows(ConflictException.class, () -> a3.checkConflict(a1));
		assertEquals("Schedule conflict.", e4.getMessage());

		Exception e5 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
		assertEquals("Schedule conflict.", e5.getMessage());

		Exception e6 = assertThrows(ConflictException.class, () -> a4.checkConflict(a1));
		assertEquals("Schedule conflict.", e6.getMessage());
	}

}
