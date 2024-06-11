/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayQueue;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * The CourseRoll class is in charge of handling everything to do with a student
 * enrolling in a course Our fields are the enrollmentCapacity of a class we
 * have a LinkedAbstractList called roll which contains the students we also
 * have fields for the minimum amount of students that can enroll and maximum
 * amount of students that can enroll Inside the constructor we pass in a
 * potential enrollment capacity for students in a class we then delegate to the
 * setter methods which check for valid inputs in addition, in this class we can
 * enroll students drop students get the amount of open seats in a class and
 * check the boolean method canEnroll to see if it is possible for a student to
 * enroll The CourseRoll method's are integrated into Course where it is now a
 * parameter for the constructs the enrollment capacity has been added to the
 * Coure's toString() method
 * 
 * @author Kennedy
 * @author Armando
 * @author Kiran
 * @author Ethan
 *
 */
public class CourseRoll {

	/** the roll's enrollment capacity */
	private int enrollmentCap;

	/** This is a LinkedAbstractList which contains students */
	private LinkedAbstractList<Student> roll;

	/** This constant is the minimum amount of of students that can be enrolled */
	private static final int MIN_ENROLLMENT = 10;

	/** This constant is the maximum amount of students that can be enrolled */
	private static final int MAX_ENROLLMENT = 250;
	/**
	 * This constant is the amount of students that can be on a waitlist for a
	 * course
	 */
	private static final int WAITLIST_SIZE = 10;
	/**
	 * This is the waitlist where the students will be added, we are using the
	 * ArrayQueue of type student
	 */
	private ArrayQueue<Student> waitlist;
	/**
	 * This is the course that the course roll is associated with.
	 */
	private Course course;

	/**
	 * The constructor sets the enrollment cap via the setEnrollmentCap method all
	 * the error checking is handled inside the method a new empty
	 * LinkedAbstractList of students with capacity enrollmentCap is created
	 * 
	 * @param c             is the course for which the CourseRoll is associated
	 *                      with
	 * @param enrollmentCap is being passed in to potentially be the capacity for
	 *                      the LinkedAbstractList after passing error checking
	 * @throws IllegalArgumentException if the enrollmentCap is not valid per the
	 *                                  setEnrollmentCap method
	 * 
	 */
	public CourseRoll(Course c, int enrollmentCap) {

		this.roll = new LinkedAbstractList<Student>(10);
		setEnrollmentCap(enrollmentCap);
		waitlist = new ArrayQueue<Student>(WAITLIST_SIZE);
		if (c == null) {
			throw new IllegalArgumentException("Course is null");
		}
		this.course = c;
	}

	/**
	 * The setEnrollmentCap method checks for a valid parameter enrollmentCap if the
	 * enrollmentCap is less than the field MIN_EROLLMENT or if it is greater than
	 * the field MAX_ENROLLMENT we throw an IAE We then check to ensure that roll
	 * has been constructed and that the size of the list is not greater than the
	 * enrollment cap if the enrollmentCap is less than the size, we throw an IAE if
	 * the enrollmentCap parameter passes the tests, we set it to be the
	 * enrollmentCap field
	 * 
	 * @param enrollmentCap is being checked and after passing our tests, it is set
	 *                      to the field enrollmentCap
	 * @throws IllegalArgumentException if the enrollmentCap is less than the list
	 *                                  size or if its value is out of bounds via
	 *                                  the checks with the enrollment fields
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		
		if(roll != null) {
			if (enrollmentCap < roll.size()) {
				throw new IllegalArgumentException();
			}
			roll.setCapacity(enrollmentCap);
			this.enrollmentCap = enrollmentCap;
		}
	}

	/**
	 * This method gets the enrollmentCap field
	 * 
	 * @return enrollmentCap is the field to represent the enrollment capacity
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * The enroll method enrolls a student in the LinkedAbstractList we start by
	 * checking for invalid student objects if the student is null we throw an IAE
	 * if the size of the list is equal to the enrollemntCap field, we throw an IAE
	 * because there is no more room in the class if the student is a duplicate of
	 * one in the list, we throw an IAE Otherwise we add the student to the list and
	 * catch any exceptions and throw an IAE
	 * 
	 * @param s is the student object which after passing all the error checks, we
	 *          add the the LinkedAbstractList
	 * @throws IllegalArgumentException if the student is null
	 * @throws IllegalArgumentException if the student is a duplicate
	 * @throws IllegalArgumentException if there is any issues with adding the
	 *                                  student to the list
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		if(roll.contains(s)) throw new IllegalArgumentException();
		
		
		if (roll.size() == enrollmentCap) {
			if (waitlist.size() == WAITLIST_SIZE) {
				throw new IllegalArgumentException();
			}
			try {
				if(s.canAdd(course)) {
					waitlist.enqueue(s);
				}
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		} else {
			try {
				roll.add(s);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * This method removes a student from the LinkedAbstractList
	 * we start by checking for invalid values for the student object
	 * if the student is null, we throw an IAe
	 * otherwise we attempt to remove the student, catch any exceptions if there are any and throw an IAE
	 * @param s is the student object to the removed
	 * @throws IllegalArgumentException if the student object is null
	 * @throws IllegalArgumentException if there are any issues with removing a student from the list
	 */
	public void drop(Student s) {
		if (s == null) { 
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				roll.remove(i);
				if (waitlist.size() > 0) {
					roll.add(roll.size(), waitlist.dequeue());
					roll.get(roll.size() - 1).getSchedule().addCourseToSchedule(course);
					break;
				}
			}
		}
		for (int i = 0; i < waitlist.size(); i++) {
			Student tempStudent = waitlist.dequeue();
			if (tempStudent != s) {
				waitlist.enqueue(tempStudent);
			} 
		}
		try {
			roll.remove(s);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This boolean method checks if a student can be added to the courseRoll we
	 * start by checking if the class is full, if so, we return false because the
	 * student can not enroll we then check to see if the student is a duplicate, if
	 * so, we return false if we pass both checks, we return true and this means the
	 * student can enroll
	 * 
	 * @param s is the student object being passed in which can potentially enroll
	 *          after passing checks
	 * @return false if the student can not enroll per the error checks, returns
	 *         true if the student passes the error checks and can enroll
	 */
	public boolean canEnroll(Student s) {
		if (waitlist.size() == WAITLIST_SIZE) {
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).compareTo(s) == 0) {
				return false;
			}
		}
		return !waitlist.contains(s); // If waitlist contains s, then false is returned
	}

	/**
	 * This method is responsible for getting the amount of opens seats in a class
	 * we do this by using the enrollmentCap field and subtracting the size of the
	 * LinkedAbstractList roll to give us the availability
	 * 
	 * @return openSeats is the amount of open seats available in a class
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * This method is responsible for getting the number of students in the
	 * waitlist, we return the size field of the waitlist
	 * 
	 * @return waitlist.size() is the number of students in the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}