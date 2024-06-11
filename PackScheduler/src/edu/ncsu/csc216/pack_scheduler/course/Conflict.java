
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The Conflict interface defines the method that is in charge of checking for
 * time conflicts between activities. This prevents courses from
 * overlapping other activities already in a student's schedule. This is
 * implemented in the Activity class.
 * 
 * 
 * @author Jongwoo Shin
 *
 */
public interface Conflict {

	/**
	 * Checks an activity to see if there is an overlap of at least one day or time
	 * between a course already in the student's schedule.
	 * ConflictException is thrown if the activity being added has meeting times
	 * that conflict with an activity in the schedule.
	 * 
	 * @param possibleConflictingActivity the activity that may cause a conflict
	 * @throws ConflictException if the meeting times of possibleConflictingActivity
	 *                           is the same or overlaps the meeting times of
	 *                           another activity already in student's schedule.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
