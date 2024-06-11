package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads course records from text files and stores constructed course objects
 * into a SortedList. Serves as a key element in being able to use text files as
 * a source for available courses. Used by CourseCatalog to store courses in
 * catalog.
 * 
 * @author Jongwoo Shin
 * @author Haleema
 *
 */
public class CourseRecordIO {
	/**
	 * Reads course records from a file and generates a SortedList of valid Courses.
	 * Any invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown. Implements the
	 * readCourse helper method to construct Course objects using valid parameters
	 * from fileName.
	 * 
	 * @param fileName file to read Course records from
	 * @return a SortedList of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Course> courses = new SortedList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the ArrayList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the SortedList with all the courses we read!
		return courses;

	}

	/**
	 * This private method takes a line from readCourseRecords and constructs an
	 * instance of the Course class. Returns the Course object constructed in this
	 * method. An IllegalArgumentException is thrown if arranged courses have start
	 * or end times, if there are too many inputs in the line, or if an input is
	 * missing. Uses the registration manager's faculty directory to determine if the course
	 * should have an instructor assigned to it or if it should be null.
	 * 
	 * @param nextLine line obtained from file read from readCourseRecords
	 * @return a constructed Course object if the parameter is a valid line of valid
	 *         inputs.
	 * @throws IllegalArgumentException if the parameter is not a valid line of
	 *                                  inputs
	 */
	private static Course readCourse(String nextLine) {
		//System.out.println(nextLine);
		Scanner readLine = new Scanner(nextLine);
		readLine.useDelimiter(",");
		try {
			String name = readLine.next();
			String title = readLine.next();
			String section = readLine.next();
			int creditHours = readLine.nextInt();
			String instructor = readLine.next();
			int enrollmentCap = readLine.nextInt();
			String meetingDays = readLine.next();
			Course course;
			
			if ("A".equals(meetingDays)) {
				if (readLine.hasNext()) {
					readLine.close();
					throw new IllegalArgumentException();
				}
				readLine.close();
				 course = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDays);
			} else {
				int startTime = readLine.nextInt();
				int endTime = readLine.nextInt();
				if (readLine.hasNext()) {
					readLine.close();
					throw new IllegalArgumentException();
				}
				readLine.close();
				course = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDays, startTime,
						endTime);
			}
			
			RegistrationManager rm = RegistrationManager.getInstance();
			Faculty f = rm.getFacultyDirectory().getFacultyById(instructor);
			
			if(f != null) {
				f.getSchedule().addCourseToSchedule(course);
			} 
			
			return course;
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * this method writes courses into a txt file.
	 * 
	 * @param fileName   this is the output file name
	 * @param activities list of courses.
	 * @throws IOException if the file cannot be found.
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> activities) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < activities.size(); i++) {
			fileWriter.println(activities.get(i).toString());
		}

		fileWriter.close();

	}

}
