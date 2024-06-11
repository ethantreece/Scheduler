package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The Faculty class extends the user class it is in charge of setting the max
 * number of courses for a faculty member which is 3, the minimum amount of
 * courses for a faculty member is 1 we construct a faculty member, each faculty
 * member has a first name, last name, id, email, password, and their max number
 * of courses We also use toString to return their credential in the correct
 * format
 * 
 * @author Ethan
 *
 */
public class Faculty extends User {

	/** Max courses the current faculty can have */
	private int maxCourses;
	/** Min courses any faculty can have */
	private static final int MIN_COURSES = 1;
	/** Max courses any faculty can have */
	private static final int MAX_COURSES = 3;
	/** Schedule of courses for the faculty member */
	private FacultySchedule facultySchedule;
	
	/**
	 * Constructs a Faculty object with values for all fields.
	 * IllegalArgumentException is thrown if any of the parameters are invalid.
	 * 
	 * @param firstName  first name of faculty
	 * @param lastName   last name of faculty
	 * @param id         faculty's id
	 * @param email      faculty's email
	 * @param password   faculty's password
	 * @param maxCourses max courses a faculty can enroll in
	 * @throws IllegalArgumentException if any of the given parameters are invalid
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		facultySchedule = new FacultySchedule(id);
	}

	/**
	 * Sets the max courses for the faculty. It must be between 1 and 3.
	 * 
	 * @param maxCourses max courses
	 * @throws IllegalArgumentException if maxCourses is invalid
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES)
			throw new IllegalArgumentException("Invalid max courses");
		this.maxCourses = maxCourses;
	}

	/**
	 * Gets the max courses
	 * 
	 * @return max courses for the current Faculty, must be between 1 and 3
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Returns the faculty schedule for the faculty member.
	 * @return The faculty members schedule.
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}
	
	/**
	 * Determines whether the faculty member is overloaded. If the number of courses 
	 * scheduled is greater than the maximum number of courses the faculty member could have, 
	 * then the faculty member is considered overloaded.
	 * @return Returns true if the faculty member has more scheduled courses than the maximum number of 
	 * courses that they should have.
	 */
	public boolean isOverloaded() {
		return facultySchedule.getNumScheduledCourses() > maxCourses;
	}
	
	/**
	 * Generates a hashCode for Student using all fields.
	 * 
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), getFirstName(), getId(), getLastName(), maxCourses, getPassword());
	}

	/**
	 * Compares a given object to this Student object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields. False if the objects
	 *         are not equal
	 */
	@Override
	public boolean equals(Object obj) {
		Faculty other = (Faculty) obj;
		return super.equals(obj) && maxCourses == other.maxCourses;
	}

	/**
	 * Returns a comma separated value String of all Faculty fields.
	 * 
	 * @return String representation of Faculty
	 */
	@Override
	public String toString() {

		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}

}
