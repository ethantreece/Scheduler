/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Manages a user who logs in and issues tasks to the student directory or
 * course catalog We use log in and log out to determine who is logging in based
 * off their credentials, their id this class also serves to return the current
 * user and the faculty directory
 * 
 * @author Kiran
 * @author Armando Rosas
 * @author Kennedy
 * 
 *
 */
public class RegistrationManager {

	/** Instance of registration manager */
	private static RegistrationManager instance;
	/** Course Catalog */
	private CourseCatalog courseCatalog;
	/** Student Directory */
	private StudentDirectory studentDirectory;
	/** The user is a Registrar */
	private User registrar; // private so it causes problems in test
	/** current user of registration manager */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** File contains registrar info */
	private static final String PROP_FILE = "registrar.properties";
	/** This is the instance of FacultyDirectory used to construct a directory */
	private FacultyDirectory facultyDirectory;

	private RegistrationManager() {
//		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		createRegistrar();
		facultyDirectory = new FacultyDirectory();
	}

	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Getter for instance of RegistrationManager, creates one if null
	 * 
	 * @return RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Getter for course catalog
	 * 
	 * @return courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Getter for student directory
	 * 
	 * @return studentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * User login function to set themselves as current user
	 * 
	 * @param id       id of user
	 * @param password password of user
	 * @return true if logged in
	 * @throws IllegalArgumentException if cannot find user or password doesn't
	 *                                  match
	 */
	public boolean login(String id, String password) {

		if (currentUser != null) {
			return false;
		}
		Student s = studentDirectory.getStudentById(id);
//		if (s == null && !id.equals(registrar.getId())) {
//			throw new IllegalArgumentException("User doesn't exist."); 
//		}
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if (s != null) {
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				} else {
					return false;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		}

		Faculty f = facultyDirectory.getFacultyById(id);
		if (f == null && !id.equals(registrar.getId())) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if (f != null && f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		}

		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}

		return false;

	}

	/** Log out of RegistrationManager by setting currentUser to null */
	public void logout() {
//		System.out.println("logout");
		currentUser = null;
	}

	/**
	 * Getter for current user
	 * 
	 * @return currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clear course catalog and student directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();
			
			//Check this line TODO
			if (s.canAdd(c) && roll.canEnroll(s)) {
				roll.enroll(s);
				schedule.addCourseToSchedule(c);
				
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	/**
	 * If the current user is not null and the current user is equal to
	 * the registrar, than the course is added to the faculty's schedule.
	 * @param c course
	 * @param f faculty
	 * @return true if the course is successfully added to faculty's schedule, false
	 * 			if not
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (registrar != null && currentUser != null && registrar.equals(currentUser)) {
			f.getSchedule().addCourseToSchedule(c);
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * If the current user is not null and the current user is equal to
	 * the registrar, than the course is removed from the faculty's schedule.
	 * @param c course
	 * @param f faculty
	 * @return true if the course is successfully added to faculty's schedule, false
	 * 			if not
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (registrar != null && currentUser != null && registrar.equals(currentUser)) {
			f.getSchedule().removeCourseFromSchedule(c);
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Resets the faculty's schedule
	 * @param f faculty
	 */
	public void resetFacultySchedule(Faculty f) {
		if (registrar != null && currentUser != null && registrar.equals(currentUser)) {
			f.getSchedule().resetSchedule();
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * Gets the faculty directory
	 * 
	 * @return the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	private static class Registrar extends User {

		/**
		 * Constructor a registrar user.
		 * 
		 * @param firstName first name of registrar
		 * @param lastName  last name of registrar
		 * @param id        id of registrar
		 * @param email     email of registrar
		 * @param hashPW    password of registrar
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}