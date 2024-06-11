/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * This method is responsible for reading the files passed in from the user
 * using the delimiter and scanners, we break apart the strings into the proper
 * format specified by the writeup and requirements being: firstName, lastName,
 * id, email, hashedPassword, maxCourses
 * 
 * @author Armando rosas
 *
 */
public class FacultyRecordIO {

	/**
	 * The readFacultyRecords method reads in the text file that the user selected
	 * using the scanner we read in the file and check for duplicate faculty
	 * members, if there is no duplicate, it is added to the faculty LinkedList
	 * 
	 * @param fileName is the file from which the load the facultyRecords, they are
	 *                 provided in the test-files folder
	 * @return a LinkedList of the faculty members
	 * @throws FileNotFoundException if the file cannot be read, it could be non
	 *                               existent
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {

				Faculty faculty = processFaculty(fileReader.nextLine());

				boolean duplicate = false;

				for (int i = 0; i < facultyList.size(); i++) {

					Faculty currentFaculty = facultyList.get(i);
					if (faculty.getFirstName().equals(currentFaculty.getFirstName())
							&& faculty.getLastName().equals(currentFaculty.getLastName())
							&& faculty.getId().equals(currentFaculty.getId())) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					facultyList.add(faculty);
				}
			} catch (IllegalArgumentException e) {
				fileReader.next();
			}
		}
		fileReader.close();
		return facultyList;
	}

	/**
	 * This private helper method helps to process the line scanned from fileName it
	 * stores the elements scanned from the line a Faculty object is created where
	 * we store the parameters This student object is returned
	 * 
	 * @param nextLine is the nextLine that is read in from the readFacultyRecords
	 *                 method
	 * @return Faculty is the faculty member with the complete parameters, first and
	 *         last name, their id, email, password, and maximum amount of courses
	 */
	private static Faculty processFaculty(String nextLine) {
		Scanner lineReader = new Scanner(nextLine);
		lineReader.useDelimiter(",");
		try {
			String fName = lineReader.next();
			String lName = lineReader.next();
			String id = lineReader.next();
			String email = lineReader.next();
			String pass = lineReader.next();
			int max = lineReader.nextInt();
			lineReader.close();
			Faculty facultyMember = new Faculty(fName, lName, id, email, pass, max);
			return facultyMember;
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * WriteFacultyRecords is in charge of writing the contents to the file
	 * 
	 * @param fileName         is the file which we are writing to
	 * @param facultyDirectory is the LinkedList which contains the faculty, this
	 *                         will be written to the file
	 * @throws IOException if for any reason, the method is unable to write to the
	 *                     file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
	}

}
