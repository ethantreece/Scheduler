package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * This class tests the Schedule clas and it's functionality
 * 
 * @author Armando rosas
 *
 */
class ScheduleTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course capacity */
	private static final int ENROLLMENTCAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * We test getting a schedule and the title
	 */
	@Test
	void testSchedule() {
		Schedule test = new Schedule();
		assertEquals(test.getTitle(), "My Schedule");
		assertEquals(test.getScheduledCourses().length, 0);
	}

	/**
	 * test adding a course to a schedule
	 */
	@Test
	void testAddCourseToSchedule() {
		Schedule test = new Schedule();

		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME,
				END_TIME);

		// Attempt to add a course that does exist
		assertTrue(test.addCourseToSchedule(c));
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> test.addCourseToSchedule(new Course(NAME,
				TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME, END_TIME)));
		assertEquals("You are already enrolled in " + NAME, e1.getMessage());

		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> test.addCourseToSchedule(new Course("E101", "Engineering in the future", "003", CREDITS,
						INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME, END_TIME)));
		assertEquals("The course cannot be added due to a conflict.", e4.getMessage());
	}

	/**
	 * test setting the title to null
	 */
	@Test
	public void nullTitleTest() {
		Schedule test = new Schedule();
		Exception e = assertThrows(IllegalArgumentException.class, () -> test.setTitle(null));
		assertEquals("Title cannot be null.", e.getMessage());
	}

	/**
	 * test reseting the schedule
	 */
	@Test
	public void resetScheduleTest() {
		Schedule test = new Schedule();
		test.setTitle(TITLE);
		test.resetSchedule();
		assertEquals("My Schedule", test.getTitle());
		assertEquals(0, test.getScheduledCourses().length);
	}

	/**
	 * test removing a course from the schedule
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule test = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME,
				END_TIME);
		test.addCourseToSchedule(c);
		assertEquals(1, test.getScheduledCourses().length);
		test.removeCourseFromSchedule(c);
		assertEquals(0, test.getScheduledCourses().length);
	}
}