/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * User parent class to student and registar 
 * 
 * @author Kiran
 * @author Kennedy
 * @author Armando
 *
 */
public abstract class User {
	/** This is the students first. */
	private String firstName;
	/** This is the students last name. */
	private String lastName;
	/** This is the student's id. */
	private String id;
	/** This is the student's email. */
	private String email;
	/** This is the password. */
	private String password;
	
	/**
	 * Constructor for user
	 * @param firstName first name of student
	 * @param lastName last name of student
	 * @param id id of student
	 * @param email email of student
	 * @param password password of student
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	/**
	 * Getter for id
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * gets the users id
	 * @param id the id to set
	 */
	public void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}
	/**
	 * Getter for email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the student's email if the given parameter is valid. An
	 * IllegalArgumentException is thrown if email is null, empty, does not contain
	 * '@', does not contain '.', and if the last index of '.' is earlier than the
	 * first index of '@'.
	 * 
	 * @param email student's email
	 * @throws IllegalArgumentException if given email parameter is invalid
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		} else if (!email.contains("@") || !email.contains("."))
			throw new IllegalArgumentException("Invalid email");
		else if (email.lastIndexOf(".") < email.indexOf("@"))
			throw new IllegalArgumentException("Invalid email");
		this.email = email;
	}
	/**
	 * Getter for password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the student's password if the given parameter is valid. An
	 * IllegalArgumentException is thrown if password is null or an empty string.
	 * 
	 * @param password student's password
	 * @throws IllegalArgumentException if given password parameter is invalid
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password))
			throw new IllegalArgumentException("Invalid password");
		this.password = password;
	}
	/**
	 * Getter for first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * This method sets the student's first name if the given parameter is valid. An
	 * IllegalArgumentException is thrown if the firstName is null or is an empty
	 * string.
	 * 
	 * @param firstName student's first name
	 * @throws IllegalArgumentException if the firstName parameter is invalid
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}
	/**
	 * Getter for last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the student's last name if the given parameter is valid. An
	 * IllegalArgumentException is thrown if lastName is null or an empty string.
	 * 
	 * @param lastName student's last name
	 * @throws IllegalArgumentException if lastName parameter is invalid
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}
	
}
