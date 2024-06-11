package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * FacultyDirectory contains all of the faculty object passed to it. Manages these with the ability
 * to load faculty into the directory, or save the current directory to a file. Can also add faculty to
 * the directory if the specified parameters are passed. Can return the directory as a 2D array, and also
 * get faculty objects in the directory if their id is passed.
 * @author crjone24
 * @author eltreece
 *
 */
public class FacultyDirectory {
	/** List of faculty that can be accessed */
	LinkedList<Faculty> facultyDirectory;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructor for the FacultyDirectory class. Calls a different method in order
	 * to construct the linked list of faculty.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Resets the facultyDirectory to a new linked list
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Loads the faculty from a file
	 * @param fileName file name
	 * @throws IllegalArgumentException if the file is not found
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Constructs a faculty object from the given parameters. Then adds that faculty object to the faculty directory.
	 * Checks that there are no duplicates by iterating through the faculty directory and looking at the ids, if there
	 * is a duplicate id then the constructed faculty object is not added to the directory and false is returned. Returns
	 * true if the faculty object is successfully added to the directory.
	 * @param firstName first name
	 * @param lastName last name
	 * @param id id
	 * @param email email
	 * @param password password
	 * @param maxCourses max courses
	 * @param repeatPassword repeat password
	 * @return true if the faculty is successfully added to the faculty directory, false if there is a duplicate
	 * @throws IllegalArgumentException if the password or repeatPassword are empty or null
	 * 									if faculty cannot be constructed from the given parameters
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
			String hashPW = "";
			String repeatHashPW = "";
			if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
				throw new IllegalArgumentException("Invalid password");
			}
			try {
				MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
				digest1.update(password.getBytes());
				hashPW = new String(digest1.digest());

				MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
				digest2.update(repeatPassword.getBytes());
				repeatHashPW = new String(digest2.digest());
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Cannot hash password");
			}

			if (!hashPW.equals(repeatHashPW)) {
				throw new IllegalArgumentException("Passwords do not match");
			}

			// If an IllegalArgumentException is thrown, it's passed up from Student
			// to the GUI
			Faculty faculty = null;
			
			faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);

			for (int i = 0; i < facultyDirectory.size(); i++) {
				Faculty f = facultyDirectory.get(i);
				if (f.getId().equals(faculty.getId())) {
					return false;
				}
			}
			return facultyDirectory.add(faculty);
	}
	
	/**
	 * Removed the faculty with the given id from the faculty directory. Done by
	 * iterating through the directory and checking if a faculty memeber has the same
	 * id as the parameter, if it does than the faculty object in the directory is removed
	 * at the current index, and true is returned. False is returned if the faculty directory
	 * is fully iterated through and there are no matches.
	 * @param facultyId faculty id
	 * @return true if the faculty is successfully removed, false if there is no match and nothing
	 * 			removed from the directory.
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			if (f.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the faculty directory in the form of a 2D array. The array contains each faculty, and
	 * their first name, last name, and then id.
	 * @return 2D array of the faculty directory
	 */
	public String[][] getFacultyDirectory() {
		String[][] directoryArray = new String[facultyDirectory.size()][3];
		
		for(int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			directoryArray[i][0] = f.getFirstName();
			directoryArray[i][1] = f.getLastName();
			directoryArray[i][2] = f.getId();
		}
		
		return directoryArray;
	}
	
	/**
	 * Saves the faculty directory to a file name.
	 * @param fileName file name
	 * @throws IllegalArgumentException if the directory is unable to be written to file
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	/**
	 * Gets the faculty by id. Done by iterating through the faculty directory and
	 * checking for the given id by each id at index. Returns the faculty object at
	 * index if their id matches with the given id. Returns null if there are no
	 * matches found.
	 * the ids match
	 * @param id id
	 * @return the faculty object with the given id if it exist in facultyDirectory,
	 * 			if it doesn't exist than null is returned.
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(id)) {
				return facultyDirectory.get(i);
			}
		}
		return null;
	}
	
}
