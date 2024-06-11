package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Activity is an abstract class that stores information on an activity's title,
 * meeting days, start time, and end time. Serves as a parent class to Course.
 * Provides the abstract methods getShortDisplayArray, getLongDisplayArray, and
 * isDuplicate for subclasses to implement. Implements Conflict interface and
 * its checkConflict abstract method. Checks if two activity instances have at
 * least one day and time that may cause a conflict in a student's schedule.
 * 
 * @author Jongwoo Shin
 *
 */
public abstract class Activity implements Conflict {

	/** Invalid military time if hour is equal or greater */
	private static final int UPPER_HOUR = 24;
	/** Invalid military time if minute is equal or greater than */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Constructs an Activity object with values of title, meeting days, start time,
	 * and end time. An Activity object cannot be instantiated but allows both
	 * Course and Event to implement it in their own constructors. An
	 * IllegalArgumentException is thrown if the given parameters are invalid.
	 * 
	 * @param title       title of activity
	 * @param meetingDays an activity's meeting days
	 * @param startTime   an activity's start time
	 * @param endTime     an activity's end time
	 * @throws IllegalArgumentException if the given parameters are invalid.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the activity's title.
	 * 
	 * @return the activity's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the activity's title. IllegalArgumentException is thrown if the title is
	 * null or an empty string.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or an empty string
	 */
	public void setTitle(String title) {

		// Throws exception if the title is null or an empty string
		if (title == null || "".equals(title))
			throw new IllegalArgumentException("Invalid title.");

		this.title = title;
	}

	/**
	 * Returns the activity's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the activity's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the activity's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the activity's meeting days and times. IllegalArgumentException is
	 * thrown if meetingDays is null, empty, or contains invalid characters. The
	 * same applies for incorrect startTime, incorrect endTime, or if endTime is
	 * less than startTime.
	 * 
	 * @param meetingDays the meetingDays to set
	 * @param startTime   the startTime to set
	 * @param endTime     the endTime to set
	 * @throws IllegalArgumentException if parameters doesn't meet the requirements
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		// Throws exception if meetingDays is null or an empty string
		if (meetingDays == null || "".equals(meetingDays))
			throw new IllegalArgumentException("Invalid meeting days and times.");

		// Throws exception if startHour is not between 0 and 23, inclusive
		if (getHour(startTime) >= UPPER_HOUR || getHour(startTime) < 0)
			throw new IllegalArgumentException("Invalid meeting days and times.");
		// Throws exception if startMin is not between 0 and 59, inclusive
		if (getMinute(startTime) >= UPPER_MINUTE || getMinute(startTime) < 0)
			throw new IllegalArgumentException("Invalid meeting days and times.");
		// Throws exception if endHour is not between 0 and 23, inclusive
		if (getHour(endTime) >= UPPER_HOUR || getHour(endTime) < 0)
			throw new IllegalArgumentException("Invalid meeting days and times.");
		// Throws exception if endMin is not between 0 and 59, inclusive
		if (getMinute(endTime) >= UPPER_MINUTE || getMinute(endTime) < 0)
			throw new IllegalArgumentException("Invalid meeting days and times.");
		// Throws exception if endTime is less than startTime
		if (endTime < startTime)
			throw new IllegalArgumentException("Invalid meeting days and times.");
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns meeting days and meeting times in standard time format for an
	 * Activity object. If the meeting days for the activity is arranged, the string
	 * "Arranged" will be returned. Implements the helper method getTimeString to
	 * convert military time into standard time format in the form of a String.
	 * 
	 * @return the meeting days and times in standard time format. If arranged, just
	 *         returns "Arranged"
	 */
	public String getMeetingString() {
		if ("A".equals(meetingDays)) {
			return "Arranged";
		} else
			return meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
	}

	/**
	 * Returns the meeting time in standard time format. The string is formatted so
	 * there is a colon in between the hour and minutes. The time is also specified
	 * as PM or AM. Implements getHour and getMinute helper methods to convert
	 * military time into standard hours and minutes.
	 * 
	 * @param militaryTime time in military format
	 * @return the meeting time in standard time format
	 */
	private String getTimeString(int militaryTime) {
		String hour = "";
		String minute = "";
		String standardTime = "";

		if (getHour(militaryTime) >= 12) {
			if (getHour(militaryTime) > 12) {
				hour = String.valueOf(getHour(militaryTime) - 12);
				if (getMinute(militaryTime) < 10) {
					minute = "0".concat(String.valueOf(getMinute(militaryTime)));
				} else
					minute = String.valueOf(getMinute(militaryTime));
				standardTime += hour + ":" + minute + "PM";
			} else {
				hour = String.valueOf(getHour(militaryTime));
				if (getMinute(militaryTime) < 10) {
					minute = "0".concat(String.valueOf(getMinute(militaryTime)));
				} else
					minute = String.valueOf(getMinute(militaryTime));
				standardTime += hour + ":" + minute + "PM";
			}

		} else if (getHour(militaryTime) == 0) {
			hour = "12";

			if (getMinute(militaryTime) < 10) {
				minute = "0".concat(String.valueOf(getMinute(militaryTime)));
			} else
				minute = String.valueOf(getMinute(militaryTime));
			standardTime += hour + ":" + minute + "AM";
		} else {
			hour = String.valueOf(getHour(militaryTime));
			if (getMinute(militaryTime) < 10) {
				minute = "0".concat(String.valueOf(getMinute(militaryTime)));
			} else
				minute = String.valueOf(getMinute(militaryTime));
			standardTime += hour + ":" + minute + "AM";

		}

		return standardTime;
	}

	/**
	 * Converts military time into standard time format and returns the hour.
	 * 
	 * @param militaryTime time in military format
	 * @return the hour converted from military format to standard time format
	 */
	private int getHour(int militaryTime) {
		return militaryTime / 100;
	}

	/**
	 * Converts military time into standard time format and returns the minute.
	 * 
	 * @param militaryTime time in military format
	 * @return the minute converted from military format to standard time format
	 */
	private int getMinute(int militaryTime) {
		return militaryTime % 100;
	}

	/**
	 * Returns an array with 4 columns for activity's name, section, title, and
	 * meeting string if applicable. Used to populate the rows of the course catalog.
	 * 
	 * @return an array with an activity's name, section, title, and meeting string.
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Returns an array with 7 columns for activity's name, section, title, meeting
	 * string, credits, instructor's id, meeting string, and event details if
	 * applicable. Used to display the final schedule.
	 * 
	 * @return array with activity's name, section, title, meeting string, credits,
	 *         instructor's id, meeting string, and event details
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Returns true if course is a duplicate and false if not a duplicate.
	 * Used to check if the course or event already exists.
	 * 
	 * @param activity activity to compare
	 * @return true if the activity is a duplicate. False if the activity is not a
	 *         duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Generates a hashCode for Activity using all fields.
	 * 
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this Activity object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields. False if the objects
	 *         are not equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Compares the current instance of Activity with possibleConflictingActivity to
	 * see if any conflict exists in their days and times. There is a conflict if
	 * there is at least one day with an overlapping time, down to the minutes.
	 * Nothing happens if there are no conflicts. ConflictExceptin is thrown if a
	 * conflict is found.
	 * 
	 * @param possibleConflictingActivity the activity that may cause a conflict
	 * @throws ConflictException if there is at least one day with an overlapping
	 *                           time
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {

		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			for (int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				if (this.getMeetingDays().charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(j)
						&& this.getMeetingDays().charAt(i) != 'A'
						&& possibleConflictingActivity.getMeetingDays().charAt(j) != 'A') {
					if (this.getStartTime() == possibleConflictingActivity.getStartTime())
						throw new ConflictException();
					else if (this.getStartTime() == possibleConflictingActivity.getEndTime())
						throw new ConflictException();
					else if (this.getEndTime() == possibleConflictingActivity.getStartTime())
						throw new ConflictException();
					else if (this.getStartTime() >= possibleConflictingActivity.getStartTime()
							&& this.getStartTime() <= possibleConflictingActivity.getEndTime())
						throw new ConflictException();
					else if (this.getEndTime() >= possibleConflictingActivity.getStartTime()
							&& this.getEndTime() <= possibleConflictingActivity.getEndTime())
						throw new ConflictException();
					else if (this.getStartTime() <= possibleConflictingActivity.getStartTime()
							&& this.getEndTime() >= possibleConflictingActivity.getEndTime())
						throw new ConflictException();
				}
			}
		}

	}

}