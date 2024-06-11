package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * StudentRecordIO class reads Student records from a file and stores them in a
 * SortedList. The Students are stored in the alphabetical order starting with
 * last name, first name, then id. A SortedList of Students can also be exported
 * and saved onto a specified file.
 * 
 * @author Jongwoo Shin
 * @author Armando Rosas
 * @author Haleema Begum
 */
public class StudentRecordIO {
	/**
	 * readStudentRecords method reads student records from fileName and stores the
	 * Student object in a SortedList. A FileNotFoundException is thrown if the file
	 * being scanned is not found. An IllegalArgumentException is thrown if the
	 * Student object can't be created.
	 * 
	 * @param fileName the file that contains the student records
	 * @return the SortedList that contains the Student objects obtained from the
	 *         file
	 * @throws FileNotFoundException    if the file does not exist on the system
	 * @throws IllegalArgumentException if Student object can't be created
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {

				Student student = processStudent(fileReader.nextLine());

				boolean duplicate = false;

				for (int i = 0; i < students.size(); i++) {

					Student currentStudent = students.get(i);
					if (student.getFirstName().equals(currentStudent.getFirstName())
							&& student.getLastName().equals(currentStudent.getLastName())
							&& student.getId().equals(currentStudent.getId())) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException e) {
				fileReader.next();
			}
		}
		fileReader.close();
		return students;
	}

	/**
	 * This method is private and processes the line scanned from fileName. It
	 * stores the elements from scanned from the line and stores them in
	 * corresponding fields. A Student object is then created using the elements as
	 * parameters. This student object is returned to the caller.
	 * NoSuchElementException is thrown if an element is unable to be stored from
	 * the scanned line. IllegalArgumentException is thrown if a Student object is
	 * unable to be created.
	 * 
	 * @param nextLine the line scanned from fileName in readStudentRecords method.
	 * @return the constructed Student object
	 * @throws NoSuchElementException   if element is unable to be stored from the
	 *                                  scanned line
	 * @throws IllegalArgumentException if Student object is unable to be created
	 */
	private static Student processStudent(String nextLine) {
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
			Student student = new Student(fName, lName, id, email, pass, max);
			return student;
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * The writeStudentRecords method writes the student's records onto the fileName
	 * provided. The records should be written in the same order that the students
	 * were stored in the SortedList. IOException is thrown if saving to file was
	 * unsuccessful.
	 * 
	 * @param fileName         the name of the file
	 * @param studentDirectory the SortedList where the students are stored
	 * @throws IOException if unable to write file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();

	}

}
