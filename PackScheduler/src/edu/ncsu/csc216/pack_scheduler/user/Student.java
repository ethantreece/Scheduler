package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The purpose of the Student class is to record and represent each individual's
 * student record. Implements Comparable interface so Student objects can be
 * added to SortedList in a sorted order.
 * 
 * @author Jongwoo Shin
 * @author Armando Rosas
 * @author Haleema Begum
 */
public class Student extends User implements Comparable<Student> {
//	/** This is the students first. */
//	private String firstName;
//	/** This is the students last name. */
//	private String lastName;
//	/** This is the student's id. */
//	private String id;
//	/** This is the student's email. */
//	private String email;
//	/** This is the password. */
//	private String password;
	/** This is the student id. */
	private int maxCredits;
	
	/** This is an instance of schedule to be used in other classes */
	private Schedule schedule = new Schedule();
	
	/** The maximum number of credit hours is 18. */
	public static final int MAX_CREDITS = 18;

	/**
	 * Constructs a Student object the given firstName, lastName, id, email, and
	 * password with maxCredits set to 18 by default. IllegalArgumentException is
	 * thrown if any of the parameters are invalid.
	 * 
	 * @param firstName first name of student
	 * @param lastName  last name of student
	 * @param id        student's id
	 * @param email     student's email
	 * @param password  student's password
	 * @throws IllegalArgumentException if any of the given parameters are invalid
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(MAX_CREDITS);
	}

	/**
	 * Constructs a Student object with values for all fields.
	 * IllegalArgumentException is thrown if any of the parameters are invalid.
	 * 
	 * @param firstName  first name of student
	 * @param lastName   last name of student
	 * @param id         student's id
	 * @param email      student's email
	 * @param password   student's password
	 * @param maxCredits max credits a student can enroll in
	 * @throws IllegalArgumentException if any of the given parameters are invalid
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);

	}

	/**
	 * Gets the student's maximum number of credits.
	 * 
	 * @return the student's max credits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the student's maximum number of credits. An IllegalArgumentException is
	 * thrown if maxCredits is less than 3 or greater than 18.
	 * 
	 * @param maxCredits student's max credits
	 * @throws IllegalArgumentException if given maxCredits parameter is invalid
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18)
			throw new IllegalArgumentException("Invalid max credits");
		this.maxCredits = maxCredits;
	}

	/**
	 * Generates a hashCode for Student using all fields.
	 * 
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), getFirstName(), getId(), getLastName(), maxCredits, getPassword());
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
		Student other = (Student) obj;
		return super.equals(obj) && maxCredits == other.maxCredits;
	}

	/**
	 * Returns a comma separated value String of all Student fields.
	 * 
	 * @return String representation of Student
	 */
	@Override
	public String toString() {

		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}

	/**
	 * Takes in the student object and compares it to the current instance of
	 * student. Returns -1 if the instance of student is less alphabetically, 0 if
	 * the student objects are equal, and 1 if the instance of student is greater
	 * alphabetically. Throws NullPointerException if the given parameter is null.
	 * Throws ClassCastException if the given parameter is not a Student object.
	 * 
	 * @return -1 if current instance comes before alphabetically, 0 if equal, 1 if
	 *         it comes after alphabetically.
	 * @throws NullPointerException if the given parameter is null
	 * @throws ClassCastException   if the given parameter is not a Student object.
	 */
	@Override
	public int compareTo(Student s) {
		int last = this.getLastName().compareToIgnoreCase(s.getLastName());
		int first = this.getFirstName().compareToIgnoreCase(s.getFirstName());
		int idCompare = this.getId().compareToIgnoreCase(s.getId());

		if (last > 0)
			return 1;
		else if (last < 0)
			return -1;
		else if (first > 0)
			return 1;
		else if (first < 0)
			return -1;
		else if (idCompare > 0)
			return 1;
		else if (idCompare < 0)
			return -1;
		return 0;
	}
	
	/**
	 * This is a getter method that returns the schedule
	 * @return schedule is the student's schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks to see if course can be added to students schedule
	 * @param c course to check
	 * @return true if course can be added to students schedule
	 */
	public boolean canAdd(Course c) {
		return schedule.canAdd(c) && maxCredits >= (schedule.getScheduleCredits() + c.getCredits());
	}

}
