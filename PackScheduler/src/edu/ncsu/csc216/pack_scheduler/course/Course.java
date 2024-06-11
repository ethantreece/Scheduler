
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * This class constructs and contains information on scheduled courses. The
 * class holds records of course name, section number, number of credit hours,
 * and the instructor's unity id. Inherits title, meetingDays, startTime, and
 * endTime from the parent class, Activity. Overrides the abstract classes
 * getShortDisplayArray, getLongDisplayArray, and isDuplicate to better serve
 * the Course class.
 * 
 * @author Jongwoo Shin
 * @author Kiran
 *
 */
public class Course extends Activity implements Comparable<Course> {

//	/** Minimum characters for Course name */
//	private static final int MIN_NAME_LENGTH = 5;
//	/** Maximum characters for Course name */
//	private static final int MAX_NAME_LENGTH = 8;
//	/** Minimum letters for Course name */
//	private static final int MIN_LETTER_COUNT = 1;
//	/** Maximum letters for Course name */
//	private static final int MAX_LETTER_COUNT = 4;
//	/** Required number of digits for Course name */
//	private static final int DIGIT_COUNT = 3;
	/** Required number of digits for section number */
	private static final int SECTION_LENGTH = 3;
	/** Maximum credits for Course */
	private static final int MAX_CREDITS = 5;
	/** Minimum credits for Course */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course name validator */
	private CourseNameValidator validator = new CourseNameValidator();
	/** Roll of Students for Course */
	private CourseRoll roll;

	/**
	 * Constructs a Course object with values for all fields. Using setters to
	 * construct the constructor to reduce redundancy. Calls Activity's constructor
	 * to set title, meetingDays, startTime, and endTime. Constructs roll to be a
	 * new CourseRoll with the current class Course and the enrollmentCap. An
	 * IllegalArgumentException is thrown if any of the given parameters are
	 * invalid.
	 * 
	 * @param name          name of Course
	 * @param title         title of Course
	 * @param section       section of Course
	 * @param credits       credit hours for Course
	 * @param instructorId  instructor's unity id
	 * @param enrollmentCap the enrollment cap for CourseRoll
	 * @param meetingDays   meeting days for Course as series of chars
	 * @param startTime     start time for Course
	 * @param endTime       end time for Course
	 * @throws IllegalArgumentException if the given parameters are invalid.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		this.roll = new CourseRoll(this, enrollmentCap);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged. startTime and endTime are
	 * instantiated to zero. Follows the paradigm of one path of construction to
	 * reduce redundancy. An IllegalArgumentException is thrown if any of the given
	 * parameters are invalid.
	 * 
	 * @param name          name of Course
	 * @param title         title of Course
	 * @param section       section of Course
	 * @param credits       credit hours for Course
	 * @param instructorId  instructor's unity id
	 * @param enrollmentCap the enrollment cap for CourseRoll
	 * @param meetingDays   meeting days for Course as series of chars
	 * @throws IllegalArgumentException if the given parameters are invalid.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the Course's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. An IllegalArgumentException is thrown if the name is
	 * null, is an empty string, contains less than 5 characters, contains more than
	 * 8 characters, does not contain one space between letters and numbers, does
	 * not start with 1 to 4 letters, or does not end with 3 digits.
	 * 
	 * @param name the name to set
	 * @throws InvalidTransitionException if the name is not valid when passed into
	 *                                    the isValid method from Validator
	 * @throws IllegalArgumentException   if the name is null, empty, less than 5
	 *                                    characters, more than 8 characters, does
	 *                                    not contain a space between letter and
	 *                                    numbers, does not start with 1 to 4
	 *                                    letters, or does not end with 3 digits.
	 */
	private void setName(String name) {

		// Throw exception if the name is null
		if (name == null || "".equals(name))
			throw new IllegalArgumentException("Invalid course name.");

		/*
		 * // Throw exception if the name is an empty string // Throw exception if the
		 * name contains less than 5 characters or greater than 8 // characters. if
		 * (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) throw
		 * new IllegalArgumentException("Invalid course name.");
		 * 
		 * // Check for pattern of L[LLL] NNN int numOfLetters = 0; int numOfDigits = 0;
		 * boolean spaceFound = false; for (int i = 0; i < name.length(); i++) { if
		 * (!spaceFound) { if (Character.isLetter(name.charAt(i))) numOfLetters++; else
		 * if (name.charAt(i) == ' ') spaceFound = true; else throw new
		 * IllegalArgumentException("Invalid course name."); } else if (spaceFound) { if
		 * (Character.isDigit(name.charAt(i))) numOfDigits++; else throw new
		 * IllegalArgumentException("Invalid course name."); } }
		 * 
		 * // Check that the number of letters is correct if (numOfLetters <
		 * MIN_LETTER_COUNT || numOfLetters > MAX_LETTER_COUNT) throw new
		 * IllegalArgumentException("Invalid course name.");
		 * 
		 * // Check that the number of digits is correct if (numOfDigits != DIGIT_COUNT)
		 * throw new IllegalArgumentException("Invalid course name.");
		 */
		// System.out.println("Try" + name);
		try {
			if (validator.isValid(name)) {
				this.name = name;
			} else {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	}

	/**
	 * Returns the Course's section. The value is a string of 3 digit characters.
	 * 
	 * @return the Course's section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. An IllegalArgumentException is thrown if the
	 * section is null, does not contain 3 characters, or if the 3 characters are
	 * not digits.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null or does not contain
	 *                                  exactly 3 characters as digits
	 */
	public void setSection(String section) {

		// Throws exception if section is null or not 3 characters
		if (section == null || section.length() != SECTION_LENGTH)
			throw new IllegalArgumentException("Invalid section.");

		// Throws exception if the 3 characters in section are not digits
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i)))
				throw new IllegalArgumentException("Invalid section.");
		}
		this.section = section;
	}

	/**
	 * Returns the Course's number of credit hours. This value is between 1 and 5,
	 * inclusive.
	 * 
	 * @return the course's number of credit hours
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's number of credit hours. An IllegalArgumentException is
	 * thrown if credit hours are less than 1 or greater than 5
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credit hours are less than 1 or greater
	 *                                  than 5
	 */
	public void setCredits(int credits) {

		// Throws exception if number of credits is less than 1 or greater than 5
		if (credits < MIN_CREDITS || credits > MAX_CREDITS)
			throw new IllegalArgumentException("Invalid credits.");

		this.credits = credits;
	}

	/**
	 * Returns the instructor's unity id.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the instructer's unity id. An IllegalArgumentException is thrown if
	 * instructorId is an empty string
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is empty
	 */
	public void setInstructorId(String instructorId) {

		// Throws exception if instructorId is an empty string
		if ("".equals(instructorId))
			throw new IllegalArgumentException("Invalid instructor id.");
		this.instructorId = instructorId;
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this Course object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields. False if the objects
	 *         are not equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields. The returned
	 * string is specific to Course objects.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return getName() + "," + getTitle() + "," + getSection() + "," + getCredits() + "," + getInstructorId()
					+ "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return getName() + "," + getTitle() + "," + getSection() + "," + getCredits() + "," + getInstructorId() + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Returns an array that contains the Course name, section, title, and meeting
	 * string. The details in the array are specific to Course objects.
	 * 
	 * @return array with Course's name, section, title, and meeting string.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = { getName(), getSection(), getTitle(), getMeetingString(),
				Integer.toString(roll.getOpenSeats()) };
		return shortDisplay;
	}

	/**
	 * Returns an array that contains the Course name, section, title, credits,
	 * instructorId, meeting string, and an empty string. The details in the array
	 * are specific to Course objects.
	 * 
	 * @return array with Course's name, section, title, credits, instructorId,
	 *         meeting string, and an empty string.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = { getName(), getSection(), getTitle(), String.valueOf(getCredits()), getInstructorId(),
				getMeetingString(), "" };
		return longDisplay;
	}

	/**
	 * Sets the course's meeting days and times. IllegalArgumentException is thrown
	 * if meetingDays is null, there are invalid characters, and if a meeting day is
	 * counted more than once. The parameters are then passed on to Activity's
	 * setMeetingDaysAndTime method to handle common checks and for final
	 * assignment. Override is necessary as Course objects only take weekdays and
	 * the character "A" as meetingDays parameters.
	 * 
	 * @param meetingDays meeting days for the course, limited to weekdays
	 * @param startTime   course's start time
	 * @param endTime     course's end time
	 * @throws IllegalArgumentException if meetingDays is null, contains duplicates,
	 *                                  or is not a valid character.
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null)
			throw new IllegalArgumentException("Invalid meeting days and times.");
		if ("A".equals(meetingDays)) {
			// Throws exception if startTime or endTime is not 0
			if (startTime != 0 || endTime != 0)
				throw new IllegalArgumentException("Invalid meeting days and times.");
			// Meeting days is set to parameter, startTime and endTime are set to 0
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}

		// Checks for valid meetingDays if it has not been arranged
		else {
			// Count for each weekday letter
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			// Checks for valid letter input. Throws exception if invalid letter is found
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M')
					mCount++;
				else if (meetingDays.charAt(i) == 'T')
					tCount++;
				else if (meetingDays.charAt(i) == 'W')
					wCount++;
				else if (meetingDays.charAt(i) == 'H')
					hCount++;
				else if (meetingDays.charAt(i) == 'F')
					fCount++;
				else
					throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// Throws exception if a weekday was counted more than once
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1)
				throw new IllegalArgumentException("Invalid meeting days and times.");
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Returns true if course is a duplicate and false if not a duplicate. Checks if
	 * the course already exists in the given activity. Override is necessary as
	 * course objects are only compared to other instances of Course.
	 * 
	 * @param activity activity to compare with course
	 * @return true if course is a duplicate. False if course is not a duplicate.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			if (this.getName().equals(course.getName()))
				return true;
		}

		return false;
	}

	@Override
	public int compareTo(Course c) {
		int compareName = this.getName().compareTo(c.getName());
		int compareSection = this.getSection().compareTo(c.getSection());
		if (compareName > 0)
			return 1;
		else if (compareName < 0)
			return -1;
		else if (compareSection > 0)
			return 1;
		else if (compareSection < 0)
			return -1;
		return 0;
	}

	/**
	 * Getter for roll
	 * 
	 * @return course roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
}
