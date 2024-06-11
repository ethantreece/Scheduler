/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * The Schedule class is used to store the student's courses The methods all
 * work together to add and remove courses from the student's schedule the
 * setter methods check for invalid inputs the getter methods return the courses
 * and title
 * 
 * @author Armando
 * @author Kiran
 * @author Kennedy
 */
public class Schedule {

	/** This a string representation of the title of the student's schedule */
	private String title;

	/**
	 * This is an ArrayList of type course called schedule used to store the
	 * student's courses
	 */
	private ArrayList<Course> schedule;

	/**
	 * The Schedule constructor sets the schedule's title to "My Schedule" an empty
	 * ArrayList of Courses is created
	 */
	public Schedule() {
		this.title = "My Schedule";
		schedule = new ArrayList<Course>();
	}

	/**
	 * This method adds a course to a student's schedule we first start by checking
	 * for null courses we check for duplicates we then check for any conflicts if
	 * all checks pass, we add the curse to schedule
	 * 
	 * @param c is the course to be added to a student's schedule
	 * @return true if the course was able to be added
	 */
	public boolean addCourseToSchedule(Course c) {
		if (c == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (c.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			try {
				c.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(schedule.size(), c);
		return true;
	}

	/**
	 * This method removes a course from a student's schedule
	 * 
	 * @param c is the course to be removed
	 * @return true if the course was able to be removed
	 */
	public boolean removeCourseFromSchedule(Course c) {
		if (c == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (c.isDuplicate(schedule.get(i))) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * This method resets the student's schedule the title is set to "My Schedule"
	 * the schedule is set to an empty ArrayList
	 */
	public void resetSchedule() {
		this.title = "My Schedule";
		schedule = new ArrayList<Course>();
	}

	/**
	 * this getter method gets the courses that a student has it has 4 columns,
	 * index 0 for name index 1 for section index 2 for title index 3 for meeting
	 * string
	 * 
	 * @return courses the 2d string array representation of the courses
	 */
	public String[][] getScheduledCourses() {
		String[][] courses = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			courses[i][0] = schedule.get(i).getShortDisplayArray()[0];
			courses[i][1] = schedule.get(i).getShortDisplayArray()[1];
			courses[i][2] = schedule.get(i).getShortDisplayArray()[2];
			courses[i][3] = schedule.get(i).getShortDisplayArray()[3];
			courses[i][4] = schedule.get(i).getShortDisplayArray()[4];

		}

		return courses;
	}

	/**
	 * This setter method checks for a null string input if the String passes the
	 * check, it is set as the title for the schedule
	 * 
	 * @param title is the string title to be passed in
	 * @throws IllegalArgumentException if the title string is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * this getter method gets the title of the schedule
	 * 
	 * @return title the name of the schedule
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Totals up credits of all courses
	 * 
	 * @return total of all credits in schedule
	 */
	public int getScheduleCredits() {
		int total = 0;
		for (int i = 0; i < schedule.size(); i++) {
			total += schedule.get(i).getCredits();
		}
		return total;
	}
	
	/**
	 * Checks to see if course can be added to schedule
	 * @param c course to check
	 * 
	 * @return true if course can be added to schedule
	 */
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (c.isDuplicate(schedule.get(i))) {
				return false;
			}
			try {
				c.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}
}
