/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * This is the class that helps us create and manage Faculty objects. This class is a POJO class which means 
 * it consist mainly of setters and getters.
 */
public class Faculty extends User {
	
	/**
	 * This is the max number of Courses that can be enrolled this faculty member
	 */
	private int maxCourses;
	
	/**
	 * This is the maximum amount of credits that can be enrolled by a faculty member
	 */
	private static final int MAX_COURSES = 3;
	
	/**
	 * This is the minimum number of courses that can be enrolled by a faculty memeber
	 */
	private static final int MIN_COURSES = 1;
	
	/**
	 * This is the Faculty Schedule for this faculty
	 */
	private FacultySchedule facultySchedule;
	
	/**
	 * This is the constructor for the faculty member
	 * @param firstName of the faculty member
	 * @param lastName of the faculty member
	 * @param id of the faculty member
	 * @param email of the faculty member
	 * @param password of the faculty member
	 * @param maxCourses is the number of courses the faculty has enrolled in
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		facultySchedule = new FacultySchedule(id);
	}
	
	/**
	 * This is the setter for maxCourses
	 * @param maxCourses is the number we want to set this faculty's courses to 
	 * @throws IllegalArgumentException if the number of courses for this faculty is more than 3
	 * or less than 1
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses > MAX_COURSES || maxCourses < MIN_COURSES) {
			throw new IllegalArgumentException("Invalid courses");
		}
		else {
			this.maxCourses = maxCourses;
		}
	}
	
	/**
	 * This is the getter method for the maxCourses
	 * @return the number of courses for this faculty member
	 */
	public int getMaxCourses() {
		return this.maxCourses;
	}
	
	/**
	 * Getter for facultySchedule
	 * @return the faculties schedule object
	 */
	public FacultySchedule getSchedule() {
		return this.facultySchedule;
	}
	
	/**
	 * This is the method that checks if the faculty is overloaded or not
	 * @return true if the faculty has more than the maximum number of courses. False otherwise
	 */
	public boolean isOverloaded() {
		return facultySchedule.getScheduledCourses().length > maxCourses;
	}
	
	/**
	 * This is a method to convert a unique hashcode for every faculty
	 * @return result which is the unique hashcode for the faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}
	
	/**
	 * this is the method that compares two faculties and checks if they are equal
	 * @return boolean value for if the faculties are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return!(maxCourses != other.maxCourses);
	}
	
	/**
	 * Returns a comma separated value string of all Faculty fields.
	 * @return String representation of Faculty
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCourses;
	}

}
