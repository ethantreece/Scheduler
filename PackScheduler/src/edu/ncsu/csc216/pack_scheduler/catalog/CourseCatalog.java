package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This class reads and stores all of the available Course records as a
 * SortedList from a given file. The courses are stored in sorted order in
 * catalog. Course records can then be written and saved onto a valid file.
 * 
 * @author Jongwoo Shin
 *
 */
public class CourseCatalog {
	/** Sorted List of courses that make up the catalog */
	private SortedList<Course> catalog;

	/**
	 * Constructs an empty SortedList of Course objects called catalog.
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs an empty SortedList of Course objects called catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Reads and stores Course objects read from a valid file into catalog. An
	 * IllegalArgumentException is thrown if the file cannot be found. Implements
	 * readCourseRecords from CourseRecordIO to read Courses from fileName.
	 * 
	 * @param fileName name of the file to read Course records from
	 * @throws IllegalArgumentException if file cannot be found
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Returns true if Course is added to catalog and false if Course already exists
	 * in the catalog. Implements getCourseFromCatalog to check if course exists in
	 * the catalog. An IllegalArgumentException is thrown if there is an error in
	 * constructing the Course object.
	 * 
	 * @param name         Course's name
	 * @param title        Course's title
	 * @param section      Course's section
	 * @param credits      number of credits for Course
	 * @param instructorId instructor's id
	 * @param meetingDays  Course's meeting days
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 * @param enrollmentCap cap of student in a course
	 * @return true if Course was successfully added, false if Course already exists
	 *         in catalog
	 * @throws IllegalArgumentException if Course cannot be constructed
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		if (getCourseFromCatalog(name, section) == null) {
			catalog.add(course);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns true if Course was successfully removed from the catalog and returns
	 * false if the course does not exist in the catalog.
	 * 
	 * @param name    Course's name
	 * @param section Course's section
	 * @return true if course was removed from catalog, false if course does not
	 *         exist in catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		if (course == null)
			return false;
		else {
			catalog.remove(catalog.indexOf(course));
			return true;
		}
	}

	/**
	 * Returns Course object if course exists in the catalog. This method scans the
	 * catalog using course name and section as parameters. If course does not exist
	 * in the catalog, null is returned.
	 * 
	 * @param name    Course's name
	 * @param section Course's section
	 * @return Course object that corresponds with name and section parameters if
	 *         the course exists in the catalog. If course is nonexistent, null is
	 *         returned
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				return catalog.get(i);
			}

		}
		return null;
	}

	/**
	 * Scans the catalog and returns a 2D String array with the course's name,
	 * section, title, and the meeting string. Implements the getShortDisplayArray
	 * method from Course class.
	 * 
	 * @return 2D String array with a row for each course and four columns for name,
	 *         section, title, and meeting string. Returns empty 2D String array if
	 *         catalog is empty
	 */
	public String[][] getCourseCatalog() {
		if (catalog.isEmpty()) {
			String[][] emptyArray = {};
			return emptyArray;
		} else {
			String[][] catalogArray = new String[catalog.size()][5];
			for (int i = 0; i < catalog.size(); i++) {
				Course c = catalog.get(i);
				catalogArray[i] = c.getShortDisplayArray();
			}
			return catalogArray;
		}
	}

	/**
	 * Writes Course records in the catalog onto a valid file. An
	 * IllegalArgumentException is thrown if the file cannot be saved. Implements
	 * writeCourseRecords from CoureRecordIO to export the catalog onto fileName.
	 * 
	 * @param fileName name of file to save Course records to
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}
